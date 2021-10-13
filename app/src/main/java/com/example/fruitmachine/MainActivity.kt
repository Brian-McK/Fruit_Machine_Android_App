package com.example.fruitmachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the button & slot images by id
        val spinButton: Button = findViewById(R.id.spinButton)
        val fruitSlot1Image: ImageView = findViewById(R.id.fruitSlot1)
        val fruitSlot2Image: ImageView = findViewById(R.id.fruitSlot2)
        val fruitSlot3Image: ImageView = findViewById(R.id.fruitSlot3)


        // set onclick listener to the button
        spinButton.setOnClickListener {
            // trigger function that generates 3 random numbers
            // set the image resource

            val slot = Slot(3)

            when (slot.spin()) {
                1 -> fruitSlot1Image.setImageResource(R.drawable.banana)
                2 -> fruitSlot1Image.setImageResource(R.drawable.cherry)
                else -> fruitSlot1Image.setImageResource(R.drawable.orange)
            }

            when (slot.spin()) {
                1 -> fruitSlot2Image.setImageResource(R.drawable.banana)
                2 -> fruitSlot2Image.setImageResource(R.drawable.cherry)
                else -> fruitSlot2Image.setImageResource(R.drawable.orange)
            }

            when (slot.spin()) {
                1 -> fruitSlot3Image.setImageResource(R.drawable.banana)
                2 -> fruitSlot3Image.setImageResource(R.drawable.cherry)
                else -> fruitSlot3Image.setImageResource(R.drawable.orange)
            }
        }
    }
}

class Slot(private val possibleOutcomes: Int)
{
    // the function must choose random number between 1 and 3
    fun spin(): Int {
        return (1..possibleOutcomes).random()
    }
}