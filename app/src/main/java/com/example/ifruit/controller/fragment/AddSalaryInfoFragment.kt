package com.example.ifruit.controller.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ifruit.R
import com.example.ifruit.model.Employee
import com.example.ifruit.model.Fruit
import com.example.ifruit.sql.DatabaseHelper


class AddSalaryInfoFragment : Fragment(R.layout.fragment_add_salary_info) {
    private lateinit var addEmployee_btn: Button
    private lateinit var id_edittext: EditText
    private lateinit var name_edittext: EditText
    private lateinit var salary_edittext: EditText
    private lateinit var phoneNumber_edittext: EditText
    private var db: DatabaseHelper?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onViewCreated(view, savedInstanceState)
        db= DatabaseHelper(requireContext())

        initViews(view)

        addEmployee_btn.setOnClickListener {
            addEmployee(db)
            Toast.makeText(requireContext(),"اطلاعات ثبت شد", Toast.LENGTH_LONG).show()
        }





    }

    private fun addEmployee(db: DatabaseHelper?) {
        val employee= Employee(
                id_edittext.text.toString().toInt(),
                name_edittext.text.toString()
                ,salary_edittext.text.toString().toInt(),
                phoneNumber_edittext.text.toString().toInt()
        )
        db!!.addEmployee(employee)

    }

    private fun initViews(view: View) {
        addEmployee_btn=view.findViewById(R.id.submit_btn) as Button
        id_edittext=view.findViewById(R.id.employee_id) as EditText
        name_edittext=view.findViewById(R.id.employee_name) as EditText
        salary_edittext=view.findViewById(R.id.employee_salary) as EditText
        phoneNumber_edittext=view.findViewById(R.id.employee_phonenumber) as EditText

    }

}