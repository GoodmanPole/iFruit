package com.example.ifruit.controller.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ifruit.R
import com.example.ifruit.model.Fruit
import com.example.ifruit.sql.DatabaseHelper


class AddFruitFragment : Fragment(R.layout.fragment_add_fruit) {

    private lateinit var addFruit_btn: Button
    private lateinit var id_edittext: EditText
    private lateinit var name_edittext: EditText
    private lateinit var price_edittext: EditText
    private lateinit var qlt_edittext: EditText
    private var db: DatabaseHelper?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onViewCreated(view, savedInstanceState)
        db=DatabaseHelper(requireContext())

        initViews(view)

        addFruit_btn.setOnClickListener {
            addFruit(db)
            Toast.makeText(requireContext(),"اطلاعات ثبت شد",Toast.LENGTH_LONG).show()
        }





    }

    private fun addFruit(db: DatabaseHelper?) {
        val fruit1= Fruit(
                id_edittext.text.toString().toInt(),
                name_edittext.text.toString()
                ,price_edittext.text.toString().toInt(),
                qlt_edittext.text.toString().toInt()
        )
        db!!.addFruit(fruit1)

    }

    private fun initViews(view: View) {
        addFruit_btn=view.findViewById(R.id.fruitinfo_btn) as Button
        id_edittext=view.findViewById(R.id.fruit_id) as EditText
        name_edittext=view.findViewById(R.id.fruit_name) as EditText
        price_edittext=view.findViewById(R.id.fruit_price) as EditText
        qlt_edittext=view.findViewById(R.id.fruit_quality) as EditText

    }
}