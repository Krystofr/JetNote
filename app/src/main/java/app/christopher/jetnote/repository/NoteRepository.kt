package app.christopher.jetnote.repository

import app.christopher.jetnote.data.NoteDatabaseDao
import app.christopher.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note)

    suspend fun deleteNote(note: Note) = noteDatabaseDao.removeNote(note)

    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAllNotes()

    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)

    fun getAllNotes() : Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

    suspend fun getNoteById(noteId: Int) = noteDatabaseDao.getNoteById(noteId)
}