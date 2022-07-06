package app.christopher.jetnote.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.christopher.jetnote.R
import app.christopher.jetnote.components.NoteInputText

@Composable
fun NoteScreen() {

    Column(Modifier.padding(16.dp)) {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name), color = Color.White)
        }, actions = {
            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "App Bar icon")
        },
        backgroundColor = Color(0xFF9979E2))
    }

    
    //Content
    Column(Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        //Call NoteInputText composable
        NoteInputText(text = "Note App", label = "Label here", onTextChange = {})
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen()
}
