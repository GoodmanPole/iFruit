package com.example.ifruit.controller.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.navigation.fragment.findNavController
import com.example.ifruit.R
import com.example.ifruit.sql.DatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SalaryFragment : Fragment(R.layout.fragment_salary) {
    private lateinit var add_salary: FloatingActionButton
    private lateinit var listView: ListView
    private lateinit var empty_view: View
    private lateinit var adapter: ArrayAdapter<String>
    private var db: DatabaseHelper?=null
    var listEmployee:List<String> = listOf<String>("")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        db=DatabaseHelper(requireContext())

        var result= db!!.readEmployeeData()
        for (i in 0..(result?.size-1)){
            listEmployee+= result.get(i).name.toString()
        }

        initViews(view)

        listView.adapter=adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireContext(),parent?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
        listView.emptyView=empty_view


        add_salary.setOnClickListener {
            val action=SalaryFragmentDirections.actionSalaryFragmentToAddSalaryInfoFragment()
            findNavController().navigate(action)

        }




    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)

        var searchItem: MenuItem =menu.findItem(R.id.item_search)
        var searchView:SearchView= MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.queryHint="جستجو کارمند با نام"


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
    }

    private fun initViews(view: View) {
        add_salary=view.findViewById(R.id.add_employee) as FloatingActionButton
        adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                listEmployee
//            resources.getStringArray(R.array.test_array)
        )
        listView=view.findViewById(R.id.lv_listview) as ListView
        empty_view=view.findViewById(R.id.tv_textview)

    }

}