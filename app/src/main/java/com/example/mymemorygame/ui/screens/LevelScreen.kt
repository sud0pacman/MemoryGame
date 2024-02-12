package com.example.mymemorygame.ui.screens

import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymemorygame.R
import com.example.mymemorygame.data.model.LevelEnum
import com.example.mymemorygame.databinding.ScreenLevelBinding
import com.example.mymemorygame.ui.viewmodel.factory.LevelViewModelFactory
import com.example.mymemorygame.ui.viewmodel.impl.LevelViewModelImpl
import com.example.utils.myLog

class LevelScreen : Fragment(R.layout.screen_level) {
    private val binding: ScreenLevelBinding by viewBinding(ScreenLevelBinding::bind)
    private val viewModel by viewModels<LevelViewModelImpl> { LevelViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.root.post {
            val oldYEasy = binding.easy.y
            val oldYMedium = binding.medium.y
            val oldYHard = binding.hard.y
            binding.easy.y = 0F
            binding.medium.y = 0F
            binding.hard.y = 0F
            binding.easy.visibility = View.INVISIBLE
            binding.medium.visibility = View.INVISIBLE
            binding.hard.animate()
                .setDuration(500)
                .setInterpolator(BounceInterpolator())
                .y(oldYHard)
                .withEndAction {
                    binding.medium.isVisible = true
                    binding.medium.animate()
                        .setDuration(400)
                        .setInterpolator(BounceInterpolator())
                        .y(oldYMedium)
                        .withEndAction {
                            binding.easy.isVisible = true
                            binding.easy.animate()
                                .setDuration(300)
                                .setInterpolator(BounceInterpolator())
                                .y(oldYEasy)
                                .start()
                        }
                        .start()
                }
                .start()
        }

        binding.easy.setOnClickListener {
            "click easy".myLog()
            findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(LevelEnum.EASY))
        }

        binding.medium.setOnClickListener {
            "click medium".myLog()
            findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(LevelEnum.MEDIUM))
        }


        binding.hard.setOnClickListener {
            "click hard".myLog()
            findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(LevelEnum.HARD))
        }
    }
}


