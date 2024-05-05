package com.devpro.android54_day11.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.devpro.android54_day11.R
import com.devpro.android54_day11.base.BaseRecyclerViewAdapter
import com.devpro.android54_day11.databinding.LayoutItemCommentBinding
import com.devpro.android54_day11.objects.response.comments.Comment

class CommentAdapter:BaseRecyclerViewAdapter<Comment,LayoutItemCommentBinding>() {
    override fun getLayout() = R.layout.layout_item_comment

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<LayoutItemCommentBinding>,
        position: Int
    ) {
        holder.binding.comment = mListData[position]
        holder.binding.tvCommentBody.setOnClickListener{
            listener?.invoke(it,mListData[position], position)
        }
    }
}