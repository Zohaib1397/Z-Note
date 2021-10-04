package com.example.z_note.feature.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
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
    AnimatedContent(
        targetState =  noteState,
        transitionSpec = {
            fadeIn(animationSpec = tween(150,150)) with
            fadeOut(animationSpec = tween(150)) using
                    SizeTransform{ initialSize, targetSize ->
                        if(targetState==NoteState.Expanded){
                            keyframes{
                                IntSize(targetSize.width,initialSize.height) at 150
                                durationMillis = 300
                            }
                        } else {
                            keyframes{
                                IntSize(initialSize.width,targetSize.height) at 150
                                durationMillis = 300
                            }
                        }
                    }
        }
    ){ targetState ->
        if(targetState == NoteState.Collapsed){
            callComposable()
        }else {
            AddNote(
                noteTitle = viewModel.noteTitle,
                noteContent = viewModel.noteContent,
                onNoteTitleChange = viewModel::onNoteTitleChange,
                onNoteContentChange = viewModel::onNoteContentChange,
                onNoteStateChange = viewModel::onNewNoteStateChange
            )
        }
    }
}