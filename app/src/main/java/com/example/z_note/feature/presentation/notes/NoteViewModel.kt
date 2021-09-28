package com.example.z_note.feature.presentation.notes

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.z_note.feature.data.data_source.NoteDatabase
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.feature.domain.repository.NoteRepository
import com.example.z_note.feature.presentation.States.LayoutState
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
// Search Bar text is remembered by this following variable
    var SearchBarText by  mutableStateOf("")
    fun onSearchBarTextChange(text:String){
        SearchBarText = text
    }


// Layout State is Defined in this variable
    var currentLayout by mutableStateOf(LayoutState.Linear_Layout)
    fun onLayoutChange(){
        when(currentLayout){
            LayoutState.Linear_Layout -> LayoutState.Grid_Layout
            LayoutState.Grid_Layout -> LayoutState.Linear_Layout
        }
    }

}

class NoteViewModelFactory(
    private val application:Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED CAST")
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}