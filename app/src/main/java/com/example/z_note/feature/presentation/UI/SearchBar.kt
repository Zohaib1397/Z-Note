package com.example.z_note.feature.presentation.UI

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.z_note.R
import com.example.z_note.feature.presentation.States.LayoutState
import com.example.z_note.ui.theme.ZNoteTheme

@Composable
fun SearchBar(
    modifier:Modifier = Modifier,
    placeholder:String = "Search",
    searchBarText:String,
    leadingIcon: Painter = painterResource(id = R.drawable.ic_search),
    currentLayoutState:LayoutState,
    onSearchBarTextChange: (String) -> Unit,
    onLayoutChange: () -> Unit
) {
    TextField(
        value = searchBarText,
        onValueChange = onSearchBarTextChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(placeholder)
        },

        shape = RoundedCornerShape(30.dp),
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(15.dp),
                painter = leadingIcon,
                contentDescription = placeholder
            )
        },

        trailingIcon = {
            if(searchBarText.isEmpty()){
                IconButton(
                    onClick = onLayoutChange,
                    modifier = Modifier.padding(end = 15.dp),
                ) {
                    Icon(
                        painter = if (currentLayoutState == LayoutState.Linear_Layout)
                            painterResource(
                                R.drawable.ic_linear_view
                            ) else painterResource(
                            id = R.drawable.ic_grid_view
                        ),
                        contentDescription = if (currentLayoutState == LayoutState.Linear_Layout) "Linear View" else "Gird View",
                    )
                }
            }
        }
    )
}
@Preview(
    name ="Search Bar",
//    showBackground = true
)
@Composable
fun PreviewSearchBar() {
    ZNoteTheme {
        SearchBar(
            currentLayoutState = LayoutState.Linear_Layout,
            searchBarText = "",
            onSearchBarTextChange = {}
        ){}
    }
}