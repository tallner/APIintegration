package com.tallner.apiintegration


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {



    private lateinit var cityEdit: EditText
    private lateinit var countryEdit: EditText
    private lateinit var usernameEdit: EditText
    private lateinit var loginButton: Button

    private var username = ""
    private var city = ""
    private var country = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cityEdit = findViewById(R.id.text_city)
        countryEdit = findViewById(R.id.text_country)
        usernameEdit = findViewById(R.id.text_username)
        loginButton = findViewById(R.id.btn_signin)

        loginButton.setOnClickListener {
            if (
                TextUtils.isEmpty(usernameEdit.text.toString()) ||
                TextUtils.isEmpty(cityEdit.text.toString()) ||
                TextUtils.isEmpty(countryEdit.text.toString())
            )
            {
                Toast.makeText(this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
            }
            else {

                val myFirebaseHelper = FirebaseHelper()
                val user = hashMapOf(
                    "username" to usernameEdit.text.toString(),
                    "city" to cityEdit.text.toString(),
                    "country" to countryEdit.text.toString()
                )
                myFirebaseHelper.addUser(user)

                // create variable for editor of shared prefs
               // val editor: SharedPreferences.Editor = sharedPreferences.edit()

                // adding our username and pwd to shared prefs
            //    editor.putString(USER_KEY, usernameEdit.text.toString())
            //    editor.putString(PWD_KEY, passwordEdit.text.toString())

                // apply changes to our shared prefs.
           //     editor.apply()

                // if email and pwd is not empty we
                // are opening our main 2 activity on below line.
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("EXTRA_LOGGED_IN_USER",usernameEdit.text.toString())
                i.putExtra("EXTRA_USERCITY",cityEdit.text.toString())
                i.putExtra("EXTRA_USERCOUNTRY",countryEdit.text.toString())



                // on below line we are calling start
                // activity method to start our activity.
                startActivity(i)

                // on below line we are calling
                // finish to finish our main activity.
                finish()

            }
        }
    }
}