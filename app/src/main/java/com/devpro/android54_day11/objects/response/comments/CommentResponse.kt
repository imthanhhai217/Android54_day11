package com.devpro.android54_day11.objects.response.comments


import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("comments")
    var comments: List<Comment>,
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("skip")
    var skip: Int,
    @SerializedName("total")
    var total: Int
)