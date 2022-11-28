package com.example.user.Models

import java.io.File
import java.io.Serializable
import java.util.*

class ExpenseItem: Serializable {
    private var id:String = "";
    private var expenseName: String = "";
    private var categoryName = "";
    private var totalAmount = 0.0;
    private var numberOfSplit = 0;
    private var personalAmount: Double = 0.0;
    private var shop: String = "";
    private var entryDatetime: String = "";
    private var paymentMethod = "";
    private var note = "";
    private var hasSettled = false;
    lateinit var fileDir:File;

    constructor(fileDir:File ,_expenseName: String, _category: String, _totalAmount: Double, _numberOfSplit: Int, _shop: String, _entryDatetime: String, _paymentMethod: String, _note: String, _hasSettled: Boolean){
        this.fileDir = fileDir;
        id = UUID.randomUUID().toString();
        expenseName = _expenseName;
        categoryName = _category;
        totalAmount = _totalAmount;
        numberOfSplit = _numberOfSplit;
        shop = _shop;
        entryDatetime = _entryDatetime;
        paymentMethod = _paymentMethod;
        note = _note;
        personalAmount = totalAmount/numberOfSplit;
        hasSettled = _hasSettled;
        appendExpenseItem();
    }

    constructor(fileDir:File ,_expenseName: String, _category: String, _totalAmount: Double, _shop: String, _entryDatetime: String, _paymentMethod: String, _note: String, _hasSettled: Boolean){
        this.fileDir = fileDir
        id = UUID.randomUUID().toString();
        expenseName = _expenseName;
        categoryName = _category;
        totalAmount = _totalAmount;
        numberOfSplit = 1;
        shop = _shop;
        entryDatetime = _entryDatetime;
        paymentMethod = _paymentMethod;
        note = _note;
        personalAmount = totalAmount;
        hasSettled = _hasSettled;
        appendExpenseItem();
    }

    constructor(fileDir:File , _id:String, _expenseName: String, _category: String, _totalAmount: Double, _numberOfSplit: Int, _shop: String, _entryDatetime: String, _paymentMethod: String, _note: String, _personalAmount: Double, _hasSettled: Boolean){
        this.fileDir = fileDir;
        id = _id;
        expenseName = _expenseName;
        categoryName = _category;
        totalAmount = _totalAmount;
        numberOfSplit = _numberOfSplit;
        shop = _shop;
        entryDatetime = _entryDatetime;
        paymentMethod = _paymentMethod;
        note = _note;
        personalAmount = _personalAmount;
        hasSettled = _hasSettled;
    }

    fun appendExpenseItem(){
        val path = this.fileDir;
        val file = File(path, "data/ExpenseItemList.txt");
        val newExpenseItemString = "$id,$expenseName,$categoryName,$totalAmount,$numberOfSplit,$personalAmount,$shop,$entryDatetime,$paymentMethod,$note,$hasSettled\n";
        if (file.length().toInt() == 0) {
            var budgetHeaderString =
                "id,expenseName,categoryName,totalAmount,numberOfSplit,personalAmount,shop,entryDatetime,paymentMethod,note,hasSettled\n";
            file.writeText(budgetHeaderString);
        }
        file.appendText(newExpenseItemString)
    }

    fun getID():String{
        return this.id;
    }

    fun getExpenseName():String{
        return this.expenseName;
    }

    fun setExpenseName(_expenseName:String){
        expenseName = _expenseName;
    }

    fun getCategoryName():String{
        return this.categoryName;
    }

    fun setCategoryName(_category:String){
        categoryName = _category;
    }

    fun getTotalAmount():Double{
        return this.totalAmount;
    }

    fun setTotalAmount(amount:Double){
        totalAmount = amount;
    }

    fun getNumberOfSplit():Int{
        return this.numberOfSplit;
    }

    fun setNumberOfSplit(number:Int){
        numberOfSplit = number;
        personalAmount = totalAmount/numberOfSplit;
    }

    fun setShop(_shop:String){
        shop = _shop;
    }

    fun getShop():String{
        return this.shop;
    }

    fun getEntryDatetime():String{
        return this.entryDatetime;
    }

    fun setEntryDatetime(_entryDatetime: String){
        this.entryDatetime = _entryDatetime;
    }

    fun getPaymentMethod():String{
        return this.paymentMethod;
    }

    fun setPaymentMethod(_paymentMethod: String){
        this.paymentMethod = _paymentMethod;
    }

    fun getNote():String{
        return this.note;
    }

    fun setNote(_note: String){
        this.note = _note;
    }

    fun getHasSettled():Boolean{
        return this.hasSettled;
    }

    fun setHasSettled(_hasSettled: Boolean){
        this.hasSettled = _hasSettled;
    }

    fun getPersonalAmount():Double{
        return this.personalAmount;
    }
}