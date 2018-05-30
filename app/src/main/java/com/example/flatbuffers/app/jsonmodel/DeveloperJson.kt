package com.example.flatbuffers.app.jsonmodel

import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by Ivan Golovin on 29.05.18.
 */
@JsonObject
data class DeveloperJson(
    val id: Int = Int.MIN_VALUE,
    val first_name: String = "",
    val last_name: String = "",
    val age: Int = Int.MIN_VALUE,
    val gender: String = "",
    val email: String = "",
    val skill: String = "",
    val friends: List<FriendJson> = emptyList()
)