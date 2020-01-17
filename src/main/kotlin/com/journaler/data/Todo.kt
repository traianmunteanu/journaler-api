package com.journaler.data

data class Todo(
        var id: String = "",
        var title: String,
        var message: String,
        var schedule: Long,
        val location: String = ""
) {

}