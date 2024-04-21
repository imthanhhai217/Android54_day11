package com.devpro.android54_day11.objects.response


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int,
    @SerializedName("username")
    var username: String
)