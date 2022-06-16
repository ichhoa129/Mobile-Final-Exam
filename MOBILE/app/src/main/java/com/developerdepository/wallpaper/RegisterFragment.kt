package com.developerdepository.wallpaper

<<<<<<< HEAD
import android.content.ContentValues.TAG
=======
import android.content.Intent
>>>>>>> ae466978d4787083b5b32a7ca18b879eb13a6e2d
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
<<<<<<< HEAD
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
=======
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
>>>>>>> ae466978d4787083b5b32a7ca18b879eb13a6e2d
import kotlinx.android.synthetic.main.activity_main.*
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

        btn_login.setOnClickListener { view: View? ->
            login()
<<<<<<< HEAD
//            val user = FirebaseAuth.getInstance().currentUser
//            user?.getIdToken(true)?.addOnSuccessListener { result ->
//                val idToken = result?.token
//                Log.d(TAG, "GetTokenResult result = $idToken")
//            }
=======
>>>>>>> ae466978d4787083b5b32a7ca18b879eb13a6e2d
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