package com.example.z_note.feature.presentation.UI

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Refresh
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.z_note.R
import com.example.z_note.feature.domain.model.Note.Companion.noteColors
import com.example.z_note.feature.presentation.States.NoteState
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.example.z_note.ui.theme.ZNoteTheme


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


@SuppressLint("RememberReturnType")
@Composable
fun NoteCard(
    modifier:Modifier = Modifier,
    noteTitle:String,
    noteContent:String,
    getNoteColorFromIndex:Int,
    currentNoteIndex:Int,

) {
    val noteIndex by rememberSaveable(currentNoteIndex) {mutableStateOf(getNoteColorFromIndex)}
    val noteColor =noteColors[noteIndex] //this noteColors is from Entity's Class Companion Object
    var noteState by remember{ mutableStateOf(NoteState.Collapsed)}
    val transition = updateTransition(targetState = noteState,label = "Expanded State")
    val arrowRotate by transition.animateFloat(
        label = "Arrow Rotation"
    ) { state ->
        when(state){
            NoteState.Collapsed -> 0f
            NoteState.Expanded -> 180f
        }

    }
    NoteCardView(
        noteTitle,
        noteContent,
        noteColor,
        currentNoteIndex,
        noteState,
        arrowRotate,
        modifier
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
    noteTitle:String,
    noteContent: String,
    noteColor: Color,
    currentNoteIndex: Int,
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
            noteTitle,
            noteContent,
            noteColor,
            currentNoteIndex,
            noteState,
            arrowRotate,
            onNoteStateChange
        )
    }
}
/*
* NoteColumnView Composable is the layout of
* 1) Note Title
* 2) Note Content ----- Arrow Head
* 3) Expandable View Contains buttons
* */
@Composable
private fun NoteColumnView(
    noteTitle: String,
    noteContent: String,
    noteColor:Color,
    currentNoteIndex: Int,
    noteState: NoteState,
    arrowRotate: Float,
    onNoteStateChange: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = noteColor)
            .animateContentSize()
    ){
        Text(
            text = noteTitle,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(start = 20.dp,top = 12.dp)
        )
        //This Composable contains NoteContent and Arrow
        NoteExpandableRowView(
            noteContent,
            arrowRotate,
            onNoteStateChange
        )
        //This Composable is the expanded view
        ExpandedState(
            noteTitle,
            noteContent,
            currentNoteIndex,
            noteState,
        )
    }
}
/*
* This Composable is The Row of
*  Note Content and Arrow head which is rotatable
* */
@Composable
private fun NoteExpandableRowView(
    noteContent: String,
    arrowRotate: Float,
    onNoteStateChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = noteContent,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 12.dp)
                .fillMaxWidth(0.8f)
        )
        IconButton(onClick = {
            onNoteStateChange
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "DropDown",
                modifier = Modifier.rotate(arrowRotate)
            )
        }
    }
}

@Composable
private fun ExpandedState(
    noteTitle: String,
    noteContent: String,
    currentNoteIndex: Int,
    noteState: NoteState
) {
    if(noteState == NoteState.Expanded){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "Edit Todo"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Delete Todo"
                )
            }
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
        NoteCard(
            noteTitle = "Note Title",
            noteContent = "This is a sample note",
            getNoteColorFromIndex = 1,
            currentNoteIndex = 0,
        )
    }
}