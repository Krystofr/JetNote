package app.christopher.jetnote.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.christopher.jetnote.data.NoteDataSource
import app.christopher.jetnote.model.Note
import app.christopher.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.sql.RowId
import javax.inject.Inject

private const val TAG = "NoteViewModel"
@HiltViewModel
class NoteViewModel @Inject constructor (private val noteRepository: NoteRepository) : ViewModel() {

    private val _noteList : MutableStateFlow<List<Note>> by lazy { MutableStateFlow(emptyList()) }
    val noteList = _noteList.asStateFlow()

    init {
        //noteList.addAll(NoteDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes ->
                    if (listOfNotes.isEmpty()) {
                        Log.d(TAG, "List is empty")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
        //getAllNotes()
    }

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.addNote(note)
        //noteList.add(note)
    }

    fun removeNote(note: Note) = viewModelScope.launch{
        noteRepository.deleteNote(note)
        //noteList.remove(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch{
        noteRepository.updateNote(note)
    }
    fun deleteAllNotes() = viewModelScope.launch{
        noteRepository.deleteAllNotes()
    }

    fun getNoteById(noteId: Int) = viewModelScope.launch{
        noteRepository.getNoteById(noteId)
    }

    //fun getAllNotes() : Flow<List<Note>> = noteRepository.getAllNotes()
}