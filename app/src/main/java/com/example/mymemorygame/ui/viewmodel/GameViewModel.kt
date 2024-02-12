package com.example.mymemorygame.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.mymemorygame.data.model.CardData
import com.example.mymemorygame.data.model.LevelEnum
import kotlinx.coroutines.flow.MutableSharedFlow

interface GameViewModel {
    val images: MutableSharedFlow<List<CardData>>
    var message: LiveData<Boolean>
    val close: LiveData<Int>
    val canClick: LiveData<Boolean>
    val hide: LiveData<Int>

    fun checkMatchingCards(clickView: CardData, clickedIndex: Int)
    fun loadImages(level: LevelEnum)
    val open: LiveData<Int>
    val closeWithAction: LiveData<Int>
    val openAction: LiveData<Int>
    val hideWithAnim: LiveData<Int>
}

