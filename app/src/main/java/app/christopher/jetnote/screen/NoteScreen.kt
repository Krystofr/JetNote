package app.christopher.jetnote.screen

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.christopher.jetnote.R
import app.christopher.jetnote.components.NoteButton
import app.christopher.jetnote.components.NoteInputText
import app.christopher.jetnote.model.Note

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){

    val context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    Column(Modifier.padding(16.dp)) {
        TopAppBar(title = {
            Modifier.fillMaxWidth()
                          Text(text = stringResource(id = R.string.app_name), color = Color.White)
        }, actions = {
            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "App Bar icon", tint = Color.White)
        },
        backgroundColor = Color(0xFF9979E2))

        //Content
        Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            //Call NoteInputText composable
            NoteInputText(text = title, label = "Title", onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                }) title = it
            })
            Spacer(modifier = Modifier.size(20.dp))
            NoteInputText(text = description, label = "Add note", onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) description = it
            })
            Spacer(modifier = Modifier.size(20.dp))
            NoteButton(text = "Save Note", onClick = {
                when {
                    TextUtils.isEmpty(title) -> {
                        Toast.makeText(context, "Enter a title", Toast.LENGTH_SHORT).show()
                    }
                    TextUtils.isEmpty(description) -> {
                        Toast.makeText(context, "Enter a description", Toast.LENGTH_SHORT).show()
                    }
                    else -> //Save note to list
                        Toast.makeText(context, "Enter a description", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
}
