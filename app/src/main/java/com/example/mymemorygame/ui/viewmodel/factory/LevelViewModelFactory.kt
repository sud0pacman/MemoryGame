package com.example.mymemorygame.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymemorygame.repository.AppRepositoryImpl
import com.example.mymemorygame.ui.viewmodel.impl.LevelViewModelImpl

class LevelViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LevelViewModelImpl::class.java))
            return LevelViewModelImpl(AppRepositoryImpl.getInstance()) as T
        throw IllegalArgumentException("")
    }
}