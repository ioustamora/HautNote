package eu.saveliev.hautnote.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)
    class Icon(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this) {
            is Date -> Date(orderType)
            is Color -> Color(orderType)
            is Icon -> Icon(orderType)
        }
    }
}
