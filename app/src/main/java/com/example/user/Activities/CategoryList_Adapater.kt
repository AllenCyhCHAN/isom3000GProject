package com.example.user.Activities

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.user.Models.Category
import com.example.user.R

class CategoryList_Adapater(private val context: Activity, private val arrayList: ArrayList<Category>): ArrayAdapter<Category>(context,
    R.layout.category_listitem,arrayList) {

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.category_listitem,null)

        //val part to make details like colour shown on category list

        val categoryName : TextView = view.findViewById(R.id.categoryName)

        categoryName.text = arrayList[position].getCategoryName()

        return view


        //

        return super.getView(position, convertView, parent)
    }
}