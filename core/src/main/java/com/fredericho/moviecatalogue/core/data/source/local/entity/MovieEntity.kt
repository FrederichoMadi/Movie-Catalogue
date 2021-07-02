package com.fredericho.moviecatalogue.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_entity")
@Parcelize
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "overview")
    var overview : String,

    @ColumnInfo(name = "date")
    var date : String,

    @ColumnInfo(name = "poster")
    var poster : String,

    @ColumnInfo(name = "rating")
    var rating : Double,

    @ColumnInfo(name = "genres")
    var genres : String,

    @ColumnInfo(name = "favorite")
    var favorite : Boolean
) : Parcelable
