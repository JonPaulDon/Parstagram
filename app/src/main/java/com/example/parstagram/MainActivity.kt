package com.example.parstagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.parstagram.fragments.ComposeFragment
import com.example.parstagram.fragments.FeedFragment
import com.example.parstagram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->
            var fragmentToShow: Fragment?=null
            when(item.itemId){
                R.id.action_home -> {
                    //Navigate to the home screen
                    fragmentToShow=FeedFragment()
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()

                }
                R.id.action_compose -> {
                    //Navigate to the compose screen
                    fragmentToShow=ComposeFragment()
                    Toast.makeText(this,"Compose",Toast.LENGTH_SHORT).show()
                }
                R.id.action_profile -> {
                    //Navigate to the profile screen
                    Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
                    fragmentToShow=ProfileFragment()

                }
            }
            if(fragmentToShow!=null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            //Return true to say that we've handled this user interaction on the item
            true
        }
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home

       // queryPosts()
    }





    //Query for all posts in our server

    private fun goToLoginActivity(){
        val intent= Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    companion object{
        const val TAG="MainActivity"
    }
}