package app.christopher.jetnote.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import app.christopher.jetnote.data.NoteDataSource
import app.christopher.jetnote.model.Note

class NoteViewModel : ViewModel() {

    val noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes() : List<Note> = noteList
}