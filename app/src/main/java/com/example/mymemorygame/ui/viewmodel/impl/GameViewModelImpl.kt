package com.example.mymemorygame.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import com.example.mymemorygame.repository.AppRepository
import com.example.mymemorygame.ui.viewmodel.GameViewModel

class GameViewModelImpl (private val repository: AppRepository) : ViewModel(), GameViewModel {

}