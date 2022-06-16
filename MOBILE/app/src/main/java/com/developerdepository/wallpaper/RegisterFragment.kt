package com.developerdepository.wallpaper

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        edit_pass.transformationMethod = PasswordTransformationMethod.getInstance()

        btn_login.setOnClickListener { view: View? ->
            login()
        }
    }
    override fun onStart() {
        super.onStart()

    }
    fun login(){
        val email = edit_email.text.toString()
        val pass = edit_pass.text.toString()
        firebaseAuth!!.signInWithEmailAndPassword(email!!, pass!!)
            .addOnCompleteListener(requireActivity()) { task ->  // <<< CHANGE WAS MADE HERE !
                try{
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    println("createUserWithEmail:success")
                    navController!!.navigate(R.id.action_registerFragment_to_wallpapersCategoryFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    println("Authentication failed.")

                }}
                catch (e:Exception){
                    Log.d(e.toString() , "error")
                }
            }
    }
}