package com.example.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {


    lateinit var textView: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textView = findViewById(R.id.main);

        val dataDriver = intent.getSerializableExtra("data") as DataDriver;
        textView.setText(dataDriver.getTotalExpense().toString());
    }


}