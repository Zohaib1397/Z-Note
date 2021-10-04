package com.example.z_note.feature.presentation.UI

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.z_note.R
import com.example.z_note.feature.presentation.notes.NoteViewModel
import com.example.z_note.ui.theme.ZNoteTheme

@ExperimentalAnimationApi
@Composable
fun AddNote(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.surface,
//    viewModel: NoteViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
    ) {
        TopRow()
//        ColorsRow(
//            viewModel
//        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField( // For Note Title
                placeholder = "Title",
                textStyle = TextStyle(fontSize = MaterialTheme.typography.h5.fontSize)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                placeholder = "Note",
                hasTrailingIcon = false,

            )
        }
    }
}

@Composable
private fun CustomTextField(
//    viewModel: NoteViewModel,
    placeholder: String,
    trailingIcon: ImageVector = Icons.Outlined.Close,
    trailingIconContent: String = "Clear Title",
    hasTrailingIcon: Boolean = true,
    textStyle: TextStyle = TextStyle(),
    backgroundColor: Color = MaterialTheme.colors.surface
    ) {
    TextField(
//                value = viewModel.noteTitle, TODO
//                onValueChange = viewModel::onNoteTitleChange TODO//
        value = "This is a Sample p12",
        onValueChange = { },
        placeholder = {
            Text(placeholder)
        },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.Gray,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        ),
        trailingIcon = {
//                    if(viewModel.noteTitle.isNotEmpty()){
            if(hasTrailingIcon){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(trailingIcon, contentDescription = "Clear Title")
                }
            }
//                }
        },
        shape = RoundedCornerShape(12.dp),
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { /*TODO*/}
        )
    )
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
    ) {
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
    ) {
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

//@ExperimentalAnimationApi
//@Composable
//fun ColorsRow(
//    viewModel: NoteViewModel
//) {
//    AnimatedContent(
////        transitionSpec = {
////            tween(
////                durationMillis = 300,
////                easing = LinearOutSlowInEasing
////            )
////        },
//        targetState = viewModel.colorRowState == ColorRowState.Expanded
//    ){
//        ColorButtonsRow()
//    }
//}
@Composable
fun ColorButtonsRow() {
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
@Preview(
    showBackground = true
)
@Composable
fun ShowAddNotePreview() {
    ZNoteTheme {
        AddNote(
//            viewModel = viewModel(
//                factory = NoteViewModelFactory(LocalContext.current.applicationContext as Application)
//            )
        )
    }
}