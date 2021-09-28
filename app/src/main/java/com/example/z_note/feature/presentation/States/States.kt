package com.example.z_note.feature.presentation.States

/*
 Note State (Expanded / Collapsed)

* To determine the state of note following enum class
* contains two states
* if note is expanded the state will be NoteState.Expanded
* and if note is not expanded the state will be NoteState.Collapsed
* */
enum class NoteState{
    Collapsed,
    Expanded
}

enum class LayoutState{
    Linear_Layout,
    Grid_Layout
}