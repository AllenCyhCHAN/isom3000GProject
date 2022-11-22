package com.example.user

import java.io.File
import kotlin.math.exp


//category list
//category details
//expense list
//expense details
//user details
//budget details


fun main(){
    val datadriver = DataDriver();
    var expenseItemList = datadriver.getExpenseItemList();
    var categoryList = datadriver.getCategoryList();

    for (category in categoryList){
        println(category.getCategoryName())
        println(category.getExpenseItemList().size)
    }

    for (expenseItem in expenseItemList){
        println(expenseItem.getExpenseName())
        println(expenseItem.getCategoryName())
    }
}

class DataDriver {

    private var categoryList = arrayListOf<Category>();
    private var expenseItemList = arrayListOf<ExpenseItem>();
    private var isLogin: Boolean = false;
    private var acIsCreated: Boolean = false;
    private var budgetIsCreated: Boolean = false;
    private var currentBudgetItem: Budget = Budget();
    private var currentUser: User = User();

    constructor(){
        initializeUser();
        initializeBudget();
        initializeCategory();
        initializeExpenseItem();
    }


    // for anders
    fun login(_password: String): Boolean{
        if (currentUser.login(_password))
            this.isLogin = true

        return this.isLogin;
    }

    fun validate(_password: String):Boolean{
        return this.validate(_password);
    }

    fun changePassword(_newPassword: String){
        currentUser.changePassword(_newPassword);
    }

    fun createPassword(_password: String){
        currentUser.createPassword(_password);
        this.acIsCreated = true;
    }

    //end of code for enders

    fun getExpenseItemList():List<ExpenseItem>{
        return this.expenseItemList;
    }


    // for anson
    fun getCategoryList():List<Category>{
        return this.categoryList;
    }

    //true if new category doesn't in existing category list
    //false otherwise
    fun checkIfCategoryExist(newCategory: Category):Boolean{
        for (category in categoryList){
            if (category.getID() == newCategory.getID()){
                return false
            }
        }
        return true
    }

    fun addCategoryToList(_categoryName: String, _categoryDescription: String, _categoryColor: String): Boolean{
        val categoryItem = Category(_categoryName, _categoryDescription, _categoryColor);
        if (checkIfCategoryExist(categoryItem)){
            categoryList.add(categoryItem);
            return true
        } else {
            return false
        }

    }

    fun removeCategory(categoryToBeRemoved: Category){
        var otherCategory = getOtherCategory()
        for (expense in categoryToBeRemoved.getExpenseItemList()){
            expenseItemList.remove(expense);
            expense.setCategoryName("other");
            otherCategory.appendExpenseToList(expense);
            expenseItemList.add(expense);
        }
        categoryList.remove(categoryToBeRemoved);
        updateExpenseItemListStorage();
        updateCategorytoStorage();
    }


    private fun getOtherCategory(): Category{
        for (category in categoryList){
            if (category.getCategoryName() == "other"){
                return category
            }
        }
        return Category("other");
    }

    //end of code for anson

    //code for sam
    //just retrieve from the category list and get one of them for testing now
    //eg categorylist.get(1)
    //for remove category, use removeCategory() in Anson part
    //for update category, use setters of category then call this updateCategory() function

    fun updateCategory(updatedCategory: Category){
        for (expenseItem in updatedCategory.getExpenseItemList()){
            expenseItem.setCategoryName(updatedCategory.getCategoryName())
        }

        updateCategorytoStorage();
        updateExpenseItemListStorage();
    }

    //end of code for sam

    //code for jason
    //just retrieve from the expenseItemList and get one of them for testing now
    //eg expenseItem.get(1)
    //for removing expenseItem,  use removeExpenseItem funciton
    fun removeExpenseItem(expenseItem: ExpenseItem){
        for (category in categoryList){
            if (category.getCategoryName() == expenseItem.getCategoryName()){
                category.getExpenseItemList().remove(expenseItem);
            }
        }

        expenseItemList.remove(expenseItem)
        updateCategorytoStorage();
        updateExpenseItemListStorage();
    }

    //for updateExpenseItem, use setter of expenseItem then call this updateExpenseItem() function
    fun updateExpenseItem(updatedExpenseItem: ExpenseItem){
        for (category in categoryList){
            var tempExpenseItemList = category.getExpenseItemList();
            for (tempExpenseItem in tempExpenseItemList){
                if (tempExpenseItem.getID() == updatedExpenseItem.getID()){
                    tempExpenseItem.setCategoryName(updatedExpenseItem.getCategoryName());
                }
            }
        }

        updateCategorytoStorage();
        updateExpenseItemListStorage();
    }

    //end of code for jason



    fun getBudgetIsCreated():Boolean{
        return this.budgetIsCreated;
    }

    fun getCurrentBudgetItem():Budget{
        return this.currentBudgetItem;
    }

    fun getCurrentUser():User{
        return this.currentUser;
    }


    private fun initializeCategory(){
        val path = System.getProperty("user.dir");
        val file = File(path, "CategoryItemList.txt");
        val content = file.readLines();
        var hasOtherCategory = false;
        for (row in content){
            var rowContent = row.split(",");
            if (rowContent[0].equals("id"))
                continue;
            if (rowContent[0].length == 0)
                continue

            categoryList.add(Category(rowContent[0],rowContent[1], rowContent[2], rowContent[3]));
            if (rowContent[1] == "other"){
                hasOtherCategory = true;
            }
        }

        if (!hasOtherCategory){
            categoryList.add(Category("other"))
        }
    }

