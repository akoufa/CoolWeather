package com.akoufatzis.weatherappclean.custom

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.akoufatzis.weatherappclean.BR

/**
 * Created by alexk on 07.05.17.
 */
class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Any) {
        binding.setVariable(BR.obj, obj)
        binding.executePendingBindings()
    }
}