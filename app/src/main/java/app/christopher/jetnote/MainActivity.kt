package app.christopher.jetnote

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.christopher.jetnote.data.NoteDataSource
import app.christopher.jetnote.model.Note
import app.christopher.jetnote.screen.NoteScreen
import app.christopher.jetnote.screen.NoteViewModel
import app.christopher.jetnote.ui.theme.JetNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    //val noteViewModel = viewModel<NoteViewModel>() //Also works
                    val noteViewModel: NoteViewModel by viewModels()
                  NoteApp(noteViewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteApp(noteViewModel: NoteViewModel) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    val noteList = noteViewModel.noteList.collectAsState().value
    NoteScreen(notes = noteList,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            openDialog.value = true
            if (openDialog.value) {
                val dialog = AlertDialog.Builder(context)
                dialog.setMessage("Delete this note?")
                    .setCancelable(false)
                    .setPositiveButton("YES") { _, _ ->
                        //Delete note from db
                        noteViewModel.removeNote(it)
                        Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                    }.setNegativeButton("CANCEL") { action, _ ->
                        action.cancel()
                        openDialog.value = false
                    }.show()
            }
        }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetNoteTheme {
        NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
    }
}