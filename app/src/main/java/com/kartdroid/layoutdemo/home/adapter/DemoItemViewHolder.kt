package com.kartdroid.layoutdemo.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kartdroid.layoutdemo.databinding.DemoItemViewBinding

const val UNBOUND_POSITION = -1;

class DemoItemViewHolder(
    layoutInflater: LayoutInflater,
    positionClickListener: PositionClickListener,
    viewBinding: DemoItemViewBinding = DemoItemViewBinding.inflate(layoutInflater)
) : RecyclerView.ViewHolder(viewBinding.root) {
    val titleText: TextView = viewBinding.titleText
    var currentBoundPosition: Int = UNBOUND_POSITION

    init {
        /**
         * Important for the view to fill the parent
         */
        viewBinding.root.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        viewBinding.root.setOnClickListener {
            if (currentBoundPosition != UNBOUND_POSITION) {
                /** NOTE: This Closure captures currentBoundPosition reference variable
                 * and hence whenever that object's internal state/position value changes,
                 * it gets reflected on the callback.
                 */
                positionClickListener.onPositionClick(currentBoundPosition)
            }
        }
    }
}