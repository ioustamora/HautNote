package eu.saveliev.hautnote.feature_note.domain.use_case

import eu.saveliev.hautnote.NoteApp
import eu.saveliev.hautnote.R
import eu.saveliev.hautnote.feature_note.domain.model.InvalidNoteException
import eu.saveliev.hautnote.feature_note.domain.model.Note
import eu.saveliev.hautnote.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.content.isBlank()) {
            throw NoteApp.context?.let { InvalidNoteException(it.getString(R.string.err_save_note)) }!!
        }
        repository.insertNote(note)
    }
}