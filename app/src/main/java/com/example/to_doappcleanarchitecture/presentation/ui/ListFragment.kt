package com.example.to_doappcleanarchitecture.presentation.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_doappcleanarchitecture.R
import com.example.to_doappcleanarchitecture.databinding.FragmentListBinding
import com.example.to_doappcleanarchitecture.domain.model.ToDoData
import com.example.to_doappcleanarchitecture.presentation.adapter.ListAdapter
import com.example.to_doappcleanarchitecture.presentation.adapter.SwipeToDelete
import com.example.to_doappcleanarchitecture.presentation.vm.AddViewModel
import com.example.to_doappcleanarchitecture.presentation.vm.ListViewModel
import com.example.to_doappcleanarchitecture.presentation.vm.UpdateViewModel
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels()
    private val updateViewModel: UpdateViewModel by viewModels()
    private val addViewModel: AddViewModel by viewModels()

    private val listAdapter: ListAdapter by lazy { ListAdapter() }
    private var toDoArray: List<ToDoData> = emptyList()


    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

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
        swipeToDelete(binding.listLayout)
        binding.listLayout.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 500
        }

        listViewModel.getAllData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                listAdapter.submitList(it)
                toDoArray = it
            } else {
                binding.ivNoData.visibility = View.VISIBLE
                binding.tvNoData.visibility = View.VISIBLE
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun removeAllData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->

            if (toDoArray.isNotEmpty()) {
                listViewModel.deleteAll()
                toDoArray = emptyList()
                listAdapter.submitList(toDoArray)
                Toast.makeText(
                    requireContext(),
                    "Successfully Removed ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
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

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallBack = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = listAdapter.data[viewHolder.adapterPosition]
                updateViewModel.deleteData(itemToDelete)

                restoredDeletedItem(viewHolder.itemView, itemToDelete, viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoredDeletedItem(view: View, deletedItem: ToDoData, position: Int) {
        val snackBar = Snackbar.make(
            view, "Deleted ${deletedItem.title}",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo") {
            addViewModel.insertData(deletedItem)
            listAdapter.notifyItemChanged(position)
        }
        snackBar.show()
    }

    private fun searchDatabase(query: String) {
        val searQuery = "%$query%"
        listViewModel.searchDatabase(searQuery).observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    searchDatabase(it)
                    Log.e("onQueryTextSubmit", "onQueryTextSubmit: $it")
                }
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_delete_all -> {
                removeAllData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}