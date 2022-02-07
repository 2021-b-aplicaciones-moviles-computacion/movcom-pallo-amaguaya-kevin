package com.example.appsmoviles

class IPostHtto(
    val id :Int,
    var userId: Any,
    val title: String,
    val body: String
) {
    init {
        if (userId is String)
            userId = (userId as String).toInt()
        if (userId is Int)
            userId = userId
    }
}