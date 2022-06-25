package com.example.to_doappcleanarchitecture.firebase

import com.google.firebase.auth.FirebaseAuth

class AppFirebaseRepository : FireBaseRepository {

    private val mAuth = FirebaseAuth.getInstance()

    override fun connectToFirebase(
        onSuccess: () -> Unit, onFail: (String) -> Unit, email: String,
        password: String
    ) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}