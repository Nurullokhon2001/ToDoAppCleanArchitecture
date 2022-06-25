package com.example.to_doappcleanarchitecture.firebase

interface FireBaseRepository {

    fun connectToFirebase(
        onSuccess: () -> Unit,
        onFail: (String) -> Unit,
        email: String,
        password: String
    ) {
    }

    fun signOut() {}

}