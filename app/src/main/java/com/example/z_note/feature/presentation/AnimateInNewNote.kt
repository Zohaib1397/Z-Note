package com.example.z_note.feature.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize
import com.example.z_note.feature.presentation.States.NoteState
import com.example.z_note.feature.presentation.UI.AddNote
import com.example.z_note.feature.presentation.notes.NoteViewModel

@ExperimentalAnimationApi
@Composable
fun AnimateNewNote(
    addNoteTitle:String,
    addNoteContent:String,
    viewModel:NoteViewModel,
    noteState: NoteState,
    releventComposable: @Composable () -> Unit
) {
    AnimatedContent(
        targetState =  noteState,
        transitionSpec = {
            fadeIn() with
            fadeOut() using
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
            releventComposable()
        }else {
            AddNote(
                title = addNoteTitle,
                content = addNoteContent,
                viewModel = viewModel
            )
        }
    }
}