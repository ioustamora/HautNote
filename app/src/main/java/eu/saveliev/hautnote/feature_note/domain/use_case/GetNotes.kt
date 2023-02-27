package eu.saveliev.hautnote.feature_note.domain.use_case

import eu.saveliev.hautnote.feature_note.domain.model.Note
import eu.saveliev.hautnote.feature_note.domain.repository.NoteRepository
import eu.saveliev.hautnote.feature_note.domain.util.NoteOrder
import eu.saveliev.hautnote.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                        is NoteOrder.Icon -> notes.sortedBy { it.icon }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                        is NoteOrder.Icon -> notes.sortedByDescending { it.icon }
                    }
                }
            }
        }
    }
}