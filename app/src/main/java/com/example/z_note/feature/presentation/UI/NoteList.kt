package com.example.z_note.feature.presentation.UI

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.z_note.feature.presentation.AnimateNewNote
import com.example.z_note.feature.presentation.States.LayoutState
import com.example.z_note.feature.presentation.UI.GetColorOrIndex.Companion.getColor
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.puculek.pulltorefresh.PullToRefresh

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    viewModel: NoteViewModel,
) {
    val scrollState = rememberLazyListState()
    val ListOfNotes = viewModel.getAllNotes.observeAsState(listOf()).value
    viewModel.setNoteColors(ListOfNotes)
    val searchedNotes = viewModel.searchFromList(ListOfNotes)
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        if (viewModel.currentLayout == LayoutState.Linear_Layout) {
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
                        note = item,
                        currentNoteIndex = index,
                        onDeleteNote = viewModel::deleteNote,
                        viewModel = viewModel
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}
