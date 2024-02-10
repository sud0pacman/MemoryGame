package com.example.mymemorygame.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymemorygame.repository.AppRepository
import com.example.mymemorygame.ui.viewmodel.impl.GameViewModelImpl

class GameViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModelImpl::class.java))
            return GameViewModelImpl(AppRepository.getInstance()) as T

        throw IllegalArgumentException()
    }
}




