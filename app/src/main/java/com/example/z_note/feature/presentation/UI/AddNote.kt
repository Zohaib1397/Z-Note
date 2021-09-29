package com.example.z_note.feature.presentation.UI

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.z_note.ui.theme.ZNoteTheme

@Composable
fun AddNote(
    modifier: Modifier = Modifier,
    title:String,
    content:String,
    color: Color = MaterialTheme.colors.surface
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        TopRow()
    }
}

@Composable
private fun TopRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(onClick = { /*TODO*/ }) {

        }
    }
}
@Preview
@Composable
fun ShowAddNotePreview() {
    ZNoteTheme {
        AddNote(
            title = "",
            content = ""
        )
    }
}