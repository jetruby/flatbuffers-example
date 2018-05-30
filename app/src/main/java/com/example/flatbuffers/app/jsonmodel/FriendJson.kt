package com.example.flatbuffers.app.jsonmodel

import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by Ivan Golovin on 29.05.18.
 */
@JsonObject
data class FriendJson(
    val id: Long = Long.MIN_VALUE,
    val name: String = ""
)
