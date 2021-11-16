package com.edasinar.odev4

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{
            var database : SQLiteDatabase = this.openOrCreateDatabase("Ders_Odev", MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS Ders_Odev (id INTEGER PRIMARY KEY,keys VARCHAR UNIQUE, value VARCHAR)")

            /*database.execSQL("INSERT INTO ders_odev (keys,value) VALUES ('BIL359','Hello world from database')")
            database.execSQL("INSERT INTO ders_odev (keys,value) VALUES ('BIL101','Bil. Muh. Giris')")
            database.execSQL("INSERT INTO ders_odev (keys,value) VALUES ('BIL121','Yapısal Prog')")
            database.execSQL("INSERT INTO ders_odev (keys,value) VALUES ('BIL201','Veri Yapıları ve Alg')")
            database.execSQL("INSERT INTO ders_odev (keys,value) VALUES ('BIL203','Bilgisayar Mimarisi')")
            database.execSQL("INSERT INTO ders_odev (keys,value) VALUES ('BIL366','Veri Madenciligi')")*/

            var cursor : Cursor = database.rawQuery("SELECT * FROM Ders_Odev", null)
            var keysIx : Int = cursor.getColumnIndex("keys")
            var valueIx : Int = cursor.getColumnIndex("value")
            var idIx : Int = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                print("id: " + cursor.getString(idIx))
                print("  Keys: " + cursor.getString(keysIx))
                println("  Value: " + cursor.getString(valueIx))
            }


            cursor.close()

        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun clickHere(view : View){
        try{
            var dersNo : EditText = findViewById(R.id.editTextDersKodu)
            var dersNoStr : String = dersNo.getText().toString()

            var database : SQLiteDatabase = this.openOrCreateDatabase("Ders_Odev", MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS Ders_Odev (id INTEGER PRIMARY KEY,keys VARCHAR UNIQUE, value VARCHAR)")

            var cursor : Cursor = database.rawQuery("SELECT * FROM Ders_Odev", null)
            var keysIx : Int = cursor.getColumnIndex("keys")
            var valueIx : Int = cursor.getColumnIndex("value")
            var idIx : Int = cursor.getColumnIndex("id")
            var txt : String = ""
            println("***********************************************************")
            while (cursor.moveToNext()){
                print("id: " + cursor.getString(idIx))
                print("  Keys: " + cursor.getString(keysIx))
                println("  Value: " + cursor.getString(valueIx))
                if(cursor.getString(keysIx) == dersNoStr){
                    txt = cursor.getString(valueIx)
                }
            }
            println("txt value: " + txt)
            if(txt.isEmpty()) {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Warning!")
                alert.setMessage("There is no such a key value.")
                alert.setNegativeButton("OK"){dialog, which->
                    Toast.makeText(applicationContext,"Try Again", Toast.LENGTH_LONG).show()}

                alert.show()
                println("###################")
                println("there is no such a key value")
                println("###################")
            }


            else{
                val intent = Intent(applicationContext, ShowActivity::class.java)
                intent.putExtra("valueVal",txt)
                startActivity(intent)
            }


        } catch (e : Exception){
            e.printStackTrace()
        }
    }
}