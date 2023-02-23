package eu.saveliev.hautnote.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.saveliev.hautnote.feature_note.domain.model.Note
import eu.saveliev.hautnote.feature_note.domain.use_case.NoteUseCases
import eu.saveliev.hautnote.feature_note.domain.util.NoteOrder
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class && state.value
                        .noteOrder.orderType == event.noteOrder.orderType) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {

    }
}