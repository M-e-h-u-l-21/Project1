package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= FirebaseFirestore.getInstance()
        val radio=findViewById<RadioGroup>(R.id.rg)
        val des=findViewById<TextInputEditText>(R.id.etcost_et)
        val notify=findViewById<Button>(R.id.button)
        val avail="Available"
        val unavail="Unavailable"
        var name=intent.getStringExtra("collection_name")
        val checkedRadioButtonId=radio.checkedRadioButtonId
        notify.setOnClickListener {
            if (checkedRadioButtonId.toString()=="R.id.rb_available"){
                val data= hashMapOf(
                    "status" to avail,
                    "description" to des.text.toString(),
                    "date" to FieldValue.serverTimestamp()
                )
                db.collection("$name")
                    .add(data)
                    .addOnSuccessListener {documentReference->
                        Log.d("Data Addition", "DocumentSnapshot written with ID: ${documentReference.id}")
                        val intent = Intent(this,MainActivity::class.java)
                        Toast.makeText(this,"Notified", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finishAffinity()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Data Addition", "Error adding document", e)
                    }

            }
            else{
                val data= hashMapOf(
                    "status" to unavail,
                    "description" to des.text.toString(),
                    "date" to FieldValue.serverTimestamp()
                )
                db.collection("$name")
                    .add(data)
                    .addOnSuccessListener {documentReference->
                        Log.d("Data Addition", "DocumentSnapshot written with ID: ${documentReference.id}")
                        val intent = Intent(this,MainActivity::class.java)
                        Toast.makeText(this,"Notified", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finishAffinity()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Data Addition", "Error adding document", e)
                    }

            }

        }

        


    }
}