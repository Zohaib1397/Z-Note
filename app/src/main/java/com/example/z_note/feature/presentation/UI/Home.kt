package com.example.z_note.feature.presentation.UI

import android.app.Application
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.feature.domain.model.Note.Companion.noteColors
import com.example.z_note.feature.presentation.States.NoteState
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.example.z_note.feature.presentation.notes.NoteViewModelFactory
import com.example.z_note.ui.theme.ZNoteTheme

@Composable
fun Home(
    modifier:Modifier = Modifier
){
    val context = LocalContext.current
    val viewModel: NoteViewModel = viewModel(
        factory = NoteViewModelFactory(context.applicationContext as Application)
    )

    viewModel.addNote(
        Note(
            0,
            "Sample",
            "this is an example text",
            1,
            false
        )
    )
    Scaffold(
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if(viewModel.newNoteState == NoteState.Collapsed) FloatingActionButton(onClick = viewModel::onNewNoteStateChange){}
                               },
        modifier= modifier
    ) {
        NoteScreen(viewModel = viewModel)
    }
}

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