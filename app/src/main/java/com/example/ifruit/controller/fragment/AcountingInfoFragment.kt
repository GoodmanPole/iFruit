package com.example.ifruit.controller.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.ifruit.R
import com.example.ifruit.controller.MainActivity

class AcountingInfoFragment : Fragment(R.layout.fragment_acounting_info) {
    private lateinit var income_btn: Button
    private lateinit var salary_btn: Button
//    private lateinit var mng_btn: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    (activity as AppCompatActivity).supportActionBar?.hide()
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        income_btn.setOnClickListener {
            val action=AcountingInfoFragmentDirections.actionAcountingInfoFragmentToIncomeFragment()
            findNavController().navigate(action)
        }

        salary_btn.setOnClickListener {
            val action=AcountingInfoFragmentDirections.actionAcountingInfoFragmentToSalaryFragment()
            findNavController().navigate(action)

        }
//
//        log_out.setOnClickListener {
//            requireActivity().run{
//                startActivity(Intent(this, MainActivity::class.java))
//                activity?.finish()
//            }
//
//        }




    }

    private fun initViews(view: View) {
        income_btn=view.findViewById(R.id.profit_btn) as Button
        salary_btn=view.findViewById(R.id.salary_btn) as Button
//        mng_btn=view.findViewById(R.id.mng_btn) as Button

    }

}