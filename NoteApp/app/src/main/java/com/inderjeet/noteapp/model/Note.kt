package com.inderjeet.noteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,

    val title:String,
    val description:String,
    var date: Int
):Parcelable
