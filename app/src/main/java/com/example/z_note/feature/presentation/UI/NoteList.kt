package com.example.z_note.feature.presentation.UI

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.z_note.feature.presentation.States.LayoutState
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.puculek.pulltorefresh.PullToRefresh

@Composable
fun NoteScreen(
    viewModel: NoteViewModel,
) {
    val scrollState = rememberLazyListState()
    val ListOfNotes = viewModel.getAllNotes.observeAsState(listOf()).value
    viewModel.setNoteColors(ListOfNotes)
    val searchedNotes = viewModel.searchFromList(ListOfNotes)
    if(viewModel.currentLayout== LayoutState.Linear_Layout){
        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            state = scrollState
        ) {
            item {
                SearchBar(
                    searchBarText = viewModel.SearchBarText,
                    currentLayoutState = viewModel.currentLayout,
                    onSearchBarTextChange = viewModel::onSearchBarTextChange,
                    onLayoutChange = viewModel::onLayoutChange
                )
            }
            itemsIndexed(
                if (viewModel.SearchBarText.isEmpty()) ListOfNotes else searchedNotes
            ) { index, item ->
                Spacer(modifier = Modifier.height(15.dp))
                NoteCard(
                    noteTitle = ListOfNotes[index].title,
                    noteContent = ListOfNotes[index].text,
                    noteColor = viewModel.getNoteColor(index),
                    getNoteColorFromIndex = ListOfNotes[index].color,
                    currentNoteIndex = index
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
}
