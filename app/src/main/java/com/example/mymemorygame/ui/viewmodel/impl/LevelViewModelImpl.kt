package com.example.mymemorygame.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import com.example.mymemorygame.data.model.LevelEnum
import com.example.mymemorygame.repository.AppRepositoryImpl
import com.example.mymemorygame.ui.viewmodel.LevelViewModel

class LevelViewModelImpl (private val repository: AppRepositoryImpl) : ViewModel(), LevelViewModel {
    override fun selectLevel(level: LevelEnum) {
//        repository.getImagesByLevel(level)
    }
}


