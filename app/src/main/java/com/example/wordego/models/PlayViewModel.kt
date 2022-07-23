package com.example.wordego.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wordego.MAX_NO_OF_WORDS
import com.example.wordego.SCORE_INCREASE
import com.example.wordego.WordsList


class PlayViewModel : ViewModel() {


    // Declare private mutable variable that can only be modified
    // within the class it is declared.
    private var _count = 0
    private var _score = 0
    private var _allWords = 0
    private var _ans = MutableLiveData<String>()
    private val _currentWordCount = MutableLiveData(0)
    private val _currentScrambledWord = MutableLiveData<String>()
    var chances = 20

    val count: Int
        get() = _count

    val score: Int
        get() = _score

    val allWords: Int
        get() = _allWords

    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    val ans: LiveData<String>
        get() = _ans






    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord:String

    val cw : String
      get() = currentWord





    private fun getNextWord() {

        // Getting a random word from allWordList
        currentWord = WordsList.random()
        _ans.value = currentWord


        // Shuffling the currentWord
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        //Shuffled word should not be same as currentWord
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        // checking if the currentWord already displayed
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }


    }

    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }
    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()

    }
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            _allWords+=1
            return true
        }
        return false
    }

    private fun increaseScore() {
        _score+= SCORE_INCREASE
    }


    fun reinitializeData() {
        _score = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }





}