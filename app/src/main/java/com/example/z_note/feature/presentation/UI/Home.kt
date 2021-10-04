package com.example.z_note.feature.presentation.UI

import android.app.Application
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.feature.domain.model.Note.Companion.noteColors
import com.example.z_note.feature.presentation.AnimateNewNote
import com.example.z_note.feature.presentation.States.NoteState
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.example.z_note.feature.presentation.notes.NoteViewModelFactory
import com.example.z_note.ui.theme.ZNoteTheme

@ExperimentalAnimationApi
@Composable
fun Home(
    modifier:Modifier = Modifier
){
    val context = LocalContext.current
    val viewModel: NoteViewModel = viewModel(
        factory = NoteViewModelFactory(context.applicationContext as Application)
    )
    var customModifier = if(viewModel.newNoteState==NoteState.Collapsed) Modifier.padding(20.dp) else Modifier
    Scaffold(
        modifier= modifier
    ) {
        NoteScreen(viewModel = viewModel)
        Box(
            modifier = customModifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ){
            AnimateNewNote(addNoteTitle = "",addNoteContent = "",viewModel = viewModel,noteState = viewModel.newNoteState){
                FloatingActionButton(
                    onClick = {
                        viewModel.onNewNoteStateChange()
                    }
                ){
                    Icon(Icons.Outlined.Add, contentDescription = "Add New Note")
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true
)
@Preview("Light Mode")
@Composable
fun PreviewActivity(){
    ZNoteTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Home()
        }
    }
}