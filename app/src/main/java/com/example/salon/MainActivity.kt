package com.example.salon

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdmin.setOnClickListener {


            val editText = EditText(this)
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.hint = "Password"
            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("Enter Administrator Password")
                .setView(editText)
                .setCancelable(false)
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                })

            inputDialog.setPositiveButton("OK",null)


            val mAlertDialog = inputDialog.create()

            mAlertDialog.setOnShowListener(DialogInterface.OnShowListener {
                val b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                b.setOnClickListener(View.OnClickListener {

                    val password= editText.text.toString()
                    if (password=="123456"){
                        mAlertDialog.dismiss();
                        var i = Intent(this,List_View_Activity::class.java)
                        startActivity(i)
                    }else{
                        Toast.makeText(this,"Enter The Correct Password",Toast.LENGTH_SHORT).show()
                        //Return for input again
                    }

                })
            })

            mAlertDialog.show()

            /*

            var i = Intent(this,List_View_Activity::class.java)
            startActivity(i)

             */
        }



        //////////////  Count /////////////////////


        btnCount.setOnClickListener {

            val editText = EditText(this)
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.hint = "Password"

            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("Enter Administrator Password")
                .setView(editText)
                .setCancelable(false)
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                })

            inputDialog.setPositiveButton("OK",null)


            val mAlertDialog = inputDialog.create()

            mAlertDialog.setOnShowListener(DialogInterface.OnShowListener {
                val b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                b.setOnClickListener(View.OnClickListener {

                    val password= editText.text.toString()

                    if (password=="123456"){
                        mAlertDialog.dismiss();
                        var con = applicationContext
                        var myDatabase = FirebaseDatabase.getInstance().getReference("Dop")
                        myDatabase.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(applicationContext,error.message,Toast.LENGTH_SHORT).show()
                            }

                            override fun onDataChange(snapshot: DataSnapshot) {
                                Toast.makeText(con,"Reports Count : "+snapshot.childrenCount.toString(),Toast.LENGTH_LONG).show()
                            }

                        })
                    }else{
                        Toast.makeText(this,"Enter The Correct Password",Toast.LENGTH_SHORT).show()
                        //Return for input again
                    }

                })
            })

            mAlertDialog.show()

        }







        linearArabic.setOnClickListener {



            startActivity(Intent(this, Arabic_Question::class.java))
            Animatoo.animateWindmill(this)
            /*
            var i = Intent(this,Arabic_Question::class.java)
            startActivity(i)

             */
        }

        linearenglish.setOnClickListener {
            startActivity(Intent(this, English_Question::class.java))
            Animatoo.animateSpin(this)

         /*
            var i = Intent(this,English_Question::class.java)
            startActivity(i)

          */
        }
    }
}