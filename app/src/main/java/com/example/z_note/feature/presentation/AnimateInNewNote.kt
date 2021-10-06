package com.example.z_note.feature.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import com.example.z_note.feature.presentation.States.NoteState
import com.example.z_note.feature.presentation.UI.AddNote
import com.example.z_note.feature.presentation.notes.NoteViewModel

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AnimateNewNote(
    addNoteTitle:String,
    addNoteContent:String,
    viewModel:NoteViewModel,
    noteState: NoteState,
    callComposable: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.animateContentSize(

        )
    ){
        if (noteState == NoteState.Collapsed) {
            callComposable()
        } else {
            AddNote(
                noteTitle = viewModel.noteTitle,
                noteContent = viewModel.noteContent,
                onNoteTitleChange = viewModel::onNoteTitleChange,
                onNoteContentChange = viewModel::onNoteContentChange,
                onNoteStateChange = viewModel::onNewNoteStateChange,
                onAddNote = viewModel::addNote,
            )
        }
    }
}