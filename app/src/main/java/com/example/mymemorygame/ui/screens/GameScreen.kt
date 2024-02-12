package com.example.mymemorygame.ui.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymemorygame.R
import com.example.mymemorygame.data.model.CardData
import com.example.mymemorygame.data.model.LevelEnum
import com.example.mymemorygame.databinding.ScreenGameBinding
import com.example.mymemorygame.ui.viewmodel.factory.GameViewModelFactory
import com.example.mymemorygame.ui.viewmodel.impl.GameViewModelImpl
import com.example.utils.closeImage
import com.example.utils.hideAnim
import com.example.utils.myLog
import com.example.utils.openImage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Suppress("UNUSED_VARIABLE", "CAST_NEVER_SUCCEEDS")
class GameScreen : Fragment(R.layout.screen_game) {
    private val viewModel: GameViewModelImpl by viewModels { GameViewModelFactory() }
    private val binding: ScreenGameBinding by viewBinding(ScreenGameBinding::bind)
    private var x = 0
    private var y = 0
    private val views = ArrayList<ImageView>()
    private var cardWidth = 0f
    private var cardHeight = 0f
    private var centerX = 0f
    private var centerY = 0f
    private val navArgs by navArgs<GameScreenArgs>()
    private var findCards = 0
    private var canClick = true
    private lateinit var level: LevelEnum

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        "create".myLog()

        level = navArgs.level

        // ekran chizildi
        binding.container.post {
            // x y pozitsiya topildi
            x = level.rowCount
            y = level.columnCount

            cardWidth = binding.container.width.toFloat() / x
            cardHeight = binding.container.height.toFloat() / y

            centerX = binding.container.width / 2f
            centerY = binding.container.height / 2f


            "x: $x, y: $y".myLog()

            viewModel.loadImages(level) // viewmodelga rasmlar keldi

        }

        viewModel.images.onEach { loadViews(it) }
            .launchIn(lifecycleScope)

        viewModel.open.observe(viewLifecycleOwner, openObserver)
        viewModel.openAction.observe(viewLifecycleOwner, openWithActionObserver)

        viewModel.close.observe(viewLifecycleOwner, closeObserver)
        viewModel.closeWithAction.observe(viewLifecycleOwner, closeWithActionObserver)

        viewModel.hide.observe(viewLifecycleOwner, hideObserver)
        viewModel.hideWithAnim.observe(viewLifecycleOwner, hideWithAnimObserver)
    }

    private fun loadViews(images: List<CardData>) {
        for (i in 0 until x) {
            for (j in 0 until y) {
                val image = ImageView(requireContext())

                image.x = centerX - cardWidth / 2  // rasmni 1-pozitsiyasini centerga keltiri uchun
                image.y = centerY - cardHeight / 2  // rasmni 1-pozitsiyasini centerga keltiri uchun

                image.animate()
                    .setDuration(1000)
                    .x(i * cardWidth)       // rasmni x pozitsiyasi
                    .y(j * cardHeight)      // rasmni y pozitsiyasi

                image.setImageResource(R.drawable.image_back)   // image berish
                binding.container.addView(image)                // parent classga qo'shish (UI chizish)

                image.layoutParams.apply {
                    width = cardWidth.toInt()           // rasmni eni
                    height = cardHeight.toInt()         // rasmni bo'yi
                }

                image.tag = images[i * level.columnCount + j]

                views.add(image)
            }
        }

        clickEvent()
    }

    private fun clickEvent() {
       views.forEachIndexed { index, imageView ->
           imageView.setOnClickListener {
               if (!canClick) return@setOnClickListener
               viewModel.checkMatchingCards(imageView.tag as CardData, index)
           }
       }
    }


    private val openObserver = Observer<Int> {
        views[it].openImage()
    }

    private val openWithActionObserver = Observer<Int> {
        views[it].openImage {
            canClick = false
        }
    }


    private val closeObserver = Observer<Int> {
        views[it].closeImage()
    }

    private val closeWithActionObserver = Observer<Int> {
        views[it].closeImage {
            canClick = true
        }
    }

    private val hideObserver = Observer<Int> {
        views[it].hideAnim()

        views[it].apply {
            isClickable = false
            isFocusableInTouchMode = false
            isFocusable = false
        }

    }



    private val hideWithAnimObserver = Observer<Int> { index ->
        views[index].hideAnim {
            canClick = true
            ++findCards
            isFinish()

            // Apply properties after a delay using a Handler
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                views[index].apply {
                    isClickable = false
                    isFocusableInTouchMode = false
                    isFocusable = false
                }
            }, 100) // Adjust the delay time as needed
        }
    }



    private fun isFinish() {
        if (findCards == x * y)
            Toast.makeText(requireContext(), "Finish", Toast.LENGTH_SHORT).show()
    }
}