package com.example.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_category_detail_page.*


class categoryDetailPage : AppCompatActivity() {

    val cat = intent.getSerializableExtra("cat") as Category
    val datadriver = DataDriver();
    var editable = false;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail_page)

        val cat = intent.getSerializableExtra("cat") as Category

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
        datadriver.updateCategory(cat)
    }

}