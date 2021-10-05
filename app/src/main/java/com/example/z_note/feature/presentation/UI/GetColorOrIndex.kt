package com.example.z_note.feature.presentation.UI

import androidx.compose.ui.graphics.Color
import com.example.z_note.feature.domain.model.Note.Companion.noteColors

class GetColorOrIndex{
    companion object{
        fun getColor(index:Int): Color {
            return noteColors[index]
        }
        fun getIndex(givenColor:androidx.compose.ui.graphics.Color): Int {
            noteColors.forEachIndexed { index, color ->
                if(givenColor == color){
                    return index
                }
            }
            return 0
        }
    }
}