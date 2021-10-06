package com.example.z_note.feature.presentation.UI

import android.annotation.SuppressLint
import android.app.Application
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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.z_note.R
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.feature.domain.model.Note.Companion.noteColors
import com.example.z_note.feature.presentation.States.NoteState
import com.example.z_note.feature.presentation.UI.GetColorOrIndex.Companion.getColor
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.example.z_note.feature.presentation.notes.NoteViewModelFactory
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
    note:Note,
    modifier:Modifier = Modifier,
    currentNoteIndex:Int,
    onDeleteNote:(Note) -> Unit
) {
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
        note = note,
        currentNoteIndex,
        noteState,
        arrowRotate,
        modifier,
        onDeleteNote
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
    note:Note,
    currentNoteIndex: Int,
    noteState: NoteState,
    arrowRotate: Float,
    modifier:Modifier = Modifier,
    onDeleteNote: (Note) -> Unit,
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
            note = note,
            currentNoteIndex,
            noteState,
            arrowRotate,
            onNoteStateChange,
            onDeleteNote
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
    note:Note,
    currentNoteIndex: Int,
    noteState: NoteState,
    arrowRotate: Float,
    onNoteStateChange: () -> Unit,
    onDeleteNote: (Note) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = getColor(note.color))
            .animateContentSize()
    ){
        Text(
            text = note.title,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(start = 20.dp,top = 12.dp)
        )
        //This Composable contains NoteContent and Arrow
        NoteExpandableRowView(
            note,
            arrowRotate,
            onNoteStateChange
        )
        //This Composable is the expanded view
        ExpandedState(
            note,
            currentNoteIndex,
            noteState,
            onNoteStateChange,
            onDeleteNote
        )
    }
}
/*
* This Composable is The Row of
*  Note Content and Arrow head which is rotatable
* */
@Composable
private fun NoteExpandableRowView(
    note:Note,
    arrowRotate: Float,
    onNoteStateChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = note.text,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 12.dp)
                .fillMaxWidth(0.8f)
        )
        IconButton(onClick =
            onNoteStateChange
        ) {
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
    note:Note,
    currentNoteIndex: Int,
    noteState: NoteState,
    onNoteStateChange: () -> Unit,
    onDeleteNote: (Note) -> Unit
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
            IconButton(onClick = {
                onDeleteNote(note)
                onNoteStateChange()
            }
            ) {
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
            note = Note(0,"Note Title","This is a sample note",1,false),
            currentNoteIndex = 0,
            onDeleteNote = fun (Note){}
        )
    }
}