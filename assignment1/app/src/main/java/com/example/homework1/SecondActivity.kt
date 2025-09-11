package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.example.homework1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up 'binding'
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the sent over text and display
        val args = intent.extras!!
        val buttonText = args.getString("text")
        binding.buttonTextDisplay.text = buttonText

        // Make the click listener for back button
        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}