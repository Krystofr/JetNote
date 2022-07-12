package app.christopher.jetnote.util

import androidx.room.TypeConverter
import java.util.*

//This Converter class is not necessary as we are letting Room autoGenerate our id for us as PrimaryKey.
//Will be useful if we're not auto generating our id
class UUIDConverter {

    @TypeConverter
    fun fromUUID(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun uuidFromString(string: String?): UUID? = UUID.fromString(string)
}