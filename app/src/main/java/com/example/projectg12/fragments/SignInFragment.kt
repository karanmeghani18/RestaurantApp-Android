package com.example.projectg12.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projectg12.R
import com.example.projectg12.databinding.FragmentSignInBinding
import com.example.projectg12.models.DataSource
import com.example.projectg12.repository.AuthRepo
import com.example.projectg12.repository.UsersRepo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {

    val TAG: String = "PROJECT G12"
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var authRepo: AuthRepo
    private lateinit var usersRepo: UsersRepo
    private var email = ""
    private var password = ""
    private lateinit var mAuth: FirebaseAuth
    private var dataSource: DataSource = DataSource.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authRepo = AuthRepo()
        usersRepo = UsersRepo()

        binding.emailET.setText("karanmeghani18@gmail.com")
        binding.passwordET.setText("12345678")

        binding.btnSignIn.setOnClickListener {
            if (validateData()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val authResult = authRepo.signIn(requireContext(), email, password)

                    Log.d(TAG, " SIGN IN WITH $email successful")
                    if (authResult != null) {
                        saveToPrefs(email, password)
                        dataSource.currentUser = usersRepo.getUserFromUID(authResult)
                        if (dataSource.currentUser != null) {
                            println("Is not null")
                            val action =
                                SignInFragmentDirections.actionSignInFragmentToHomescreenFragment()
                            findNavController().navigate(action)
                        }
                        Log.d(TAG, "onViewCreated: ${dataSource.currentUser}")
                    }
                }
            }
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUp, null)
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