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
    private var draws = listOf<Int>()
    private var cardWidth = 0f
    private var cardHeight = 0f
    private var centerX = 0f
    private var centerY = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.container.post {
            cardWidth = binding.container.width.toFloat() / x
            cardHeight = binding.container.height.toFloat() / y

            centerX = binding.container.width / 2f
            centerY = binding.container.height / 2f

            addImages()
            loadDraws()
            clickEvent()
        }
    }

    private fun addImages() {
        for (i in 0 until x) {
            for (j in 0 until y) {
                val image = ImageView(requireContext())

                image.x = centerX - cardWidth / 2  // rasmni 1-pozitsiyasini centerga keltiri uchun
                image.y = centerY - cardHeight / 2  // rasmni 1-pozitsiyasini centerga keltiri uchun

                image.animate()
                    .setDuration(1000)
                    .x(i * cardWidth)       // rasmni x pozitsiyasi
                    .y(j * cardHeight)      // rasmni y pozitsiyasi

                image.setImageResource(R.drawable.image_back)   // image verish
                binding.container.addView(image)                // parent classga qo'shish (UI chizish)

                image.layoutParams.apply {
                    width = cardWidth.toInt()           // rasmni eni
                    height = cardHeight.toInt()         // rasmni bo'yi
                }

                views.add(image)
            }
        }
    }

    private fun clickEvent() {
        for (i in 0 until views.size - 1 step 2) {
            val onClickListener: (Int) -> Unit = {index ->
                views[index].apply {
                    this.animate()
                        .setDuration(500)
                        .rotationY(89f)
                        .withEndAction {
                            this.setImageResource(draws[index])
                            this.rotationY = -89f

                            this.animate()
                                .setDuration(500)
                                .rotationY(0f)
                                .start()
                        }
                        .start()
                }
            }


            views[i].setOnClickListener { onClickListener.invoke(i) }
            views[i+1].setOnClickListener { onClickListener.invoke(i+1) }
        }
    }

    private fun loadDraws() {
        draws = listOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8,
            R.drawable.image_9,
            R.drawable.image_10,
            R.drawable.image_11,
            R.drawable.image_12,
//            R.drawable.image_13,
//            R.drawable.image_14,
//            R.drawable.image_15,
//            R.drawable.image_16,
//            R.drawable.image_17,
//            R.drawable.image_18,
//            R.drawable.image_19,
//            R.drawable.image_20,
//            R.drawable.image_21,
//            R.drawable.image_22,
//            R.drawable.image_23,
//            R.drawable.image_24,
        )

        draws = draws.subList(0, draws.size) + draws.subList(0, draws.size).shuffled()
    }
}