package app.christopher.jetnote.screen

import android.app.AlertDialog
import android.os.Build
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.christopher.jetnote.R
import app.christopher.jetnote.components.NoteButton
import app.christopher.jetnote.components.NoteInputText
import app.christopher.jetnote.data.NoteDataSource
import app.christopher.jetnote.model.Note
import app.christopher.jetnote.util.formatDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {

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
            Icon(imageVector = Icons.Rounded.Menu,
                contentDescription = "App Bar icon",
                tint = Color.White)
        },
            backgroundColor = Color(0xFF9979E2))

        //Content
        Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(120.dp))
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
                    else -> { //Save note to list
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note added below", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        Divider(Modifier.padding(15.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {
                    //Remove note when clicked
                    onRemoveNote(note)
                })
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit,
) {

    Surface(modifier
        .padding(14.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 10.dp) {
        Column(modifier
            .clickable {
                onNoteClicked(note)
            }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title,
                style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.dateCreated.time),
                style = MaterialTheme.typography.caption, color = MaterialTheme.colors.primary)


        }


    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}