    private fun initializeExpenseItem() {
        val path = System.getProperty("user.dir");
        val file = File(path, "ExpenseItemList.txt");
        val content = file.readLines();
        for (row in content) {
            var rowContent = row.split(",");
            if (rowContent[0].equals("id"))
                continue;
            if (rowContent[0].isEmpty())
                continue

            var id = rowContent[0];
            var expenseName = rowContent[1];
            var categoryName = rowContent[2];
            var totalAmount: Double = 0.0;
            if (rowContent[3].isNotEmpty()){
                totalAmount = rowContent[3].toDouble();
            }
            var numberOfSplit = 1;
            if (rowContent[4].isNotEmpty()){
                numberOfSplit = rowContent[4].toInt();
            }
            var personalAmount = 0.0;
            if (rowContent[5].isNotEmpty()){
                personalAmount = rowContent[5].toDouble();
            }
            var shop = rowContent[6];
            var entryDatetime = rowContent[7]
            var paymentMethod = rowContent[8];
            var note = rowContent[9];

            var hasSettled = false;
            if (rowContent[10].isNotEmpty()){
                hasSettled = rowContent[10].toBoolean()
            }


            var expenseItem = ExpenseItem(
                id,
                expenseName,
                categoryName,
                totalAmount,
                numberOfSplit,
                shop,
                entryDatetime,
                paymentMethod,
                note,
                personalAmount,
                hasSettled
            )
            expenseItemList.add(expenseItem);
            appendExpenseItemToCategory(expenseItem);
        }
    }

    fun appendExpenseItemToCategory(expenseItem: ExpenseItem){
        for (category in categoryList){
            if (category.getID().equals(expenseItem.getID())){
                category.appendExpenseToList(expenseItem);
            }
        }
    }


    private fun initializeBudget() {
        var budget: Budget;
        val path = System.getProperty("user.dir");
        val file = File(path, "Budget.txt");
        if (file.canRead()) {
            val content = file.readLines()[1].split(",");
            this.currentBudgetItem = Budget(content[1][0].toDouble(), content[1], content[2]);
            this.budgetIsCreated = true;
        }
    }

    private fun initializeUser(){
        val path = System.getProperty("user.dir");
        val file = File(path, "UserPassword.txt");
        if (file.canRead()) {
            val password = file.readLines()[0];
            currentUser = User(password);
            this.acIsCreated = true;
        } else {
            currentUser = User();
        }
    }

    private fun updateCategorytoStorage(){
        val path = System.getProperty("user.dir");
        val categoryDetailsFile = File(path, "CategoryItemList.txt");
        categoryDetailsFile.writeText("id,categoryname, categoryDescription, categoryColor\n");
        for (category in this.categoryList){
            val newExpenseItemString = "${category.getID()},${category.getCategoryName()},${category.getCategoryDescription()},${category.getCategoryColor()}\n";
            categoryDetailsFile.appendText(newExpenseItemString);
        }
    }


    fun createNewExpense(_expenseName: String, _category: String, _totalAmount: Double, _numberOfSplit: Int, _shop: String, _entryDatetime: String, _paymentMethod: String, _note: String, _hasSettled: Boolean){
        val expenseItem = ExpenseItem(_expenseName,_category,_totalAmount,_numberOfSplit,_shop,_entryDatetime,_paymentMethod,_note,_hasSettled);
        expenseItemList.add(expenseItem);

    }

//    fun addExpenseItemToCategory(expenseItem: ExpenseItem){
//        for (category in categoryList){
//            if (category.getCategoryName().equals(expenseItem.getCategoryName())){
//                category.appendExpenseToList(expenseItem);
//            }
//        }
//    }


    fun createNewCategory(_name:String, _description:String, _color:String){
        val category = Category(_name, _description, _color);
        categoryList.add(category);
        updateCategorytoStorage();
    }


    private fun updateExpenseItemListStorage(){
        val path = System.getProperty("user.dir");
        val file = File(path, "ExpenseItemList.txt");
        var expenseHeaderString = "id, expenseName, categoryName, totalAmount, numberOfSplit, personalAmount, shop, entryDatetime, paymentMethod, note, hasSettled\n";
        file.writeText(expenseHeaderString);
        for (item in expenseItemList){
            val id = item.getID();
            val expenseName = item.getExpenseName();
            val category = item.getCategoryName();
            val totalAmount = item.getTotalAmount();
            val numberOfSplit = item.getNumberOfSplit()
            val personalAmount = item.getPersonalAmount();
            val shop = item.getShop();
            val entryDatetime = item.getEntryDatetime();
            val paymentMethod = item.getPaymentMethod();
            val note = item.getNote();
            val hasSettled = item.getHasSettled();
            val newExpenseItemString = "$id,$expenseName,$category,$totalAmount,$numberOfSplit,$personalAmount,$shop,$entryDatetime,$paymentMethod,$note,$hasSettled\n";
            file.appendText(newExpenseItemString);
        }
    }

    fun getTotalExpense(): Double{
        var totalExpense = 0.0;
        for (category in categoryList){
            totalExpense += category.getCategoryExpense();
        }

        return totalExpense;
    }





}