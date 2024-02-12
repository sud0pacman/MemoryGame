package com.example.mymemorygame.ui.viewmodel.factory

import com.example.mymemorygame.ui.viewmodel.impl.GameViewModelImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymemorygame.repository.AppRepositoryImpl

class GameViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModelImpl::class.java))
            return GameViewModelImpl(AppRepositoryImpl.getInstance()) as T

        throw IllegalArgumentException()
    }
}




