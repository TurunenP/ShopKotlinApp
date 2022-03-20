package com.example.shopkotlinapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    private lateinit var username:EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var fAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.reg_username)
        password = view.findViewById(R.id.reg_password)
        confirmPassword = view.findViewById(R.id.reg_confirm_password)
        fAuth = Firebase.auth

        view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
            val navRegister = activity as FragmentNav
            navRegister.navigateFragment(LoginFragment(), addToStack = false)
        }
        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            validateEmptyForm()
        }
        return view
    }

    private fun firebaseSignUp() {

       //make user to register once - kotlin email firebase = 10mins(part four)

        fAuth.createUserWithEmailAndPassword(username.text.toString(),
            confirmPassword.text.toString()).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                val navHome = activity as FragmentNav
                navHome.navigateFragment(HomePageFragment(),true)
            } else {
                Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

private fun validateEmptyForm() {
    val icon = AppCompatResources.getDrawable(requireContext(),
    R.drawable.ic_error)

    icon?.setBounds(0,0,icon.intrinsicWidth,icon.minimumHeight)
    when {
        TextUtils.isEmpty(username.text.toString().trim())-> {
            username.setError("Username is required", icon)
        }
        TextUtils.isEmpty(password.text.toString().trim())-> {
            password.setError("Password is required", icon)
        }
        TextUtils.isEmpty(confirmPassword.text.toString().trim())-> {
            confirmPassword.setError("Confirm your password", icon)
        }

        username.text.toString().isNotEmpty() &&
                password.text.toString().isNotEmpty() &&
                confirmPassword.text.toString().isNotEmpty()->
        {
            if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {

                if (password.text.toString().length >=6){

                    if (password.text.toString() == confirmPassword.text.toString()){
                        firebaseSignUp()

                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        confirmPassword.setError("Password does not match", icon)
                    }
                }
                else{
                    password.setError("Please Enter at least six characters", icon)
                }

            }else{
                username.setError("Please Enter a valid Email Id", icon)
            }
        }
    }
}
}
