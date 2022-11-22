package com.example.user

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun main(){
    val budget = Budget(200.0, "2022/12/31");
    val path = System.getProperty("user.dir");
    val file = File(path, "Budget.txt");
    print(file.readLines());
}


class Budget{
    private var budgetAmount: Double = 0.0
    private var budgetStartingPeriod = "";
    private var budgetEndingPeriod:String = "";

    constructor(_budget: Double, _end:String){
        this.budgetAmount = _budget;
        this.budgetStartingPeriod = SimpleDateFormat("dd/M/yyyy").format(Calendar.getInstance().time);
        this.budgetEndingPeriod = _end;
        updateBudgetToStorage();
    }

    constructor(_budget: Double, _start:String, _end:String){
        this.budgetAmount = _budget;
        this.budgetStartingPeriod = _start;
        this.budgetEndingPeriod = _end;
    }

    constructor(){

    }

//    override fun toString(): String {
//        return "Budget starting date: $budgetStartingPeriod \nBudget ending date:   $budgetEndingPeriod \nRemaning budget:      $budgetAmount\n"
//    }

    fun updateBudgetToStorage(){
        val path = System.getProperty("user.dir");
        val file = File(path, "Budget.txt");
        var budgetHeaderString = "budgetAmount,budgetStartingDate,budgetEndingDate\n";
        var budgetContentString = "$budgetAmount,$budgetStartingPeriod,$budgetEndingPeriod\n";
        file.writeText(budgetHeaderString);
        file.appendText(budgetContentString);
    }

    fun setBudgetEndingPeriod(endingTime:String){
        this.budgetEndingPeriod = endingTime
        updateBudgetToStorage();
    }

    fun getBudgetEndingPeriod(): String{
        return this.budgetEndingPeriod;
    }

    fun setBudgetAmount(amount:Double){
        this.budgetAmount = amount;
        updateBudgetToStorage();
    }

    fun getBudgetAmount():Double{
        return this.budgetAmount
    }

    fun getExpenseToBudgetLevel(totalExpenseAmount: Double): Double {
        return (totalExpenseAmount / budgetAmount) * 100
    }
}