package com.devpro.android54_day11.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any, VBD : ViewDataBinding> :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.Companion.BaseViewHolder<VBD>>() {
    companion object {
        class BaseViewHolder<VBD : ViewDataBinding>(var binding: VBD) :
            RecyclerView.ViewHolder(binding.root)
    }

    var mListData = mutableListOf<T>()

    fun updateData(data: List<T>) {
        this.mListData = data as MutableList<T>
        notifyDataSetChanged()
    }

    var listener: ((view: View, item: T, position: Int) -> Unit)? = null

    abstract fun getLayout(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VBD> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayout(),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        if (mListData.isEmpty()) {
            return 0
        }
        return mListData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VBD>, position: Int) {

    }
}