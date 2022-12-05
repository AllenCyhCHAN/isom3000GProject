package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.user.Models.DataDriver
import com.example.user.R
import java.io.File

class RegisterActivity : AppCompatActivity() {
    lateinit var masterDataDriver: DataDriver
    private lateinit var pass1:EditText
    private lateinit var pass2:EditText
    private lateinit var createBtn: Button
    lateinit var check: CheckBox
    lateinit var infoText:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val fileDir: File = filesDir;
        this.masterDataDriver = DataDriver(fileDir);

        pass1 = findViewById(R.id.password1)
        pass2 = findViewById(R.id.password2)
        createBtn = findViewById(R.id.createUserBtn)
        infoText = findViewById(R.id.infoDisplay)
        check = findViewById(R.id.checkBox)

        createBtn.setOnClickListener{
            if(pass1.text.isNotEmpty() && pass2.text.isNotEmpty()) {
                if (pass1.text.toString() == pass2.text.toString()) {
                    masterDataDriver.createPassword(pass1.text.toString())
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java)
                        .putExtra("data",masterDataDriver))
                } else {
                    infoText.text = Html.fromHtml("<font color='#EE0000'>Passwords don't match, please try again </font>")
                }
            }
            else{
                infoText.text = Html.fromHtml("<font color='#EE0000'>Please enter password in both boxes. </font>")
            }
        }
        check.setOnClickListener {
            if (check.isChecked) {
                pass1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                pass2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                check.text = "Hide"

            } else {
                pass1.transformationMethod = PasswordTransformationMethod.getInstance()
                pass2.transformationMethod = PasswordTransformationMethod.getInstance()
                check.text = "Show"
            }
        }

    }
}
