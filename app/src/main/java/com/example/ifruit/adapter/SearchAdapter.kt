package com.example.ifruit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ifruit.R
import com.example.ifruit.controller.fragment.FruitInfoFragment
import com.example.ifruit.model.Fruit

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var name: TextView
    lateinit var price: TextView
    lateinit var qlt: TextView

    fun searchViewHolder(itemView: View){
        super.itemView
        initViews(itemView)

    }

    private fun initViews(itemView: View) {
        name=itemView.findViewById(R.id.name_text) as TextView
        price=itemView.findViewById(R.id.price_text) as TextView
        qlt=itemView.findViewById(R.id.qlt_text) as TextView

    }


}

class SearchAdapter(context: FruitInfoFragment, allFruit: MutableList<Fruit>) : RecyclerView.Adapter<SearchViewHolder>() {

    private var context: FruitInfoFragment =context
    private var allFruit: MutableList<Fruit> = allFruit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater:LayoutInflater=LayoutInflater.from(parent.context)
        val itemView:View=inflater.inflate(R.layout.layout_fruit_item,parent,false)
        return SearchViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.name.setText(allFruit.get(position).name)
        holder.price.setText(allFruit.get(position).price.toString())
        holder.qlt.setText(allFruit.get(position).qlt.toString())
    }

    override fun getItemCount(): Int {
        return allFruit.size
    }

}