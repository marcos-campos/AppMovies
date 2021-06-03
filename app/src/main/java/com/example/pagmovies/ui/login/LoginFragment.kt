package com.example.pagmovies.ui.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.pagmovies.MainActivity
import com.example.pagmovies.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment  : Fragment() {

    val btnLogin by lazy {view?.findViewById<Button>(R.id.login_btn_login)}
    val btnLogout by lazy {view?.findViewById<Button>(R.id.login_btn_logout)}
    val btnLoginGmail by lazy {view?.findViewById<ImageView>(R.id.login_iv_gmail)}
    val btnLoginFacebook by lazy {view?.findViewById<ImageView>(R.id.login_iv_facebook)}

    val etLogin by lazy {view?.findViewById<TextView>(R.id.login_et_user)}
    val etPassword by lazy {view?.findViewById<TextView>(R.id.login_et_password)}

    lateinit var auth: FirebaseAuth
    lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = context?.let { GoogleSignIn.getClient(it, googleSignInOptions) }!!

        btnLoginGmail?.setOnClickListener {
            signIn()
        }

        btnLogout?.setOnClickListener {
            signout()
        }

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
//        updateUI(currentUser)
        etLogin?.text = currentUser?.email
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)

            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    etLogin?.text = user?.email.toString()
                    etPassword?.text = "**********"

                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)

                }  else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    etLogin?.text = "erro"
                }
            }
    }

    fun signout(){
        auth.signOut()
        googleSignInClient.signOut()

        Toast.makeText(context, "Usuário desconectado", Toast.LENGTH_SHORT).show()

        etLogin?.text = ""
        etPassword?.text = ""
    }

}