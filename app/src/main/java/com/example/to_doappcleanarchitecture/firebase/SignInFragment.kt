package com.example.to_doappcleanarchitecture.firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.to_doappcleanarchitecture.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInFragment : Fragment() {

    val fireBaseViewModel by viewModel<FireBaseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)


        val email = view.findViewById<EditText>(R.id.et_login)

        val password = view.findViewById<EditText>(R.id.password)

        val btn = view.findViewById<Button>(R.id.next)

        btn.setOnClickListener {
            fireBaseViewModel.initFireBase(email.text.toString(), password.text.toString())
        }

        return view
    }
}