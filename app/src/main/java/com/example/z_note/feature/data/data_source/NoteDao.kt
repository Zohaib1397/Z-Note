package com.example.z_note.feature.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.z_note.feature.domain.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)


}