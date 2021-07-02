package com.fredericho.moviecatalogue.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id : Int,
    var title : String,
    var overview : String,
    var date : String,
    var poster : String,
    var rating : Double,
    var genres : String,
    var favorite : Boolean
) : Parcelable