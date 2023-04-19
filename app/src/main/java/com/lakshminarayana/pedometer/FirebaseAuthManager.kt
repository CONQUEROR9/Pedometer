package com.lakshminarayana.pedometer

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthManager {
    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, onSuccess: (FirebaseUser?) -> Unit, onFailure: (Exception?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result -> onSuccess(result.user) }
            .addOnFailureListener { e -> onFailure(e) }
    }

    fun signUp(email: String, password: String, onSuccess: (FirebaseUser?) -> Unit, onFailure: (Exception?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result -> onSuccess(result.user) }
            .addOnFailureListener { e -> onFailure(e) }
    }
}
