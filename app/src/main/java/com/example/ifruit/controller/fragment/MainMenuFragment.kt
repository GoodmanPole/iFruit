package com.example.ifruit.controller.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.ifruit.R
import com.example.ifruit.controller.MainActivity


class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {
    private lateinit var log_out: Button
    private lateinit var acc_btn: Button
    private lateinit var mng_btn: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        acc_btn.setOnClickListener {
            val action=MainMenuFragmentDirections.actionMainMenuFragmentToAccountingFragment()
            findNavController().navigate(action)
        }

        mng_btn.setOnClickListener {
            val action=MainMenuFragmentDirections.actionMainMenuFragmentToManagementFragment()
            findNavController().navigate(action)

        }

        log_out.setOnClickListener {
            requireActivity().run{
                startActivity(Intent(this, MainActivity::class.java))
                activity?.finish()
            }

        }




    }

    private fun initViews(view: View) {
        log_out=view.findViewById(R.id.log_out) as Button
        acc_btn=view.findViewById(R.id.acc_btn) as Button
        mng_btn=view.findViewById(R.id.mng_btn) as Button

    }
}