package com.example.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun nextPage(view:View){
        val testCat = Category( "cat name", "description des des des", "red")
        val intent = Intent(this, categoryDetailPage::class.java)
            .putExtra("Category", testCat);

        startActivity(intent);
    }

}
