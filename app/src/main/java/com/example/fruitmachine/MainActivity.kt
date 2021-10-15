// Brian McKenna - SDB
// Github Link:

package com.example.fruitmachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.animation.ObjectAnimator.ofPropertyValuesHolder
import android.animation.PropertyValuesHolder
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create an empty array of type Animator
        val animations: MutableList<Animator> = ArrayList()

        // get the button / win image / slot images by id
        val spinButton: Button = findViewById(R.id.spinButton)
        val fruitSlot1Image: ImageView = findViewById(R.id.fruitSlot1)
        val fruitSlot2Image: ImageView = findViewById(R.id.fruitSlot2)
        val fruitSlot3Image: ImageView = findViewById(R.id.fruitSlot3)
        val winImage: ImageView = findViewById(R.id.winImage)

        // set the win image and set invisible
        winImage.visibility = View.INVISIBLE
        winImage.setImageResource(R.drawable.win)

        // store references to images in an array for animation purposes
        val imageRefs = arrayOf(fruitSlot1Image, fruitSlot2Image, fruitSlot3Image, winImage)

        // loop through the image array and add animation property values to each image
        for (image in imageRefs) {
            animations.add(ofPropertyValuesHolder(image,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f)).apply {
                duration = 310
                repeatCount = INFINITE
                repeatMode = REVERSE
            })
        }
            // create a set for the animations
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(animations)

            // get all the textview result texts by id
            val spinsSinceLastWinResult: TextView = findViewById(R.id.spinsSinceLastWinResult)
            val winSpinRatioResult: TextView = findViewById(R.id.winSpinRatioResult)
            val totalSpinsResult: TextView = findViewById(R.id.totalSpinsResult)
            val totalWinsResult: TextView = findViewById(R.id.totalWinsResult)

            // declare and initialise counters
            var totalSpinCounter = 0
            var totalWinCounter = 0
            var winRatio: Double
            var spinSinceLastWinCounter = 0


            // set onclick listener to the button
            spinButton.setOnClickListener {
                // set the image resource

                val slot = Slot(4)

                winImage.visibility = View.INVISIBLE

                // trigger function that generates 3 random numbers
                val spinSlot1 = slot.spin()

                when (spinSlot1)
                {
                    1 -> fruitSlot1Image.setImageResource(R.drawable.banana)
                    2 -> fruitSlot1Image.setImageResource(R.drawable.cherry)
                    3 -> fruitSlot1Image.setImageResource(R.drawable.watermelon)
                    4 -> fruitSlot1Image.setImageResource(R.drawable.orange)
                }

                val spinSlot2 = slot.spin()

                when (spinSlot2)
                {
                    1 -> fruitSlot2Image.setImageResource(R.drawable.banana)
                    2 -> fruitSlot2Image.setImageResource(R.drawable.cherry)
                    3 -> fruitSlot2Image.setImageResource(R.drawable.watermelon)
                    4 -> fruitSlot2Image.setImageResource(R.drawable.orange)
                }

                val spinSlot3 = slot.spin()

                when (spinSlot3)
                {
                    1 -> fruitSlot3Image.setImageResource(R.drawable.banana)
                    2 -> fruitSlot3Image.setImageResource(R.drawable.cherry)
                    3 -> fruitSlot3Image.setImageResource(R.drawable.watermelon)
                    4 -> fruitSlot3Image.setImageResource(R.drawable.orange)
                }

                // increment the total number of spins after all three slots have spun
                totalSpinCounter++

                totalSpinsResult.text = "$totalSpinCounter"

                // win condition
                if(spinSlot1 == spinSlot2 && spinSlot2 == spinSlot3)
                {
                    winImage.visibility = View.VISIBLE
                    animatorSet.start()
                    totalWinCounter++
                    spinSinceLastWinCounter = 0
                }
                else {
                    if(totalWinCounter != 0)
                    {
                        spinSinceLastWinCounter++
                    }
                }
                totalWinsResult.text = "$totalWinCounter"

                winRatio = (totalWinCounter / totalSpinCounter.toDouble()) * 100
                winSpinRatioResult.text = "%.2f%%".format(winRatio)

                spinsSinceLastWinResult.text = "$spinSinceLastWinCounter"

                if(spinSinceLastWinCounter == 1)
                {
                    // stop the set of animations the next click after a win
                    animatorSet.cancel()
                }
            }
        }
    }

class Slot(val possibleOutcomes: Int)
{
    fun spin(): Int {
        return (1..possibleOutcomes).random()
    }
}

// TODO - MAKE VIDEO
