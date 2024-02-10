package com.example.mymemorygame.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymemorygame.R
import com.example.mymemorygame.databinding.ScreenLevelBinding

class LevelScreen : Fragment(R.layout.screen_level) {
    private val binding: ScreenLevelBinding by viewBinding(ScreenLevelBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.easy.setOnClickListener {
            findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen())
        }
    }
}


