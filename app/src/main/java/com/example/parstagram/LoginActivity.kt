package com.example.parstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)
            //Check if user is logged in

            //If they are, take them to MainActivity
            if(ParseUser.getCurrentUser()!=null){
              goToMainActivity()
            }

            findViewById<Button>(R.id.login_button).setOnClickListener(){
                val username= findViewById<EditText>(R.id.etUsername).text.toString()
                val password= findViewById<EditText>(R.id.etPassword).text.toString()
                loginUser(username,password)
            }
        findViewById<Button>(R.id.signupBtn).setOnClickListener(){
            val username= findViewById<EditText>(R.id.etUsername).text.toString()
            val password= findViewById<EditText>(R.id.etPassword).text.toString()
            signUpUser(username,password)
        }


    }

    private fun loginUser(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                // Hooray!  The user is logged in.
                Log.i(TAG,"Successfully logged in user")
                goToMainActivity()
            } else {
                // Signup failed.  Look at the ParseException to see what happened.
                e.printStackTrace()
                Toast.makeText(this,"Error logging in",Toast.LENGTH_SHORT).show()
            }})
        )
    }
    private fun signUpUser(username: String, password: String){
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
                //TODO: Show a toast
                Toast.makeText(this,"Successfully signed up!",Toast.LENGTH_SHORT).show()
                Log.i(TAG,"Successfully signed up user")
                //TODO:Navigate the user to the main activity
                loginUser(username,password)


            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                //Show a toast to tell them their signup wasn't successful
                e.printStackTrace()
            }
        }

    }

    private fun goToMainActivity(){
        val intent= Intent(this@LoginActivity,DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
    companion object{
        const val TAG="LoginActivity"
    }
}