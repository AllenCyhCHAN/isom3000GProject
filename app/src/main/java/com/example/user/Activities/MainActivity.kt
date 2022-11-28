package com.example.user.Activities

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.user.Models.DataDriver
import com.example.user.R
import java.io.File


class MainActivity : AppCompatActivity() {

    lateinit var btn: Button;
    lateinit var btn2: Button;
    lateinit var btn3: Button;
    lateinit var btn4: Button
    lateinit var hello: TextView;
    private lateinit var masterDataDriver: DataDriver;


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        var fileDir: File = filesDir;
        this.masterDataDriver = DataDriver(fileDir);

        btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener{
            masterDataDriver.createPassword("AllenCHAN")
            println(masterDataDriver.getCurrentUser().getPassword());
        };


        btn2 = findViewById(R.id.button2)
        btn2.setOnClickListener{
//            masterDataDriver.createPassword("AllenCHAN")
            startActivity(Intent(
                this, BudgetDetails::class.java)
                .putExtra("data",masterDataDriver)
            )
        };



        hello = findViewById(R.id.textView);
        btn3 = findViewById(R.id.button3)
        btn3.setOnClickListener{
            masterDataDriver.getCurrentBudgetItem().setBudgetAmount(100.0);
            hello.setText(masterDataDriver.getCurrentBudgetItem().getBudgetAmount().toString());
//            masterDataDriver.createPassword("Addoil20010717");
//            println(masterDataDriver.getPassword());
//            masterDataDriver.createNewCategory("Other", "", "");
//            masterDataDriver.createNewExpense("Testing", "Other", 100.0,1,
//            "","","","",false);
//            println(masterDataDriver.getCategoryList().get(0));
//            println(masterDataDriver.getExpenseItemList().get(0));
        }




        btn = findViewById(R.id.button)
        btn.setOnClickListener{
            startActivity(
                Intent(
                    this, MainActivity2::class.java)
                    .putExtra("data",masterDataDriver)
            )
        }


    }


}