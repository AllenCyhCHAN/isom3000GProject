package com.example.user
import org.mindrot.jbcrypt.BCrypt
import java.io.File


class User() {
    private var hashedPassword: String = "";

    constructor(_hashedPassword: String) : this() {
        hashedPassword = _hashedPassword;
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
        val path = System.getProperty("user.dir");
        val file = File(path, "UserPassword.txt");
        file.writeText(_hashedPassword);
    }

}