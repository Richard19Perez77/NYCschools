package com.practice.nycschools.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practice.nycschools.FirstFragment
import com.practice.nycschools.R
import com.practice.nycschools.model.NYCListClass

class DataAdapter(private var dataList: List<NYCListClass?>?, private var first: FirstFragment) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.listitem, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val school = dataList?.get(position)?.schoolName
        holder.idTextview.text = school
        holder.idTextview.setOnClickListener { dataList?.get(position)?.let { it1 ->
            first.startSecond(
                it1.dbn)
        } }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var idTextview: TextView

    init {
        idTextview = itemView.findViewById(R.id.textId)
    }
}