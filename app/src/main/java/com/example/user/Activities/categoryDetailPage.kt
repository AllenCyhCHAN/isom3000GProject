package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import com.example.user.Models.Category
import com.example.user.Models.DataDriver
import com.example.user.R


class categoryDetailPage : AppCompatActivity() {




    lateinit var cat: Category;
    lateinit var masterDriver: DataDriver;

    lateinit var editCategoryName  : EditText
    lateinit var editCategoryDetailDescription  : EditText
    lateinit var editCategoryColour : EditText

    var editable = false;

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail_page)
        cat = intent.getSerializableExtra("data") as Category
        masterDriver = intent.getSerializableExtra("driver") as DataDriver

        editCategoryName = findViewById<EditText>(R.id.editCategoryName)
        editCategoryDetailDescription = findViewById<EditText>(R.id.editCategoryDetailDescription)
        editCategoryColour = findViewById<EditText>(R.id.editCategoryColour)

        editCategoryName.setText(cat.getCategoryName().toString() )
        editCategoryDetailDescription.setText( cat.getCategoryDescription().toString())
        editCategoryColour.setText( cat.getCategoryColor().toString())

    }



    fun editSwitch(view: View) {

        editable = !editable

        if (editable) {
            editCategoryName.setInputType(InputType.TYPE_CLASS_TEXT);
            editCategoryDetailDescription.setInputType(InputType.TYPE_CLASS_TEXT);
            editCategoryColour.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            editCategoryName.setInputType(InputType.TYPE_NULL);
            editCategoryDetailDescription.setInputType(InputType.TYPE_NULL);
            editCategoryColour.setInputType(InputType.TYPE_NULL);
        }
    }

    fun saveData(view: View){
        cat.setCategoryColor(editCategoryColour.getText().toString());
        cat.setCategoryName(editCategoryDetailDescription.getText().toString());
        cat.setCategoryDescription(editCategoryName.getText().toString());
        masterDriver.updateCategory(cat)
        startActivity(Intent(this, MainActivity2::class.java).putExtra("driver",masterDriver))

    }

}