package com.example.user.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.user.Models.DataDriver
import com.example.user.R


class LoginActivity : AppCompatActivity() {
    lateinit var loginBtn: Button
    lateinit var passwordInput: EditText
    lateinit var infoDisplay: TextView
    lateinit var check: CheckBox
    lateinit var masterDataDriver: DataDriver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginButton)
        passwordInput = findViewById(R.id.passwordInput)
        infoDisplay = findViewById(R.id.nesten)
        check = findViewById(R.id.checkBox)

        masterDataDriver = intent.getSerializableExtra("driver") as DataDriver

        loginBtn.setOnClickListener {
            if (masterDataDriver.login(passwordInput.text.toString())) {
                startActivity(
                    Intent(this, categoryListPage::class.java)
                        .putExtra("driver", masterDataDriver)
                )
            } else {
                infoDisplay.setText("Wrong password, please try again.")
            }
        }
        check.setOnClickListener {
            if (check.isChecked) {
                passwordInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                check.text = "Hide"
            } else {
                passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
                check.text = "Show"
            }
        }
    }
}
