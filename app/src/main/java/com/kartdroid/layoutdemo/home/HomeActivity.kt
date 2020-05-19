package com.kartdroid.layoutdemo.home

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kartdroid.layoutdemo.databinding.ActivityHomeBinding
import com.kartdroid.layoutdemo.home.adapter.AdapterViewItemClickListener
import com.kartdroid.layoutdemo.home.adapter.DemoItemsAdapterView
import com.kartdroid.layoutdemo.home.model.DemoItem

/**
 * Activity is a [androidx.lifecycle.ViewModelStoreOwner]
 */
class HomeActivity : AppCompatActivity() {
    private lateinit var homeViewBinding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeViewBinding.root)
        /**
         * Initializing ViewModelProvider for an Activity like this is not very elegant.
         * But this is explicitly done ONLY as a demo of what happens under the hood.
         *
         * [androidx.lifecycle.ViewModelStore] is the one that holds the [androidx.lifecycle.ViewModel]
         */
        val viewModelProvider = ViewModelProvider(this, HomeVideoModelProviderFactory(application))
//        viewModelProvider = ViewModelProvider(viewModelStore, HomeVideoModelProviderFactory(application))
        homeViewModel = viewModelProvider.get(HomeViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.demoItems.observe(this, Observer {
            if (homeViewBinding.layoutDemoList.adapter == null) {
                val adapter = DemoItemsAdapterView(it)
                homeViewBinding.layoutDemoList.swapAdapter(adapter, false)
                homeViewBinding.layoutDemoList.layoutManager = LinearLayoutManager(this)
                adapter.setItemClickListener(object : AdapterViewItemClickListener<DemoItem> {
                    override fun onItemClicked(position: Int, item: DemoItem) {
                        val intent = Intent(Intent.ACTION_DEFAULT)
                        intent.component =
                            ComponentName(this@HomeActivity, item.demoActivityComponetClass)
                        startActivity(intent)
                    }
                })
            }
        })
    }
}
