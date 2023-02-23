package eu.saveliev.hautnote.feature_note.domain.use_case

import eu.saveliev.hautnote.feature_note.domain.model.InvalidNoteException
import eu.saveliev.hautnote.feature_note.domain.model.Note
import eu.saveliev.hautnote.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Title of the note can't be empty!")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Content of the note can't be empty!")
        }
        repository.insertNote(note)
    }
}