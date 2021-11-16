package com.edasinar.odev4

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class AddNewValueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_value)
    }

    fun add(view : View){
        var editTextKey : EditText = findViewById(R.id.editTextKey)
        var editTextValue : EditText = findViewById(R.id.editTextValue)

        var editTextKeyStr : String = editTextKey.getText().toString()
        var editTextValueStr : String = editTextValue.getText().toString()

        var database : SQLiteDatabase = this.openOrCreateDatabase("Ders_Odev", MODE_PRIVATE,null)
        database.execSQL("CREATE TABLE IF NOT EXISTS ders_odev (id INTEGER PRIMARY KEY,keys VARCHAR UNIQUE, value VARCHAR)")
        database.execSQL("INSERT INTO ders_odev (keys,value) VALUES('"+editTextKeyStr+"','"+editTextValueStr+"') ")

        var cursor : Cursor = database.rawQuery("SELECT * FROM ders_odev", null)
        var keysIx : Int = cursor.getColumnIndex("keys")
        var valueIx : Int = cursor.getColumnIndex("value")
        var idIx : Int = cursor.getColumnIndex("id")
        println("***************************************************************************")
        while (cursor.moveToNext()){
            print("id: " + cursor.getString(idIx))
            print("  Keys: " + cursor.getString(keysIx))
            println("  Value: " + cursor.getString(valueIx))
        }
        println("***************************************************************************")
    }

    fun back(view : View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}