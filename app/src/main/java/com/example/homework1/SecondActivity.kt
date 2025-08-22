package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras!!
        val buttonText = args.getString("text")

        binding.buttonTextDisplay.text = buttonText

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}