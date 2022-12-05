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

class ValidateActivity : AppCompatActivity() {
    lateinit var pass1: EditText
    lateinit var gbtn: Button
    lateinit var check: CheckBox
    lateinit var tes: TextView
    lateinit var masterDataDriver: DataDriver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate)

        pass1 = findViewById(R.id.validatePasswordInput)
        gbtn = findViewById(R.id.validate_button)
        check = findViewById(R.id.checkBox)
        tes = findViewById(R.id.validateText)
        masterDataDriver = intent.getSerializableExtra("data") as DataDriver

        check.setOnClickListener {
            if (check.isChecked) {
                pass1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                check.text = "Hide"
            } else {
                pass1.transformationMethod = PasswordTransformationMethod.getInstance()
                check.text = "Show"
            }
        }
        gbtn.setOnClickListener{
            if (masterDataDriver.validate(pass1.text.toString())) {
                startActivity(
                    Intent(this, ChangePasswordActivity::class.java)
                        .putExtra("data", masterDataDriver)
                )
            } else {
                tes.text = Html.fromHtml("<font color='#EE0000'>Wrong password, please try again. </font>")

            }
        }
    }
}