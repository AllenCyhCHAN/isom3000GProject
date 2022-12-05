package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.user.Models.DataDriver
import com.example.user.R


class ChangePasswordActivity : AppCompatActivity() {
    lateinit var masterDataDriver: DataDriver
    lateinit var btn: Button
    lateinit var check: CheckBox
    lateinit var pass1: EditText
    lateinit var pass2: EditText
    lateinit var infoTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        masterDataDriver = intent.getSerializableExtra("data") as DataDriver

        btn = findViewById(R.id.changePasswordButton)
        pass1 = findViewById(R.id.changePass1)
        pass2 = findViewById(R.id.changePass2)
        infoTxt = findViewById(R.id.infoDisplayChange)
        check = findViewById(R.id.checkBox2)

        check.setOnClickListener {
            if (check.isChecked) {
                pass1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                check.text = "Hide"
            } else {
                pass1.transformationMethod = PasswordTransformationMethod.getInstance()
                check.text = "Show"
            }
        }
        btn.setOnClickListener{
            if(pass1.text.isNotEmpty() && pass2.text.isNotEmpty()) {
                if (pass1.text.toString() == pass2.text.toString()) {
                    masterDataDriver.changePassword(pass1.text.toString())
                    startActivity(Intent(this@ChangePasswordActivity, ValidateActivity::class.java)
                        .putExtra("data",masterDataDriver))


                } else {
                    infoTxt.text = Html.fromHtml("<font color='#EE0000'>Passwords don't match, please try again </font>")
                }
            }
            else{
                infoTxt.text = Html.fromHtml("<font color='#EE0000'>Please enter password in both boxes. </font>")
            }
        }


    }
}