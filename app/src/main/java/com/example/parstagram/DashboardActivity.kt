package com.example.parstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.parse.ParseUser
//This activity is no longer useful
class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        findViewById<Button>(R.id.btnLogOut).setOnClickListener {

                ParseUser.logOut()
                goToLoginActivity()

        }
        findViewById<Button>(R.id.btnPost).setOnClickListener {
            val intent = Intent(this@DashboardActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun goToLoginActivity(){
        val intent= Intent(this@DashboardActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}