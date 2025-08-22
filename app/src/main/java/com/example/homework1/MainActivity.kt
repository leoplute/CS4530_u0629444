package com.example.homework1

import android.os.Bundle
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homework1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        //enableEdgeToEdge()
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        // Bind the pizza button to switch screens and display 'pizza' on new screen
        binding.pizzaButton.setOnClickListener {
            val buttonText = binding.pizzaButton.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            val argsBundle = Bundle()
            argsBundle.putString("text", buttonText)
            intent.putExtras(argsBundle)
            startActivity(intent)
        }

        // Bind the bacon button to switch screens and display 'bacon' on new screen
        binding.baconButton.setOnClickListener {
            val buttonText = binding.baconButton.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            val argsBundle = Bundle()
            argsBundle.putString("text", buttonText)
            intent.putExtras(argsBundle)
            startActivity(intent)
        }

        // Bind the apple button to switch screens and display 'apple' on new screen
        binding.appleButton.setOnClickListener {
            val buttonText = binding.appleButton.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            val argsBundle = Bundle()
            argsBundle.putString("text", buttonText)
            intent.putExtras(argsBundle)
            startActivity(intent)
        }

        // Bind the chips button to switch screens and display 'chips' on new screen
        binding.chipsButton.setOnClickListener {
            val buttonText = binding.chipsButton.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            val argsBundle = Bundle()
            argsBundle.putString("text", buttonText)
            intent.putExtras(argsBundle)
            startActivity(intent)
        }

        // Bind the cheeseburger button to switch screens and display 'cheeseburger' on new screen
        binding.burgerButton.setOnClickListener {
            val buttonText = binding.burgerButton.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            val argsBundle = Bundle()
            argsBundle.putString("text", buttonText)
            intent.putExtras(argsBundle)
            startActivity(intent)
        }

        setContentView(binding.root)

    }
}