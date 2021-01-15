package com.example.skilltest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,

    @SerializedName(value = "id")
    @ColumnInfo(name = "external_id")
    @Expose
    val externalId: String?,

    @SerializedName(value = "screenname", alternate = ["login"])
    @ColumnInfo(name = "user_name")
    @Expose
    val userName: String,

    @SerializedName(value = "avatar_url", alternate = ["avatar_190_url"])
    @ColumnInfo(name = "avatar_url")
    @Expose
    val avatarUrl: String,

    @ColumnInfo(name = "source_url")
    var sourceUrl: String
)