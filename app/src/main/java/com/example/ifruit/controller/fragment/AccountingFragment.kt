package com.example.ifruit.controller.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.ifruit.R


class AccountingFragment : Fragment(R.layout.fragment_accounting) {

    private lateinit var accInfo_btn: Button
    private lateinit var fruitInfo_btn: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        accInfo_btn.setOnClickListener {
            val action = AccountingFragmentDirections.actionAccountingFragmentToAcountingInfoFragment()
            findNavController().navigate(action)
        }

        fruitInfo_btn.setOnClickListener {
            val action=AccountingFragmentDirections.actionAccountingFragmentToFruitInfoFragment()
            findNavController().navigate(action)
   }

        }





    private fun initViews(view: View) {
        accInfo_btn=view.findViewById(R.id.accinfo_btn) as Button
        fruitInfo_btn=view.findViewById(R.id.fruitinfo_btn) as Button

    }
}
