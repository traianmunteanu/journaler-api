package com.journaler.controller

import com.journaler.data.Note
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/notes")
@EnableAutoConfiguration
class NoteController {

    /*
    * Get notes
    * */
    @GetMapping(
            value = ["/obtain"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getNotes(): List<Note> {
        return listOf(
                Note(
                        UUID.randomUUID().toString(),
                        "My First Note",
                        "This is a message for the 1st note."
                ),
                Note(
                        UUID.randomUUID().toString(),
                        "My Second Note",
                        "This is a message for the 2nd note."
                )
        )
    }

    /*
    * Insert note.
    * It consumes JSON, that is: request body Note.
    * */
    @RequestMapping(
            value = ["/insert"],
            method = [RequestMethod.POST],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insertNote(@RequestBody note: Note) : Note {
        note.id = UUID.randomUUID().toString()
        return note
    }

    /*
    * Update item.
    * It consumes JSON, that is: request body Note.
    * As result it returns updated Note
    * */
    @RequestMapping(
            value= ["/update"],
            method= [RequestMethod.PUT],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateNote(@RequestBody note: Note): Note {
        note.title += " [ updated ]"
        note.message += " [ updated ]"
        return note
    }

    /*
    * Remove note by Id.
    * We introduced path variable for Id to pass.
    * */
    @DeleteMapping(
            value = ["/delete/{id}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun deleteNote(@PathVariable(name = "id") id: String): Boolean {
        println("Removing: $id")
        return true
    }
}