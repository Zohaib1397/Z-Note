package com.example.z_note.feature.presentation.notes

import android.app.Application
import android.graphics.Color
import androidx.compose.material.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.lifecycle.*
import com.example.z_note.feature.data.data_source.NoteDatabase
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.feature.domain.model.Note.Companion.noteColors
import com.example.z_note.feature.domain.repository.NoteRepository
import com.example.z_note.feature.presentation.States.ColorRowState
import com.example.z_note.feature.presentation.States.LayoutState
import com.example.z_note.feature.presentation.States.NoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val getAllNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init{
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        getAllNotes = repository.getAllNotes()
    }

    fun addNote(note:Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNote(note)
        }
    }

    var noteBackgroundColor = mutableListOf<androidx.compose.ui.graphics.Color>()
    fun setNoteColors(notes: List<Note>){
        for(item in notes){
            noteBackgroundColor.add(noteColors[item.color])
        }
    }

// /*
// Default Note Title And Content Text
// is described in this following State
// */
    var noteTitle by mutableStateOf("")
    var noteContent by mutableStateOf("")
    fun onNoteTitleChange(title:String){
        noteTitle = title
    }
    fun onNoteContentChange(content:String){
        noteContent = content
    }

// Search Bar text is remembered by this following variable
    var SearchBarText by  mutableStateOf("")
    fun onSearchBarTextChange(text:String){
        SearchBarText = text
    }


// Layout State is Defined in this variable
    var currentLayout by mutableStateOf(LayoutState.Linear_Layout)
    fun onLayoutChange(){
        currentLayout =when(currentLayout){
            LayoutState.Linear_Layout -> LayoutState.Grid_Layout
            LayoutState.Grid_Layout -> LayoutState.Linear_Layout
        }
    }

// Searched List items
    val searchedItemsList = mutableListOf<Note>()
    fun addInSearchList(note:Note){
        searchedItemsList.add(note)
    }

// Search Items list logic
    fun searchFromList(notes: List<Note>): MutableList<Note> {
        for(item in notes){
            if(SearchBarText.lowercase() == item.text.lowercase() || SearchBarText.lowercase() == item.title.lowercase()){
                addInSearchList(item)
            }
        }
    return searchedItemsList
    }
// Default Color Row State
    var colorRowState by mutableStateOf(ColorRowState.Collapsed)
    fun onColorRowStateChange(){
        colorRowState = when(colorRowState){
            ColorRowState.Collapsed -> ColorRowState.Expanded
            ColorRowState.Expanded -> ColorRowState.Collapsed
        }
    }

// New Note Expanded State
    var newNoteState by mutableStateOf(NoteState.Collapsed)
    fun onNewNoteStateChange(){
        newNoteState = when(newNoteState){
            NoteState.Collapsed -> NoteState.Expanded
            NoteState.Expanded -> NoteState.Collapsed
        }
    }



}


class NoteViewModelFactory(
    private val application:Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED CAST")
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}