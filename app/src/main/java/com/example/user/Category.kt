package com.example.user

import java.io.File
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


class Category : Serializable{
    private var id: String = "";
    private var categoryName: String = "";
    private var categoryDescription: String = "";
    private var categoryColor: String = "";
    private var expenseItemList = arrayListOf<ExpenseItem>();

    constructor(){
    }

    constructor(_categoryName: String, _categoryDescription: String, _categoryColor: String){
        this.id = UUID.randomUUID().toString();
        this.categoryName = _categoryName;
        this.categoryDescription = _categoryDescription;
        this.categoryColor = _categoryColor;
        appendCategorytoStorage();
    }

    constructor(_id: String, _categoryName: String, _categoryDescription: String, _categoryColor: String){
        this.id = _id;
        this.categoryName = _categoryName;
        this.categoryDescription = _categoryDescription;
        this.categoryColor = _categoryColor;
    }

    constructor(_categoryName: String){
        this.id = UUID.randomUUID().toString();
        this.categoryName = _categoryName;
        appendCategorytoStorage();
    }

    constructor(_categoryName: String, testing: Boolean){
        this.id = UUID.randomUUID().toString();
        this.categoryName = _categoryName;
//        appendCategorytoStorage();
    }

    fun getID(): String{
        return id;
    }

    fun appendExpenseToList(expenseItem: ExpenseItem){
        this.expenseItemList.add(expenseItem);
    }

    fun getExpenseItemList(): ArrayList<ExpenseItem>{
        return this.expenseItemList;
    }

    fun removeExpenseToList(expenseItem: ExpenseItem){
        this.expenseItemList.remove(expenseItem);

    }


    fun appendCategorytoStorage(){
        val path = System.getProperty("user.dir");
        val file = File(path, "CategoryItemList.txt");
        val newExpenseItemString = "$id,$categoryName,$categoryDescription,$categoryColor\n";

        if (file.length() > 0){
            file.appendText(newExpenseItemString);
        } else {
            file.writeText("id,categoryname, categoryDescription, categoryColor\n");
            file.appendText(newExpenseItemString);
        }
    }

    fun getCategoryName():String{
        return this.categoryName;
    }

    fun setCategoryName(_updatedCategoryName:String){
        this.categoryName = _updatedCategoryName;
    }

    fun getCategoryDescription():String{
        return this.categoryDescription;
    }

    fun setCategoryDescription(_updatedCategoryDescription:String){
        this.categoryDescription = _updatedCategoryDescription;
    }

    fun getCategoryColor():String{
        return this.categoryColor;
    }

    fun setCategoryColor(_updatedCategoryColor:String){
        this.categoryColor = _updatedCategoryColor;
    }


    fun getCategoryExpense():Double{
        var totalExpense = 0.0;
        for (expenseItem in expenseItemList){
            totalExpense += expenseItem.getPersonalAmount();
        }
        return totalExpense;
    }

}