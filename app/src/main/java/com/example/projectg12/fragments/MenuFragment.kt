package com.example.projectg12.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.projectg12.R
import com.example.projectg12.databinding.FragmentMenuBinding
import com.example.projectg12.databinding.FragmentSignUpBinding
import com.example.projectg12.repository.MenuRepo
import kotlinx.coroutines.launch

class MenuFragment : Fragment() {
    val TAG: String = "SIGNUP-FRAGMENT"
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuRepo:MenuRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRepo = MenuRepo()
        viewLifecycleOwner.lifecycleScope.launch {
            menuRepo.getAllMenuItems()
        }


    }

}