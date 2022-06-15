package com.example.to_doappcleanarchitecture.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.to_doappcleanarchitecture.R
import com.example.to_doappcleanarchitecture.databinding.FragmentUpdateBinding
import com.example.to_doappcleanarchitecture.domain.model.Priority
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.presentation.vm.SharedViewModel
import com.example.to_doappcleanarchitecture.presentation.vm.UpdateViewModel

class UpdateFragment : Fragment() {

    private val mSharedViewModel by viewModels<SharedViewModel>()
    private val updateViewModel by viewModels<UpdateViewModel>()

    private val args by navArgs<UpdateFragmentArgs>()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_to_do -> {
                updateData()
            }
            R.id.delete_to_do -> {
                removeData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            updateViewModel.deleteData(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed ${args.currentItem.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigateUp()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.title}'")
        builder.setMessage("Are you sure you want remove '${args.currentItem.title}' ?")
        builder.create().show()
    }

    private fun updateData() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val priority = binding.spPriority.selectedItem.toString()

        val validate =
            mSharedViewModel.verifyDataFromUser(title = title, description = description)

        val data = ToDoData(
            id = args.currentItem.id,
            title = title,
            priority = mSharedViewModel.parsePriority(priority),
            description = description
        )

        if (validate) {
            updateViewModel.updateData(data)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigateUp()

        } else {
            Toast.makeText(
                requireContext(),
                "Please fill out all fields.",
                Toast.LENGTH_SHORT
            ).show()
        }
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