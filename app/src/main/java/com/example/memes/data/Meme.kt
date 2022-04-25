package com.example.memes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "memes_table")
data class Meme(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
//    val author: String,
//    val nsfw: Boolean,
//    val postLink: String,
//    val preview: List<String>,
//    val spoiler: Boolean,
//    val subreddit: String,
//    val title: String,
//    val ups: Int,
    val url: String
)