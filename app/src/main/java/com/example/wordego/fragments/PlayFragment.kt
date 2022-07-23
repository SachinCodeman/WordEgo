package com.example.wordego.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.wordego.MAX_NO_OF_WORDS
import com.example.wordego.R
import com.example.wordego.databinding.FragmentPlayBinding
import com.example.wordego.models.PlayViewModel

class PlayFragment : Fragment() {

    // Creating a reference object to PlayViewModel
    private val viewModel: PlayViewModel by viewModels()

    private lateinit var binding: FragmentPlayBinding

    private var playerWord:String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(inflater, container, false)

        Log.d("GameFragment", "GameFragment created/re-created!")


        ("Remaining Guesses : "+ viewModel.chances.toString()).also { binding.guesses.text = it }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Setting a click listener for the Submit and Skip buttons.
        binding.submitBtn.setOnClickListener { onSubmitWord() }
        binding.skipBtn.setOnClickListener { onSkipWord() }

        viewModel.ans.observe(viewLifecycleOwner
        ) { newWord ->
            binding.ans.text = newWord
        }



        viewModel.currentScrambledWord.observe(viewLifecycleOwner
        ) { newWord ->
            binding.words.text = newWord
        }


        viewModel.currentWordCount.observe(viewLifecycleOwner
        ) { newWordCount ->
            binding.wordCount.text =
                getString(R.string.word_count, newWordCount, MAX_NO_OF_WORDS)
        }



    }

    private fun updateScore() {
        ("Score :"+viewModel.score.toString()).also { binding.score.text = it }
    }

    // onDetach will be called when the corresponding activity and fragment are destroyed.
    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }


    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textInputLayout.isErrorEnabled = true
            binding.textInputLayout.error = getString(R.string.try_again)
        } else {
            binding.textInputLayout.isErrorEnabled = false
            binding.textEditText.text = null
        }
    }




    private fun onSkipWord() {
        if (viewModel.nextWord()) {
            setErrorTextField(false)

        } else {
            val score :String = viewModel.score.toString()

            val action = PlayFragmentDirections.actionPlayFragmentToTryAgainFragment(score)
            view?.findNavController()?.navigate(action)

        }
    }

    private fun onSubmitWord() {
         playerWord = binding.textEditText.text.toString()

        if (playerWord.isEmpty()){
            binding.textInputLayout.isErrorEnabled = true
            binding.textInputLayout.error = getString(R.string.empty)
        }
        else{

            viewModel.chances -=1

            if (viewModel.chances < 1){

                view?.findNavController()?.navigate(R.id.action_playFragment_to_tryAgainFragment)

            }
            else{

                correctAns()
            }




        }


    }

    private fun correctAns() {
        if (viewModel.isUserWordCorrect(playerWord)) {
            updateScore()
            setErrorTextField(false)
            ("Remaining Guesses : "+viewModel.chances.toString()).also { binding.guesses.text = it }

            if (!viewModel.nextWord() ) {

                if (viewModel.allWords > 9){

                    winnerFragment()
                }
                else{
                    tryAgainFragment()
                }

            }
        }
        else {
            ("Remaining Guesses : "+viewModel.chances.toString()).also { binding.guesses.text = it }
            setErrorTextField(true)
        }
    }

    private fun tryAgainFragment() {

        val score :String = viewModel.score.toString()

        val action = PlayFragmentDirections.actionPlayFragmentToTryAgainFragment(score)
        view?.findNavController()?.navigate(action)
    }

    private fun winnerFragment() {

        val score :String = viewModel.score.toString()

        val action = PlayFragmentDirections.actionPlayFragmentToWinnerFragment(score)
        view?.findNavController()?.navigate(action)
    }


}

