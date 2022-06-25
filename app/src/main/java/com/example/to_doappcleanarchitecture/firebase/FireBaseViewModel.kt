package com.example.to_doappcleanarchitecture.firebase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class FireBaseViewModel(application: Application) : AndroidViewModel(application) {

    fun onSuccess() {
        Log.e("FireBase", "onSuccess: ")
    }

    fun onFail(str: String) {
        Log.e("FireBase", "$str")
    }

    fun initFireBase(email: String, password: String) {
        val repo = AppFirebaseRepository()
        repo.connectToFirebase({ onSuccess() }, { onFail(it) }, email, password)
    }

}