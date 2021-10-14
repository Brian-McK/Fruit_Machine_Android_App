package com.example.fruitmachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the button & slot images by id
        val spinButton: Button = findViewById(R.id.spinButton)
        val fruitSlot1Image: ImageView = findViewById(R.id.fruitSlot1)
        val fruitSlot2Image: ImageView = findViewById(R.id.fruitSlot2)
        val fruitSlot3Image: ImageView = findViewById(R.id.fruitSlot3)

        // unused
        val imageRefs = arrayOf(fruitSlot1Image, fruitSlot2Image, fruitSlot3Image)

        // get all the textview result texts by id
        val spinsSinceLastWinResult: TextView = findViewById(R.id.spinsSinceLastWinResult)
        val winSpinRatioResult: TextView = findViewById(R.id.winSpinRatioResult)
        val totalSpinsResult: TextView = findViewById(R.id.totalSpinsResult)
        val totalWinsResult: TextView = findViewById(R.id.totalWinsResult)

        // declare and initialise counter for total number of spins
        var totalSpinCounter = 0
        var totalWinCounter = 0
        var winRatio: Double
        var spinSinceLastWinCounter = 0


        // set onclick listener to the button
        spinButton.setOnClickListener {
            // trigger function that generates 3 random numbers
            // set the image resource

            val slot = Slot(4)

            val spinSlot1 = slot.spin()
            when (spinSlot1)
            {
                1 -> fruitSlot1Image.setImageResource(R.drawable.banana)
                2 -> fruitSlot1Image.setImageResource(R.drawable.cherry)
                3 -> fruitSlot1Image.setImageResource(R.drawable.watermelon)
                4 -> fruitSlot1Image.setImageResource(R.drawable.orange)
            }

            println("SLOT 1: $spinSlot1")

            val spinSlot2 = slot.spin()

            Handler(Looper.getMainLooper()).postDelayed({
                when (spinSlot2)
                {
                    1 -> fruitSlot2Image.setImageResource(R.drawable.banana)
                    2 -> fruitSlot2Image.setImageResource(R.drawable.cherry)
                    3 -> fruitSlot2Image.setImageResource(R.drawable.watermelon)
                    4 -> fruitSlot2Image.setImageResource(R.drawable.orange)
                }
            }, 300)


            println("SLOT 2: $spinSlot2")

            val spinSlot3 = slot.spin()

            Handler(Looper.getMainLooper()).postDelayed({
                when (spinSlot3)
                {
                    1 -> fruitSlot3Image.setImageResource(R.drawable.banana)
                    2 -> fruitSlot3Image.setImageResource(R.drawable.cherry)
                    3 -> fruitSlot3Image.setImageResource(R.drawable.watermelon)
                    4 -> fruitSlot3Image.setImageResource(R.drawable.orange)
                }
            }, 600)

            println("SLOT 3: $spinSlot3")

            // increment the total number of spins after all three slots have spun
            totalSpinCounter++

            totalSpinsResult.text = "$totalSpinCounter"

            if(spinSlot1 == spinSlot2 && spinSlot2 == spinSlot3)
            {
                totalWinCounter++
                spinSinceLastWinCounter = 0
            }
            else {
                spinSinceLastWinCounter++
            }
            totalWinsResult.text = "$totalWinCounter"

            winRatio = (totalWinCounter / totalSpinCounter.toDouble()) * 100
            winSpinRatioResult.text = "%.2f%%".format(winRatio)

            spinsSinceLastWinResult.text = "$spinSinceLastWinCounter"
        }
    }
}

class Slot(val possibleOutcomes: Int)
{
    // the function must choose random number between 1 and 3
    fun spin(): Int {
        return (1..possibleOutcomes).random()
    }
}





// TODO - FIX IMAGES
// TODO - MAKE VIDEO
// TODO - COMMENT PROPERLY
// TODO - TRY GET SPINNING ANIMATION
