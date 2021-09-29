package com.example.z_note.feature.presentation.UI

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.z_note.R
import com.example.z_note.feature.presentation.States.ColorRowState
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.example.z_note.ui.theme.ZNoteTheme

@ExperimentalAnimationApi
@Composable
fun AddNote(
    modifier: Modifier = Modifier,
    title:String,
    content:String,
    color: Color = MaterialTheme.colors.surface,
    viewModel: NoteViewModel
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        TopRow()
        ColorsRow(
            viewModel
        )
    }
}

/*This Composable is Placed on top of the AddNote menu

it Contains Cross Button and a Done Button by default and Other
buttons can be implemented on conditions

* */
@Composable
private fun TopRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(5.dp)
        ) {
            Icon(
                Icons.Rounded.Close,
                contentDescription = "Close Button"
            )
        }
        ButtonsRow()
    }
}
@Composable
private fun ButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ){
        IconButton(
            onClick = { /*Todo*/ },
            modifier = Modifier.padding(top = 5.dp)
        ) {
            Icon(
                Icons.Outlined.Notifications,
                contentDescription = "Share Button"
            )
        }
        IconButton(
            onClick = { /*Todo*/ },
            modifier = Modifier.padding(top = 5.dp)
        ) {
            Icon(
                Icons.Outlined.Share,
                contentDescription = "Share Button"
            )
        }
        Button(
            onClick = { /*Todo*/ },
            modifier = Modifier.padding(10.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Done")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ColorsRow(
    viewModel: NoteViewModel
) {
    AnimatedContent(
//        transitionSpec = {
//            tween(
//                durationMillis = 300,
//                easing = LinearOutSlowInEasing
//            )
//        },
        targetState = viewModel.colorRowState == ColorRowState.Expanded
    ){
        ColorButtonsRow()
    }
}
@Composable
fun ColorButtonsRow(){
    Row(
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedColorButton(onClick = {})
    }
}
@Composable
fun RoundedColorButton(
    onClick: () -> Unit,
    borderThickness: BorderStroke = BorderStroke(1.dp, Color.Gray),
    color: Color = Color.Unspecified,
    contentDescription: String = "White Color"
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filled_circle),
            contentDescription = contentDescription,
            tint = color,
            modifier = Modifier
                .border(
                    border = borderThickness,
                    shape = CircleShape
                )
                .size(35.dp)
        )
    }
}
@ExperimentalAnimationApi
@Preview
@Composable
fun ShowAddNotePreview() {
    ZNoteTheme {
        AddNote(
            title = "",
            content = "",
            viewModel = null!!
        )
    }
}