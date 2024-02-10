package com.example.mymemorygame.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymemorygame.R
import com.example.mymemorygame.databinding.ScreenGameBinding

class GameScreen : Fragment(R.layout.screen_game) {
    private val binding: ScreenGameBinding by viewBinding(ScreenGameBinding::bind)
    private val x = 4
    private val y = 6
    private val views = ArrayList<ImageView>()
    private var cardWith = 0f
    private var cardHeight = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.container.post {
            cardWith = binding.container.width.toFloat() / x
            cardHeight = binding.container.height.toFloat() / y

            addImages()
        }
    }

    private fun addImages() {
        for (i in 0 until x) {
            for (j in 0 until y) {
                val image = ImageView(requireContext())

                image.setImageResource(R.drawable.image_back)
                image.layoutParams.apply {
                    width = cardWith.toInt()
                    height = cardHeight.toInt()
                }
            }
        }
    }
}