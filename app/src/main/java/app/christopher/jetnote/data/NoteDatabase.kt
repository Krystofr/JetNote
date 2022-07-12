package app.christopher.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.christopher.jetnote.model.Note
import app.christopher.jetnote.util.DateConverter
import app.christopher.jetnote.util.UUIDConverter

@TypeConverters(DateConverter::class)
@Database(entities = [Note::class], version = 4, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao
}