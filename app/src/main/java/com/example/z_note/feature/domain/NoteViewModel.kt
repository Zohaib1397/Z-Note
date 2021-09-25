package com.example.z_note.feature.domain

import android.app.Application
import androidx.lifecycle.*
import com.example.z_note.feature.data.data_source.NoteDatabase
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.feature.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val getAllNotes: LiveData<List<Note>>
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