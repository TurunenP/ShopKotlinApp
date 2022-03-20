package com.example.shopkotlinapp

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var fAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        username = view.findViewById(R.id.login_username)
        password = view.findViewById(R.id.login_password)
        fAuth = Firebase.auth

        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            val navRegister = activity as FragmentNav
            navRegister.navigateFragment(RegisterFragment(), addToStack = false)
        }

        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            validateForm()
        }
        return view
    }

    private fun firebaseSignIn(){

        fAuth.signInWithEmailAndPassword(username.text.toString(),
            password.text.toString()).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                val navHome = activity as FragmentNav
                navHome.navigateFragment(HomePageFragment(),true)
            }
        }
    }
    private fun validateForm() {

        val icon = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.ic_error
        )

        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.minimumHeight)
        when {
            TextUtils.isEmpty(username.text.toString().trim()) -> {
                username.setError("Username is required", icon)
            }
            TextUtils.isEmpty(password.text.toString().trim()) -> {
                password.setError("Password is required", icon)
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() -> {

                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {

                    firebaseSignIn()

                 Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    username.setError("Please Enter a valid Email Id", icon)
                }
            }
        }
    }
}

