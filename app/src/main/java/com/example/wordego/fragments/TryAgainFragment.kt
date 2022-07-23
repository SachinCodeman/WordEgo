package com.example.wordego.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wordego.R
import com.example.wordego.databinding.FragmentTryAgainBinding
import com.example.wordego.databinding.FragmentWinnerBinding

class TryAgainFragment : Fragment() {

    val args : TryAgainFragmentArgs by navArgs()

    private  var _binding : FragmentTryAgainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTryAgainBinding.inflate(inflater, container, false)

        binding.winnerScore.text = args.score

        binding.tryAgain.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_global_playFragment)

        }

        return binding.root


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}