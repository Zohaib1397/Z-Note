package com.example.z_note.feature.presentation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.z_note.feature.presentation.States.LayoutState
import com.example.z_note.feature.presentation.UI.NoteCard
import com.example.z_note.feature.presentation.UI.SearchBar
import com.example.z_note.ui.theme.ZNoteTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true
)
@Preview("Light Mode")
@Composable
fun PreviewTodoCard() {
    ZNoteTheme {
        NoteCard(
            noteTitle = "Note Title",
            noteContent = "This is a sample note",
            getNoteColorFromIndex = 1,
            currentNoteIndex = 0,
        )
    }
}

@Composable
@Preview("Light Mode")
fun PreviewLayoutSwitcher() {
    ZNoteTheme {
        SearchBar(
            currentLayoutState = LayoutState.Linear_Layout,
            searchBarText = "",
            onSearchBarTextChange = {}
        ){}
    }
}