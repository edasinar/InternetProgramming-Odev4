package com.edasinar.odev4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val intent = intent

        var value = intent.getStringExtra("valueVal")

        var valueVal : TextView = findViewById(R.id.valueTextView)

        valueVal.setText(value)
    }

    fun backPage(view : View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)

    }

    fun addValue(view : View){
        val intent = Intent(applicationContext, AddNewValueActivity::class.java)
        startActivity(intent)

    }
}