package ru.el.coordinatestracker.utils


const val TYPE_ROOM = "type_room"


//lateinit var REPOSITORY: DatabaseRepository

object Constants {
    object Keys{
        const val START = "Начать"
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"

        const val ADD_NOTE = "Добавить новую заметку"
        const val ID= "id"
        const val NONE= "none"
        const val UPDATE_NOTE = "Обновить заметку"
        const val EDIT_NOTE = "Отредактировать заметку"
        const val EMPTY = ""
        const val DELETE = "Удалить"
        const val NAV_BACK = "Назад"
        const val UPDATE = "Обновить"
        const val SORT_BY_PRIORITY = "by_priority"
        const val SORT_BY_DATE = "by_date"
    }

    object Screens{
        const val START_SCREEN="start_screen"
        const val MAIN_SCREEN="main_screen"
        const val ADD_SCREEN=("add_screen")
        const val NOTE_SCREEN=("note_screen")
    }
}