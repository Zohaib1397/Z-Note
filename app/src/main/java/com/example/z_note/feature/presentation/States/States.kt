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
/*
* Layout State (Linear / Grid)
*
* To Switch between Layout Views in the App following State
* contains two States
* */
enum class LayoutState{
    Linear_Layout,
    Grid_Layout
}

/*
* Color Row State is the button in AddNote screen
* to get Colors
* */
enum class ColorRowState{
    Collapsed,
    Expanded
}