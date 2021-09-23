package com.example.notesappmvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Table is created here
@Entity(tableName ="notes_table")
class Note(@ColumnInfo(name ="text") val text: String) {
    @PrimaryKey(autoGenerate = true)var id=0
}