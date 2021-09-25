package com.example.z_note.feature.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.z_note.feature.domain.model.Note

@Database(entities = [Note::class],version = 1)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase?=null

        fun getDatabase(context: Context): NoteDatabase{
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_database"
            ).build().also {
                INSTANCE = it
            }
        }
    }

}