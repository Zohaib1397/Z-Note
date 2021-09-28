package com.example.z_note.feature.presentation.UI

import android.app.Application
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
    Scaffold(
        modifier= modifier
    ) {
        Column {
            SearchBar(
                searchBarText = viewModel.SearchBarText,
                currentLayoutState = viewModel.currentLayout,
                onSearchBarTextChange = viewModel::onSearchBarTextChange,
                onLayoutChange = viewModel::onLayoutChange
            )
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true,
)
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