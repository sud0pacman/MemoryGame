package com.example.mymemorygame.repository

import com.example.mymemorygame.data.model.CardData
import com.example.mymemorygame.data.model.LevelEnum
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun checkOpens(openedImages: ArrayList<Int>) : Boolean
    fun getImagesByLevel(level: LevelEnum): Flow<List<CardData>>
}