package com.example.z_note.feature.presentation.UI

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.z_note.R
import com.example.z_note.feature.domain.model.Note
import com.example.z_note.ui.theme.ZNoteTheme

private enum class ColorRowState{
    Collapsed,
    Expanded
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AddNote(
    noteTitle:String,
    noteContent:String,
    onNoteTitleChange: (String) -> Unit,
    onNoteContentChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.surface,
    onNoteStateChange: () -> Unit,
    onAddNote: (Note) -> Unit
) {
    var colorRowState by remember{mutableStateOf(ColorRowState.Collapsed)}
//    var textFieldEnabled by remember{mutableStateOf(false)}
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
    ) {
        TopRow(
            onNoteStateChange,
            onColorRowStateChange = {
                colorRowState = when(colorRowState){
                    ColorRowState.Expanded -> ColorRowState.Collapsed
                    ColorRowState.Collapsed -> ColorRowState.Expanded
                }
            }
        )
        AnimatedVisibility(colorRowState == ColorRowState.Expanded){
            ColorButtonsRow()
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField( // For Note Title
                noteText = noteTitle,
                onNoteTextChange = {onNoteTitleChange(it)},
                maxLines = 1,
                maxLength = 20,
                imeAction = ImeAction.Next,
                placeholder = "Title",
                textStyle = TextStyle(fontSize = MaterialTheme.typography.h6.fontSize)
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomTextField(
                noteText = noteContent,
                onNoteTextChange = {onNoteContentChange(it)},
                maxLines = 40,
                maxLength = 10000,
                imeAction = ImeAction.Done,
                placeholder = "Note",
                modifier = Modifier
                    .fillMaxHeight(0.9f),
                hasTrailingIcon = false,
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun CustomTextField(
    noteText:String,
    onNoteTextChange:(String) -> Unit,
    placeholder: String,
    maxLines:Int,
    maxLength:Int,
    imeAction: ImeAction,
    trailingIcon: ImageVector = Icons.Outlined.Close,
    trailingIconContent: String = "Clear Title",
    hasTrailingIcon: Boolean = true,
    textStyle: TextStyle = TextStyle(),
    backgroundColor: Color = MaterialTheme.colors.surface,
    modifier:Modifier = Modifier
    ) {
    var keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.padding(start = 30.dp,end = 30.dp)
    ){
        Surface(
            elevation = 2.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            TextField(
                value = noteText,
                onValueChange = {
                    if(it.length <= maxLength) onNoteTextChange(it)
                },
                placeholder = {
                    Text(placeholder,fontSize = textStyle.fontSize)
                },
                modifier = modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = backgroundColor,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                trailingIcon = {
                    if(noteText.isNotEmpty()){
                        if (hasTrailingIcon) {
                            IconButton(onClick = { onNoteTextChange("")}) {
                                Icon(
                                    trailingIcon,
                                    contentDescription = trailingIconContent,
                                    tint = if(noteText.length == maxLength)
                                        MaterialTheme.colors.error else MaterialTheme.colors.onSurface
                                    )
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                textStyle = textStyle,
                keyboardOptions = KeyboardOptions(
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /*TODO*/ },
                    onDone = { keyboardController!!.hide() }
                ),
                maxLines = maxLines
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            if(noteText.length == maxLength){
                Text("Reached max limit",color = MaterialTheme.colors.error)
            }else{
                Spacer(modifier= Modifier)
            }
            Text("${noteText.length}/$maxLength",
                color = if(noteText.length == maxLength)
                    MaterialTheme.colors.error else Color.Unspecified
            )
        }
    }
}

/*This Composable is Placed on top of the AddNote menu

it Contains Cross Button and a Done Button by default and Other
buttons can be implemented on conditions

* */
@Composable
private fun TopRow(
    onNoteStateChange: () -> Unit,
    onColorRowStateChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onNoteStateChange,
            modifier = Modifier.padding(5.dp)
        ) {
            Icon(
                Icons.Rounded.Close,
                contentDescription = "Close Button"
            )
        }
        ButtonsRow(
            onColorRowStateChange
        )
    }
}

@Composable
private fun ButtonsRow(
    onColorRowStateChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = onColorRowStateChange,
            modifier = Modifier.padding(top = 5.dp)
        ) {
            Icon(
                painterResource(R.drawable.ic_color_lens),
                contentDescription = "Colors Button"
            )
        }
        IconButton(
            onClick = { /*Todo*/ },
            modifier = Modifier.padding(top = 5.dp)
        ) {
            Icon(
                Icons.Outlined.Notifications,
                contentDescription = "Set Reminder"
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
private fun ColorsRow(
    colorRowState: ColorRowState
) {
    if(colorRowState == ColorRowState.Expanded){
        AnimatedContent(
//        transitionSpec = {
//            tween(
//                durationMillis = 300,
//                easing = LinearOutSlowInEasing
//            )
//        },
            targetState = colorRowState == ColorRowState.Expanded
        ) {
            ColorButtonsRow()
        }
    }
}
@Composable
fun ColorButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedColorButton(onClick = {})
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

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(
    showBackground = true
)
@Composable
fun ShowAddNotePreview() {
    ZNoteTheme {
        AddNote(
            noteTitle = "",
            noteContent = "",
            onNoteTitleChange = {},
            onNoteContentChange = {},
            onNoteStateChange = {},
            onAddNote = fun (Note){}
        )
    }
}