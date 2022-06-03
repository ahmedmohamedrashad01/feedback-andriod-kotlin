package com.example.salon

import android.app.ActionBar
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_list__view_.*
import kotlinx.android.synthetic.main.activity_main.*

class List_View_Activity : AppCompatActivity() {
    var mNoteList : ArrayList<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list__view_)




        var database = FirebaseDatabase.getInstance().getReference("Dop")
        mNoteList = ArrayList()

        if(checkConnection()){
            database.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,error.message, Toast.LENGTH_SHORT).show()

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    mNoteList!!.clear()
                    for(i in snapshot.children){
                        var note = i.getValue(Note::class.java)
                        mNoteList!!.add(note!!)

                    }
                    val noteAdapter = NoteAdapter(applicationContext, mNoteList!!)
                    MList.adapter = noteAdapter

                }

            })

            MList.setOnItemClickListener { parent, view, position, id ->
               // Toast.makeText(this,"$position",Toast.LENGTH_SHORT).show()
                //var database2 = FirebaseDatabase.getInstance().getReference("Dop")
                //var myNote = mNoteList!!.get(position)
               // database?.child(myNote.id!!)?.removeValue()

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Delete Report")
                builder.setMessage("Do you want to delete this report?")
                builder.setPositiveButton("YES"){dialog, which ->
                    var myNote = mNoteList!!.get(position)
                    database?.child(myNote.id!!)?.removeValue()
                }



                builder.setNegativeButton("No"){dialog,which ->
                    Toast.makeText(applicationContext,"No",Toast.LENGTH_SHORT).show()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        }






        /////










    }
    fun checkConnection():Boolean{
        val conManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiConn = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileConn = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if(wifiConn.isConnected || mobileConn.isConnected){
            return true
        }else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
            return false
        }
    }
}