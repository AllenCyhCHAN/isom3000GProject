package com.example.user.Activities

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.user.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel


class BudgetDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_details)
        setData()
    }

    private fun setData(){
        var tvR: TextView
        var tvJava: TextView
        var pieChart: PieChart


        var startingDate = findViewById<TextView>(R.id.startingDate);
        var endingDate = findViewById<TextView>(R.id.endingDate);
        startingDate.setText("Budget Starting Date: 2022/1/1");
        endingDate.setText("Budget Ending Date: 2022/12/31");


        tvR = findViewById(R.id.tvR);
        tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);

        val expense = 20;
        val budget = 100 - expense

        tvR.setText(Integer.toString(expense));
        tvJava.setText(Integer.toString(budget));


// Set the data and color to the pie chart

// Set the data and color to the pie chart
        pieChart.addPieSlice(
            PieModel(
                "R", tvR.text.toString().toInt().toFloat(),
                Color.parseColor("#FFA726")
            )
        )

        pieChart.addPieSlice(
            PieModel(
                "Java", tvJava.text.toString().toInt().toFloat(),
                Color.parseColor("#29B6F6")
            )
        )


// To animate the pie chart
        pieChart.startAnimation();
    }
}