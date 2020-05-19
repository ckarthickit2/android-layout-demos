package com.kartdroid.layoutdemo.home

import android.app.Application
import androidx.lifecycle.*
import com.kartdroid.layoutdemo.cl.ConstraintDemoActivity
import com.kartdroid.layoutdemo.home.model.DemoItem

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val demoItems: LiveData<List<DemoItem>>

    init {
        val mutableDemoItems = MutableLiveData<List<DemoItem>>()
        this.demoItems = mutableDemoItems
        val demoItems = mutableListOf(
            DemoItem("Constraint Layout Demo", ConstraintDemoActivity::class.java),
            DemoItem("Motion Layout Demo", ConstraintDemoActivity::class.java)
        )
        mutableDemoItems.value = demoItems
    }
}

class HomeVideoModelProviderFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(HomeViewModel::class.java))
        @Suppress("UNCHECKED_CAST")
        return HomeViewModel(application) as T
    }

}
