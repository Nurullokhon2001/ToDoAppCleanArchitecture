package com.example.to_doappcleanarchitecture.presentation.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_doappcleanarchitecture.R
import com.example.to_doappcleanarchitecture.databinding.FragmentListBinding
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.presentation.adapter.ListAdapter
import com.example.to_doappcleanarchitecture.presentation.vm.ListViewModel

class ListFragment : Fragment() {

    private val mToDoViewModel: ListViewModel by viewModels()
    private val listAdapter: ListAdapter by lazy { ListAdapter() }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var toDoArray : List<ToDoData> = emptyList()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        binding.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }
        binding.listLayout.adapter = listAdapter
        binding.listLayout.layoutManager = LinearLayoutManager(requireContext())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                listAdapter.submitList(it)
                toDoArray=it
            } else {
                binding.ivNoData.visibility = View.VISIBLE
                binding.tvNoData.visibility = View.VISIBLE
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_delete_all -> {
                removeAllData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeAllData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->

            if (toDoArray.isNotEmpty()){
                mToDoViewModel.deleteAll()
                toDoArray = emptyList()
                listAdapter.submitList(toDoArray)
                Toast.makeText(
                    requireContext(),
                    "Successfully Removed ",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                Toast.makeText(
                    requireContext(),
                    "The database is already empty :(",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("All data deleted")
        builder.setMessage("Are you sure you want remove all data ?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}