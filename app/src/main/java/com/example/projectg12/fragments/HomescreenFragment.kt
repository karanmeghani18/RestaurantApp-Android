package com.example.projectg12.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projectg12.R
import com.example.projectg12.databinding.ActivityMainBinding.inflate
import com.example.projectg12.databinding.FragmentCartBinding
import com.example.projectg12.databinding.FragmentHomescreenBinding

class HomescreenFragment : Fragment() {

    private var _binding: FragmentHomescreenBinding? = null
    private val binding  get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomescreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView1.setOnClickListener{
            val action = HomescreenFragmentDirections.actionHomescreenFragmentToMenuFragment()
            findNavController().navigate(action)
        }

        binding.imgBurger.setOnClickListener{
            val action = HomescreenFragmentDirections.actionHomescreenFragmentToMenuFragment()
            findNavController().navigate(action)

        }
    }
}