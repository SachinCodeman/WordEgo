package com.example.wordego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

import android.view.WindowManager
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


         */

        setContentView(R.layout.activity_main)

    }
}
