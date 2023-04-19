package com.lakshminarayana.pedometer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
//import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val firebaseAuthManager = FirebaseAuthManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_login = findViewById<Button>(R.id.btn_login)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_password)
        val tv_signup = findViewById<TextView>(R.id.tv_signup)

        btn_login.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            firebaseAuthManager.login(email, password, ::onSignInSuccess, ::onSignInFailure)
        }

        tv_signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun onSignInSuccess(user: FirebaseUser?) {
        Toast.makeText(this, "Signed in successfully as ${user?.email}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, UserSetup::class.java))
        finish()
    }

    private fun onSignInFailure(e: Exception?) {
        Toast.makeText(this, "Failed to sign in: ${e?.message}", Toast.LENGTH_SHORT).show()
    }
}
