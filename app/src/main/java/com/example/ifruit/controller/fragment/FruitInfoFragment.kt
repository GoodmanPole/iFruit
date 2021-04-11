package com.example.ifruit.controller.fragment

import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.adapter.SearchAdapter
import com.example.ifruit.sql.DatabaseHelper
import com.mancj.materialsearchbar.MaterialSearchBar
import android.text.TextWatcher as TextWatcher1


class FruitInfoFragment : Fragment(R.layout.fragment_fruit_info) {

    private lateinit var addFruit_btn: View
    private lateinit var listView: ListView
    private lateinit var empty_view: View
    private lateinit var adapter: ArrayAdapter<String>
    private var db:DatabaseHelper?=null
    var listFruit:List<String> = listOf<String>("")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        db=DatabaseHelper(requireContext())

        var result= db!!.readFruitData()
        for (i in 0..(result?.size-1)){
            listFruit+= result.get(i).name.toString()
        }



        initViews(view)
        listView.adapter=adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireContext(),parent?.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show()
        }
        listView.emptyView=empty_view

//        System.out.println(db?.allFruit)





        addFruit_btn.setOnClickListener {
            val action=FruitInfoFragmentDirections.actionFruitInfoFragmentToAddFruitFragment()
            findNavController().navigate(action)
        }


        }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)

        var searchItem:MenuItem=menu.findItem(R.id.item_search)
        var searchView:SearchView=MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.queryHint="جستجو میوه با نام"


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
        addFruit_btn=view.findViewById(R.id.add_fruit) as View
        adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                listFruit
//            resources.getStringArray(R.array.test_array)
        )
        listView=view.findViewById(R.id.lv_listview) as ListView
        empty_view=view.findViewById(R.id.tv_textview)





    }


}