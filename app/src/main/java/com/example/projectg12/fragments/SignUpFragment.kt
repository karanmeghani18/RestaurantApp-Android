package com.example.projectg12.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projectg12.databinding.FragmentSignUpBinding
import com.example.projectg12.models.DataSource
import com.example.projectg12.models.Order
import com.example.projectg12.models.User
import com.example.projectg12.repository.AuthRepo
import com.example.projectg12.repository.UsersRepo
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class SignUpFragment : Fragment() {
    val TAG: String = "Project G12"
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var authRepo: AuthRepo
    private lateinit var usersRepo: UsersRepo
    private lateinit var datasource: DataSource
    var email = ""
    var password = ""
    var name = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        datasource = DataSource.getInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authRepo = AuthRepo()
        usersRepo = UsersRepo()

        binding.btnSignUp.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()
            val name = binding.nameET.text.toString()
            signUpUser(email, password, name)
            val action = SignUpFragmentDirections.actionSignUpToHomeScreen()
            findNavController().navigate(action, null)
        }
    }

    private fun signUpUser(email: String, password: String, name: String) {
        if (validateData()) {
            lifecycleScope.launch {
                val userUIDFromAuth = authRepo.createAccount(requireContext(), email, password)

                if (userUIDFromAuth != null) {
                    val newUser: User = User(
                        id = userUIDFromAuth,
                        cartItemIds = listOf(),
                        email = email,
                        name = name,
                        orderHistory = listOf<Order>(
                            Order(
                                Timestamp.now(),
                                listOf<String>("123", "321"),
                                123.23
                            )
                        ),
                    )
                    usersRepo.addUserToDB(newUser)
                    saveToPrefs(email, password, name)
                    datasource.currentUser = newUser

                } else {
                    Toast.makeText(requireContext(), "Unable to create user", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }
    }

    private fun validateData(): Boolean {
        var validData = true
        email = ""
        password = ""
        name = binding.nameET.text.toString()
        if (binding.emailET.text.toString().isEmpty()) {
            binding.emailET.error = "Email Cannot be Empty"
            validData = false
        } else {
            email = binding.emailET.text.toString()
        }
        if (name.isBlank()) {
            binding.nameET.error = "Username is invalid"
        }
        if (binding.passwordET.text.toString().isEmpty()) {
            binding.passwordET.error = "Password Cannot be Empty"
            validData = false
        } else {
            if (binding.password2ET.getText().toString().isEmpty()) {
                binding.password2ET.setError("Confirm Password Cannot be Empty")
                validData = false
            } else {
                if (binding.passwordET.text.toString() != binding.password2ET.text.toString()
                ) {
                    binding.password2ET.error = "Both passwords must be same"
                    validData = false
                } else {
                    password = binding.passwordET.text.toString()
                }
            }
        }
        return validData
    }

    private fun saveToPrefs(email: String, password: String, name: String) {
        Log.d(TAG, "SignUpFragment: Saving to prefs")
        val prefs = requireContext().getSharedPreferences(
            requireContext().toString(),
            AppCompatActivity.MODE_PRIVATE
        )

        prefs.edit().putString("USER_EMAIL", email).apply()
        prefs.edit().putString("USER_PASSWORD", password).apply()
        prefs.edit().putString("NAME", name).apply()
    }


}