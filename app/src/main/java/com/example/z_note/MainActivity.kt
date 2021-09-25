package com.example.z_note

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.z_note.feature.domain.NoteViewModel
import com.example.z_note.feature.domain.NoteViewModelFactory
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.ui.theme.ZNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZNoteTheme {
                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
                    CallDatabase()
//                }
            }
        }
    }
}

@Composable
fun CallDatabase() {
    val context = LocalContext.current
    val NoteViewModel: NoteViewModel = viewModel(
        factory = NoteViewModelFactory(context.applicationContext as Application))
    NoteViewModel.addNote(Note(0,"Sample","This is a test"))
}