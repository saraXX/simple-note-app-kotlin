package android.guide.note_app_kotlin.data

import android.guide.note_app_kotlin.model.Note
import android.util.Log
import kotlin.math.log

//TODO 6 : CREATE DATASOURCE OBJECT CLASS
object DataSource {
    var noteList = mutableListOf<Note>()
    init{
        noteList.add(Note("hi this is a first note",
            "try to add more from button bellow"))
    }
}