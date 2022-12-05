package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.user.Models.Category
import com.example.user.Models.DataDriver
import com.example.user.R
import com.example.user.databinding.ActivityCategoryListPageBinding

class categoryListPage : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryListPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryListPageBinding.inflate(layoutInflater)
        setContentView(binding.root);

        val masterdatadriver = intent.getSerializableExtra("driver") as DataDriver
        val categoryList = masterdatadriver.getCategoryList()
        val categoryNameList = arrayListOf<String>()
        val categoryDes = arrayListOf<String>()
        val categoryColour = arrayListOf<String>()

        for (category in categoryList) {
            categoryNameList.add(category.getCategoryName())
            categoryDes.add(category.getCategoryDescription())
            categoryColour.add(category.getCategoryColor())

        }

        /* var listView = findViewById<ListView>(R.id.listview1);
        val arrayAdapter: ArrayAdapter<*>

        arrayAdapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, categoryNameList)
        listView.adapter  = arrayAdapter */
        binding.listview1.isClickable = true
        binding.listview1.adapter = CategoryList_Adapater(this, categoryList as ArrayList<Category>)
        binding.listview1.setOnItemClickListener { parent, view, position, id ->

            val name = categoryNameList[position]
            val colour = categoryColour[position]
            val description = categoryDes[position]
            var category_temp = Category()

            for (category in categoryList) {
                if (category.getCategoryName().equals(name)){
                    category_temp = category;
                }

            }


            val i = Intent(this, categoryDetailPage::class.java)
            category_temp = Category()
            i.putExtra("data",category_temp)
            i.putExtra("driver",masterdatadriver)

            startActivity(i)


        }

    }
}