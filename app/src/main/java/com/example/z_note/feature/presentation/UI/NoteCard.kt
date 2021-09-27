package com.example.z_note.feature.presentation.UI

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.z_note.ui.theme.ZNoteTheme

private enum class NoteState{
    Collapsed,
    Expanded
}




@Composable
fun NoteCard() {
    var noteState by remember{ mutableStateOf(NoteState.Collapsed)}
    val transition = updateTransition(targetState = noteState)
    val arrowRotate by transition.animateFloat(
    ) { state ->
        when(state){
            NoteState.Collapsed -> 0f
            NoteState.Expanded -> 180f
        }

    }
}



@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true
)
@Preview("Light Mode")
@Composable
fun previewTodoCard() {
    ZNoteTheme {
        NoteCard()
    }
}