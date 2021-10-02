package com.example.z_note.feature.presentation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.z_note.feature.presentation.UI.NoteScreen
import com.example.z_note.feature.presentation.notes.NoteViewModel

@Composable
fun checkOrientation(viewModel: NoteViewModel){
    when(LocalConfiguration.current.orientation){
        Configuration.ORIENTATION_LANDSCAPE -> NoteScreen(viewModel = viewModel)
        Configuration.ORIENTATION_PORTRAIT -> NoteScreen(viewModel)
    }
}