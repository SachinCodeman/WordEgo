package com.example.wordego.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.example.wordego.R
import com.example.wordego.databinding.FragmentHomeBinding
import kotlin.system.exitProcess

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.playBtn.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToPlayFragment()
            view?.findNavController()?.navigate(action)

        }

        binding.exitBtn.setOnClickListener {



        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}