package com.example.to_doappcleanarchitecture.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doappcleanarchitecture.R
import com.example.to_doappcleanarchitecture.presentation.adapter.ListAdapter
import com.example.to_doappcleanarchitecture.presentation.vm.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private val mToDoViewModel: ListViewModel by viewModels()
    private val listAdapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val floatButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val listLayout = view.findViewById<RecyclerView>(R.id.list_layout)
        listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }
        listLayout.adapter = listAdapter
        listLayout.layoutManager = LinearLayoutManager(requireContext())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })

        setHasOptionsMenu(true)

        return view
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
            mToDoViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully Removed ",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("All data deleted")
        builder.setMessage("Are you sure you want remove all data ?")
        builder.create().show()
    }
}