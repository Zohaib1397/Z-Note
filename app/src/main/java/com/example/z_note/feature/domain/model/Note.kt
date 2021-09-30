package com.example.z_note.feature.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.z_note.ui.theme.*

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "NoteTitle")
    val title:String,
    @ColumnInfo(name = "NoteText")
    val text:String,
    @ColumnInfo(name = "NoteColor")
    val color:Int,
    @ColumnInfo(name = "IsTodo")
    val isTodo:Boolean
){
    companion object{
        val noteColors = listOf(
            Color.White,
            Yellow300,
            Green300,
            Red300,
            Blue300,
            Magenta300,
        )
    }
}