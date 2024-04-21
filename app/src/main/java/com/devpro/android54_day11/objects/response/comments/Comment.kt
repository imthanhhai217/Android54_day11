package com.devpro.android54_day11.objects.response.comments


import com.devpro.android54_day11.objects.response.User
import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("body")
    var body: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("postId")
    var postId: Int,
    @SerializedName("user")
    var user: User
)