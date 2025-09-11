package com.example.homework1

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homework1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        // Bind the pizza button to switch screens and display 'pizza' on new screen
        createClickListener(binding.pizzaButton)

        // Bind the bacon button to switch screens and display 'bacon' on new screen
        createClickListener(binding.baconButton)

        // Bind the apple button to switch screens and display 'apple' on new screen
        createClickListener(binding.appleButton)

        // Bind the chips button to switch screens and display 'chips' on new screen
        createClickListener(binding.chipsButton)

        // Bind the cheeseburger button to switch screens and display 'cheeseburger' on new screen
        createClickListener(binding.burgerButton)

        setContentView(binding.root)
    }

    // Helper function to reduce code reuse.
    // Takes in a 'binding.*id_name*' and adds a click listener to that button
    fun createClickListener(button : android.widget.Button){
        button.setOnClickListener {
            val buttonText = button.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            val argsBundle = Bundle()
            argsBundle.putString("text", buttonText)
            intent.putExtras(argsBundle)
            startActivity(intent)
        }
    }

}