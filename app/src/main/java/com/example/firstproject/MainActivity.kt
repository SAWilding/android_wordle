package com.example.firstproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL


class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var infoText: TextView
    lateinit var button: Button
    lateinit var spaces: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val game = Wordle()



// Initialize variable for each widget from activity_main.xml
        editText = findViewById(R.id.textbox)
        button = findViewById(R.id.button)
        infoText = findViewById(R.id.infoText)
        spaces = findViewById(R.id.spaces)

        // fetch a random word from the list
        game.getRandomWord()

        // Reset spaces
        spaces.text = game.getSpaces()
        // Display number of guesses left
        infoText.text = "You have ${game.numberOfGuesses} guesses left."

        // Create event listener for guess button
        var continueGame = true
        button.setOnClickListener {
            val inputText = editText.text.toString()

            if (continueGame) {
                // Make sure input is a 5 letter word
                if (inputText.length == 5) {
                    // If the guess is correct give option to restart game
                    if (editText.text.toString() == game.word) {
                        button.text = "Restart"
                        infoText.text = "That is correct!\nHit the button to start a new game!"
                        spaces.text = game.word
                        continueGame = false
                    } else {
                        // If the guess is incorrect, compare the guess to the correct word
                        game.numberOfGuesses -= 1
                        infoText.text =
                            "That was incorrect! \nYou have ${game.numberOfGuesses} guesses left."
                        game.compareWords(editText.text.toString())
                        spaces.text = game.getSpaces()
                    }
                    // If player runs out of guesses, end game
                    if (game.numberOfGuesses == 0) {
                        button.text = "Restart"
                        infoText.text = "You ran out of guesses.\nHit the button to try again."
                        spaces.text = game.word
                        continueGame = false
                    }
                } else {
                    // If the input is not a 5 letter word
                    infoText.text = "Please enter a 5 letter word."
                }
            } else {
                // Reset the game
                infoText.text = "The game has restarted.\nGuess the new word!"
                button.text = "Guess"
                game.getRandomWord()

                spaces.text = game.getSpaces()
                editText.text = null
                game.numberOfGuesses = 6

                continueGame = true

            }
        }
    }
}



















