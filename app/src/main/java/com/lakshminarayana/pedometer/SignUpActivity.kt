package com.lakshminarayana.pedometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
//import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val firebaseAuthManager = FirebaseAuthManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btn_signup = findViewById<Button>(R.id.btn_login)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_confirm_password = findViewById<EditText>(R.id.et_confirm_password)

        btn_signup.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val confirmPassword = et_confirm_password.text.toString()

            if (password == confirmPassword) {
                firebaseAuthManager.signUp(email, password, ::onSignUpSuccess, ::onSignUpFailure)
            }
            else{
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

        val tv_login = findViewById<TextView>(R.id.tv_login)

        tv_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun onSignUpSuccess(user: FirebaseUser?) {
        Toast.makeText(this, "Signed up successfully as ${user?.email}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, UserSetup::class.java))
        finish()
    }

    private fun onSignUpFailure(e: Exception?) {
        Toast.makeText(this, "Failed to sign up: ${e?.message}", Toast.LENGTH_SHORT).show()
    }
}
