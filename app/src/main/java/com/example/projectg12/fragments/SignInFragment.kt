package com.example.projectg12.fragments

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.projectg12.R
import com.example.projectg12.databinding.FragmentSignInBinding
import com.example.projectg12.repository.AuthRepo
import com.example.projectg12.repository.UsersRepo
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {

    val TAG: String = "PROJECT G12"
    private var bindingg: FragmentSignInBinding? = null
    private val binding get() = bindingg!!
    private lateinit var authRepo: AuthRepo
    private lateinit var usersRepo: UsersRepo
    private var email = ""
    private var password = ""
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingg = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authRepo = AuthRepo()
        usersRepo = UsersRepo()

        binding.btnSignIn.setOnClickListener {
            if (validateData()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val authResult = authRepo.signIn(requireContext(), email, password)
                    Log.d(TAG, " SIGN IN WITH $email successful")
                    if (authResult) {
                        saveToPrefs(email, password)
                    }
                }
            }
        }
        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment_to_signUp,null)
        }
    }

    private fun validateData(): Boolean {
        var validData = true

        if (binding.emailET.text.toString().isEmpty()) {
            binding.emailET.error = "Email Cannot be Empty"
            validData = false
        } else {
            email = binding.emailET.text.toString()
        }
        if (binding.passwordET.text.toString().isEmpty()) {
            binding.passwordET.error = "Password Cannot be Empty"
            validData = false
        } else {
            password = binding.passwordET.text.toString()
        }
        return validData
    }

    private fun saveToPrefs(email: String, password: String) {
        val prefs = requireContext().getSharedPreferences(
            requireContext().toString(),
            AppCompatActivity.MODE_PRIVATE
        )
        prefs.edit().putString("USER_EMAIL", email).apply()
        prefs.edit().putString("USER_PASSWORD", password).apply()

    }
}