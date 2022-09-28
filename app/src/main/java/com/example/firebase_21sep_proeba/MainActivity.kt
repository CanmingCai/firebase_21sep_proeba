package com.example.firebase_21sep_proeba

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance().reference

        val button = findViewById<Button>(R.id.button)
        val text1 = findViewById<EditText>(R.id.peso)
        val text2 = findViewById<EditText>(R.id.estatura)
        val text3 = findViewById<EditText>(R.id.tipo_sangre)
        val text4 = findViewById<EditText>(R.id.nacimiento)
        val textview = findViewById<TextView>(R.id.text1)

        button.setOnClickListener{

            val emppe = text1.text.toString().toInt()
            val empes = text2.text.toString().toDouble()
            val empsa = text3.text.toString()
            val empna = text4.text.toString()

            database.child(empsa.toString()).setValue(example(emppe,empes,empsa,empna))

        }

        // Retrieve data

        val getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sb = StringBuilder()
                for(i in snapshot.children){
                    val enppe1 = i.child("emppe").value
                    val enpes1 = i.child("empes").value
                    val enpna1 = i.child("empna")
                    sb.append("${i.key} $enppe1  $enpes1 $enpna1 \n")
                }
                textview.setText(sb)

            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)


    }
}

