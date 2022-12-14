package com.example.projectg12.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projectg12.R
import com.example.projectg12.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    val TAG: String = "Splash-Fragment"
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Add navigation logic
        Handler(Looper.myLooper()!!).postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToSignInFragment()
            findNavController().navigate(action)

        }, 2000)
    }

}