package com.example.utils

import android.animation.ObjectAnimator.*
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import com.example.mymemorygame.R
import com.example.mymemorygame.data.model.CardData
import timber.log.Timber

fun String.myLog(tag: String = "TTT") = Timber.tag(tag).d(this)

fun ImageView.animateBounce() {
    val bounceAnimator = ofFloat(this, "translationY", 0f, 0f, 0f)
    bounceAnimator.interpolator = BounceInterpolator()
    bounceAnimator.duration = 1000 // Set the duration of the animation
    bounceAnimator.start()
}


fun ImageView.openImage() {
    animate()
        .setDuration(500)
        .rotationY(89f)
        .withEndAction {
            setImageResource((this.tag as CardData).resID)
            rotationY = -89f

            animate()
                .setDuration(500)
                .rotationY(0f)
                .withEndAction {
                }
                .start()
        }
        .start()
}

fun ImageView.openImage(endAnim: () -> Unit) {
    animate()
        .setDuration(500)
        .rotationY(89f)
        .withEndAction {
            setImageResource((this.tag as CardData).resID)
            rotationY = -89f

            animate()
                .setDuration(500)
                .rotationY(0f)
                .withEndAction {
                    endAnim.invoke()
                }
                .start()
        }
        .start()
}

fun ImageView.closeImage() {
    this.apply {
        animate()
            .setDuration(500)
            .rotationY(89f)
            .withEndAction {
                setImageResource(R.drawable.image_back)
                rotationY = -89f

                animate()
                    .setDuration(500)
                    .rotationY(0f)
                    .withEndAction {
                    }
                    .start()
            }
            .start()
    }

}

fun ImageView.closeImage(endAnim: () -> Unit) {
    animate()
        .setDuration(500)
        .rotationY(89f)
        .withEndAction {
            setImageResource(R.drawable.image_back)
            rotationY = -89f

            animate()
                .setDuration(500)
                .rotationY(0f)
                .withEndAction {
                    endAnim.invoke()
                }
                .start()
        }
        .start()
}

fun ImageView.hideAnim() {
    this.animate()
        .setDuration(1000)
        .alpha(0.5f)
//        .scaleX(0f)
//        .scaleY(0f)
        .start()
}

fun ImageView.hideAnim(endAnim: () -> Unit) {
    this.animate()
        .setDuration(1000)
        .alpha(0.5f)
//        .scaleX(0f)
//        .scaleY(0f)
        .withEndAction(endAnim)
        .start()
}

