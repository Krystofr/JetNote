package app.christopher.jetnote.model

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.util.*

@SuppressLint("NewApi")
data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String? = null,
    val description: String? = null,
    val dateCreated: LocalDateTime = LocalDateTime.now()

)
