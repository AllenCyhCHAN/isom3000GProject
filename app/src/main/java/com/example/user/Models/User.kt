package com.example.user.Models
import org.mindrot.jbcrypt.BCrypt
import java.io.File
import java.io.Serializable


class User(): Serializable {
    lateinit var fileDir:File;
    private var hashedPassword: String = "";

    constructor(fileDir:File ,_hashedPassword: String) : this() {
        this.fileDir = fileDir;
        hashedPassword = _hashedPassword;
    }

    constructor(fileDir:File) : this() {
        this.fileDir = fileDir;
    }

    fun getPassword():String{
        return this.hashedPassword;
    }

    fun createPassword(_stringPassword:String){
        hashedPassword = BCrypt.hashpw(_stringPassword, BCrypt.gensalt());
        updateStoredPassword(hashedPassword);
    }

    fun login(inputPassword: String):Boolean{
        return BCrypt.checkpw(inputPassword,this.hashedPassword)
    }

    fun validatePassword(inputPassword: String): Boolean{
        return BCrypt.checkpw(inputPassword,this.hashedPassword);
    }

    //dun care if the new pw equals to the original one > update it whatever
    fun changePassword(_newStringPassword:String){
        hashedPassword = BCrypt.hashpw(_newStringPassword, BCrypt.gensalt());
        updateStoredPassword(hashedPassword);
    }

    private fun updateStoredPassword(_hashedPassword:String){
        val path = this.fileDir;
        val file = File(path, "UserPassword.txt");
        if (file.length().toInt() == 0){
            file.setWritable(true)
        }
        file.writeText(_hashedPassword);
    }

}