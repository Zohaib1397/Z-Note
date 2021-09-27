package com.example.z_note.feature.presentation.UI

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.z_note.ui.theme.ZNoteTheme


/*
 Note State (Expanded / Collapsed)

* To determine the state of note following enum class
* contains two states
* if note is expanded the state will be NoteState.Expanded
* and if note is not expanded the state will be NoteState.Collapsed
* */
private enum class NoteState{
    Collapsed,
    Expanded
}

/*
* Note Card
*
* following NoteCard Composable function contains only states
* 1) the Expanded State for a note
* 2) Little Arrow head rotation state based on the expanding state of
*    note
*
* This NoteCard calls another Composable which accepts the parameters of states
* and onChangeState is to change the current state to either Expanded or Collapsed
* -- here Lambda functions are useful
* */


@Composable
fun NoteCard() {
    var noteState by remember{ mutableStateOf(NoteState.Collapsed)}
    val transition = updateTransition(targetState = noteState,label = "Expanded State")
    val arrowRotate by transition.animateFloat(
        label = "Arrow Rotation",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy
            )
        }
    ) { state ->
        when(state){
            NoteState.Collapsed -> 0f
            NoteState.Expanded -> 180f
        }

    }
    NoteCardView(
        noteState,
        arrowRotate
    ){
        noteState = when(noteState){
            NoteState.Collapsed -> NoteState.Expanded
            NoteState.Expanded -> NoteState.Collapsed
        }
    }
}

/*
* Following Composable just contains the view
*
* NoteCardView is a background Card having the elevation and RoundedShape
* */

@Composable
private fun NoteCardView(
    noteState: NoteState,
    arrowRotate: Float,
    modifier:Modifier = Modifier,
    onNoteStateChange:() -> Unit
) {
    Card(
        modifier = modifier.clickable{
            onNoteStateChange()
        },
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp
    ) {
        NoteColumnView(
            noteState,
            arrowRotate,
        ){
            onNoteStateChange()
        }
    }
}

@Composable
private fun NoteColumnView(
    noteState: NoteState,
    arrowRotate: Float,
    function: () -> Unit
) {

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