package com.example.to_doappcleanarchitecture.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.to_doappcleanarchitecture.R
import com.example.to_doappcleanarchitecture.databinding.FragmentUpdateBinding
import com.example.to_doappcleanarchitecture.presentation.model.Priority
import com.example.to_doappcleanarchitecture.presentation.vm.SharedViewModel

class UpdateFragment : Fragment() {

    private val mSharedViewModel: SharedViewModel by viewModels()

    private val args by navArgs<UpdateFragmentArgs>()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        binding.etTitle.setText(args.currentItem.title)
        binding.etDescription.setText(args.currentItem.description)
        binding.spPriority.setSelection(parsePriority(args.currentItem.priority))

        binding.spPriority.onItemSelectedListener = mSharedViewModel.listener

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parsePriority(priority: Priority): Int {
        return when (priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            else -> 2
        }
    }

}