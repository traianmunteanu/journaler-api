package com.journaler.controller

import com.journaler.data.Todo
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.awt.PageAttributes
import java.util.*

@RestController
@RequestMapping("/todos")
@EnableAutoConfiguration
class TodoController {
    @GetMapping(
            value = ["/obtain"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTodos(): List<Todo> {
        return listOf(
                Todo(
                    UUID.randomUUID().toString(),
                    "My First Todo",
                        "This",
                        System.currentTimeMillis()
                ),
                Todo(
                        UUID.randomUUID().toString(),
                        "My Second Todo",
                        "That",
                        System.currentTimeMillis()
                )
        )
    }

    @RequestMapping(
            value = ["/insert"],
            method = [RequestMethod.POST],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insertTodo(@RequestBody todo: Todo): Todo {
        todo.id = UUID.randomUUID().toString()
        todo.schedule = System.currentTimeMillis()
        return todo
    }

    @PutMapping(
            value = ["/update"],
//            method = [RequestMethod.PUT],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateTodo(@RequestBody todo: Todo): Todo {
        todo.title += " [ updated ]"
        todo.message += " [ updated ]"
        todo.schedule = System.currentTimeMillis()
        return todo
    }

    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun deleteTodo(@PathVariable(name = "id") id: String) : Boolean {
        println("Removing $id")
        return true
    }


}