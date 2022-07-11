package app.christopher.jetnote.data

import androidx.room.*
import app.christopher.jetnote.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM notes_table")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id =:id")
    suspend fun getNoteById(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun removeNote(note: Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()
}