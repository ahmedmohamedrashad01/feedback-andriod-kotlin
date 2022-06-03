package com.example.salon

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_english__question.*
import kotlinx.android.synthetic.main.comment.*
import kotlinx.android.synthetic.main.comment.view.*
import kotlinx.android.synthetic.main.comment.view.btnSaveNameAndPhoneNumber
import kotlinx.android.synthetic.main.comment.view.txtCustomerName
import kotlinx.android.synthetic.main.comment.view.txtPhoneNumber
import kotlinx.android.synthetic.main.phonenumber.view.*
import kotlinx.android.synthetic.main.phonenumber_arabic.view.*
import java.text.SimpleDateFormat
import java.util.*

class English_Question : AppCompatActivity() , View.OnClickListener{

    var database = FirebaseDatabase.getInstance().getReference("Dop")

    var firstImgBad: String? = ""
    var firstImgBadComment: String? = ""

    var SecondImgBad: String? = ""
    var SecondImgBadComment: String? = ""

    var ThirdImgBad: String? = ""
    var ThirdImgBadComment: String? = ""

    var FourthImgBad: String? = ""
    var FourthImgBadComment: String? = ""

    var FifthImgBad: String? = ""
    var FifthImgBadComment: String? = ""

    var customerName: String? = ""
    var customerPhoneNumber: String? = ""

    var typeOfService : String? = ""
    var frindsEn : String? = ""
    var frindsEnAll  = arrayOf("Yes","No")

    val itemList = arrayOf("Very bad service","Facial","Manicure & Pedicure","Massage")
   // val quietness = arrayOf("Employee","TV","Music","Talk a lot","Location")
    val tempreture = arrayOf("Too Hot","Too cold")
    val tempreture2 = arrayOf("Hot","cold")
    val staffPerformance = arrayOf("bad performance","There is no interest")
    val staffPerformance2 = arrayOf("Low performance","Limited experience")
    val price = arrayOf("Too Expensive","Expensive")
    val price2 = arrayOf("Expensive","Price is a little high")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english__question)



        btnSend.setOnClickListener {

            if (firstImgBad!!.trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "please select feedback about the Overall Satisfaction",
                    Toast.LENGTH_LONG
                ).show()
            } else if (SecondImgBad!!.trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "please select feedback about the quietness",
                    Toast.LENGTH_LONG
                ).show()

            } else if (ThirdImgBad!!.trim().isEmpty()) {
                Toast.makeText(this, "please select feedback about temperature", Toast.LENGTH_LONG)
                    .show()

            } else if (FourthImgBad!!.trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "please select feedback about staff performance",
                    Toast.LENGTH_LONG
                ).show()

            } else if (FifthImgBad!!.trim().isEmpty()) {
                Toast.makeText(this, "please select feedback about price", Toast.LENGTH_LONG).show()

            } else if (firstImgBad.equals("Very Bad Service") || firstImgBad.equals("Bad")
                || SecondImgBad.equals("Very Bad") || SecondImgBad.equals("Bad")
                || ThirdImgBad.equals("Very Bad Service") || ThirdImgBad.equals("Bad Service")
                || FourthImgBad.equals("Very Bad Service") || FourthImgBad.equals("Bad Service")
                || FifthImgBad.equals("Very Bad Service") || FifthImgBad.equals("Bad Service")
            ) {
                customerName = ""
                customerPhoneNumber = ""
                typeOfService = ""
                frindsEn = ""
                var alertBuilder = AlertDialog.Builder(this)
                var lay = LayoutInflater.from(this).inflate(R.layout.comment, null)
                alertBuilder.setView(lay)
                var alertDialog = alertBuilder.create()
                alertDialog.setCanceledOnTouchOutside(false)

                alertDialog.show()

                lay.btnSaveNameAndPhoneNumber.setOnClickListener {
                    if (lay.txtCustomerName.text.trim().isEmpty()) {
                        Toast.makeText(this, "please enter your name", Toast.LENGTH_LONG).show()

                    } else if (lay.txtPhoneNumber.text.trim().isEmpty()) {
                        Toast.makeText(this, "please enter your phone number", Toast.LENGTH_LONG)
                            .show()

                    }else if(typeOfService!!.trim().isBlank()){

                      Toast.makeText(this,"please select type of service",Toast.LENGTH_LONG).show()
                    }else if(frindsEn!!.trim().isBlank()){
                      //  Toast.makeText(this,"please select answer for last question",Toast.LENGTH_LONG).show()

                        lateinit var dialog:AlertDialog
                        var builder = AlertDialog.Builder(this)
                        builder.setCancelable(false)
                        builder.setTitle("Do you want to tell your friends about our services?")
                        builder.setSingleChoiceItems(frindsEnAll,-1){dialog, which ->
                            frindsEn = frindsEnAll[which]
                         //   Toast.makeText(this,frindsEnAll[which],Toast.LENGTH_LONG).show()
                        }

                        builder.setPositiveButton("Ok",{dialog, which ->
                            dialog.dismiss()
                         if(frindsEn!!.trim().isBlank()){
                             Toast.makeText(this, "please select answer for last question", Toast.LENGTH_LONG).show()
                         }else{
                           //  Toast.makeText(this, typeOfService, Toast.LENGTH_LONG).show()


                            // Toast.makeText(this, "Thanks", Toast.LENGTH_LONG).show()

                             customerName = lay.txtCustomerName.text.toString()
                             customerPhoneNumber = lay.txtPhoneNumber.text.toString()

                             alertDialog.cancel()
                           // sendEmail("Dopamine.rak@gmail.com")

                             if(checkConnection()){
                                 var id =   database.push().key
                                 var myNote = Note(id,firstImgBad!!,SecondImgBad!!,ThirdImgBad!!,ThirdImgBadComment!!,FourthImgBad!!,FourthImgBadComment!!,FifthImgBad!!,FifthImgBadComment!!,customerName!!,customerPhoneNumber!!,typeOfService,frindsEn!!,getCurrentTime())
                                 database!!.child(id!!).setValue(myNote)

                                 Toast.makeText(this,"Sent Successfully",Toast.LENGTH_SHORT).show()
                                 Handler().postDelayed({
                                     var i = Intent(this,MainActivity::class.java)
                                     startActivity(i)
                                 },1000)
                             }


                         }
                        })


                        builder.setNegativeButton("Cancel",{dialog, which ->
                            frindsEn = ""

                          Toast.makeText(this,"please choose Yes or No",Toast.LENGTH_LONG).show()

                            dialog.dismiss()


                        })




                        val alertDialog = builder.create()
                        alertDialog.show()

                    }

                    else {


                    }
                }
            } else {
               // sendEmail("Dopamine.rak@gmail.com")
                //   Toast.makeText(this,"Done",Toast.LENGTH_LONG).show()

                var alertBuilder = AlertDialog.Builder(this)
                var lay = LayoutInflater.from(this).inflate(R.layout.phonenumber, null)
                alertBuilder.setView(lay)
                var alertDialog = alertBuilder.create()
                alertDialog.setCanceledOnTouchOutside(false)
                alertDialog.show()
                lay.btnSaveNameAndPhoneNumber2.setOnClickListener {
                    if(lay.txtCustomerName2.text.toString().trim().isEmpty() || lay.txtPhoneNumber2.text.toString().trim().isEmpty()){
                        Toast.makeText(this,"please enter your name and phone number",Toast.LENGTH_SHORT).show()

                    }else{
                       // sendEmail("Dopamine.rak@gmail.com")
                        if(checkConnection()){
                            var id =   database.push().key
                            var myNote = Note(id,firstImgBad!!,SecondImgBad!!,ThirdImgBad!!,ThirdImgBadComment!!,FourthImgBad!!,FourthImgBadComment!!,FifthImgBad!!,FifthImgBadComment!!,lay.txtCustomerName2.text.toString()!!,lay.txtPhoneNumber2.text.toString()!!,typeOfService,frindsEn!!,getCurrentTime())
                            database!!.child(id!!).setValue(myNote)

                            Toast.makeText(this,"Sent Successfully",Toast.LENGTH_SHORT).show()

                            Handler().postDelayed({
                                var i = Intent(this,MainActivity::class.java)
                                startActivity(i)
                            },1000)

                        }
                    }

                }

            }

        }


    }

    public fun checkFirstRow(v: View) {



        if (v == imgFirstMada) {
             imgFirstMada.animate().rotationBy(360f).setDuration(1900);

             firstImgBad = "Very Bad Service"

        } else if (v == imgSecondMada) {
            imgSecondMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "Bad"

        } else if (v == imgThirdMada) {
            imgThirdMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "Good"
           // Toast.makeText(this, firstImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFourthMada) {
            imgFourthMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "Very Good"
          //  Toast.makeText(this, firstImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFifthMada) {
            imgFifthMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "Excellent"
         //   Toast.makeText(this, firstImgBad, Toast.LENGTH_SHORT).show()
        }
    }

    ////////////////////////// Second Row /////////////////////////////////

    public fun checkSecondRow(v: View) {
        if (v == imgFirstdHodoo) {
            imgFirstdHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "Very Bad"

        } else if (v == imgSecondHodoo) {
            imgSecondHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "Bad"


        } else if (v == imgThirdHodoo) {
            imgThirdHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "Good"

        } else if (v == imgFourthHodoo) {
            imgFourthHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "Very Good"

        } else if (v == imgFifthHodoo) {
            imgFifthHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "Excellent"

        }
    }

    /////////////////// Third Row //////////////////////////

    public fun checkThirddRow(v: View) {
        if (v == imgFirstHarara) {
            imgFirstHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "Very Bad Service"
            lateinit var dialog:AlertDialog
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Please Choose The Problem ")
            builder.setSingleChoiceItems(tempreture,-1){dialog, which ->
                ThirdImgBadComment = tempreture[which]
              //  Toast.makeText(this,tempreture[which],Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("Ok",{dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("Cancel",{dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()

        } else if (v == imgSecondHarara) {
            imgSecondHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "Bad Service"
            lateinit var dialog:AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("Please Choose The Problem ")
            builder.setSingleChoiceItems(tempreture2,-1){dialog, which ->
                ThirdImgBadComment = tempreture2[which]
             //   Toast.makeText(this,tempreture2[which],Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("Ok",{dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("Cancel",{dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()
        } else if (v == imgThirdHarara) {
            imgThirdHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "Good"
          //  Toast.makeText(this, ThirdImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFourthHarara) {
            imgFourthHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "Very Good"
         //   Toast.makeText(this, ThirdImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFifthHarara) {
            imgFifthHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "Excellent"
          //  Toast.makeText(this, ThirdImgBad, Toast.LENGTH_SHORT).show()
        }
    }

    //////////// Fourth Row ////////////////

    public fun checkFourthdRow(v: View) {
        if (v == imgFirstAdaa) {
            imgFirstAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "Very Bad Service"
            lateinit var dialog:AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("Please Choose The Problem ")
            builder.setSingleChoiceItems(staffPerformance,-1){dialog, which ->
                FourthImgBadComment = staffPerformance[which]
              //  Toast.makeText(this,staffPerformance[which],Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("Ok",{dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("Cancel",{dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()

        } else if (v == imgSecondAdaa) {
            imgSecondAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "Bad Service"
            lateinit var dialog:AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("Please Choose The Problem ")
            builder.setSingleChoiceItems(staffPerformance2,-1){dialog, which ->
                FourthImgBadComment = staffPerformance[which]
              //  Toast.makeText(this,staffPerformance2[which],Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("Ok",{dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("Cancel",{dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()
        } else if (v == imgThirdAdaa) {
            imgThirdAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "Good"
        //    Toast.makeText(this, FourthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFourthAdaa) {
            imgFourthAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "Very Good"
          //  Toast.makeText(this, FourthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFifthAdaa) {
            imgFifthAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "Excellent"
         //   Toast.makeText(this, FourthImgBad, Toast.LENGTH_SHORT).show()
        }
    }

    ///////////// Fifth Row ///////////////

    public fun checkFifthdRow(v: View) {
        if (v == imgFirstPrice) {
            imgFirstPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "Very Bad Service"
            lateinit var dialog:AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("Please Choose The Problem ")
            builder.setSingleChoiceItems(price,-1){dialog, which ->
                FifthImgBadComment = price[which]
             //   Toast.makeText(this,price[which],Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("Ok",{dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("Cancel",{dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()


        } else if (v == imgSecondPrice) {
            imgSecondPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "Bad Service"

            lateinit var dialog:AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("Please Choose The Problem ")
            builder.setSingleChoiceItems(price2,-1){dialog, which ->
                FifthImgBadComment = price2[which]
            //    Toast.makeText(this,price2[which],Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("Ok",{dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("Cancel",{dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()

        } else if (v == imgThirdPrice) {
            imgThirdPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "Good"
           // Toast.makeText(this, FifthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFourthPrice) {
            imgFourthPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "Very Good"
           // Toast.makeText(this, FifthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFifthPrice) {
            imgFifthPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "Excellent"
          //  Toast.makeText(this, FifthImgBad, Toast.LENGTH_SHORT).show()
        }
    }


    private fun sendEmail(recipient: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer Feedback")
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, "Overall Satisfaction Feedback : "+firstImgBad +"\n"
                +"Quietness Feedback : $SecondImgBad"+"\n"
                +"Temperature Feedback : $ThirdImgBad"+"\n"+"Temperature Comment : $ThirdImgBadComment"
        +"\n"+"Staff Performance Feedback : $FourthImgBad"+"\n"+"Staff Performance Comment : $FourthImgBadComment"
        +"\n"+"Price Feedback : $FifthImgBad"+"\n"+"Price Comment : $FifthImgBadComment"
         +"\n"+"Customer Name : $customerName"+"\n"+"Customer Phone Number : $customerPhoneNumber"
        +"\n"+"Type Of Service: $typeOfService"+"\n"
          +"tell friends : $frindsEn")


        try {

            startActivity(Intent.createChooser(mIntent, "Please Choose Gmail ..."))
        } catch (e: Exception) {

            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideDown(this);
    }

    override fun onClick(v: View?) {


       v as CheckBox
        var check :Boolean = v.isChecked
        when(v.id){
            R.id.cbx1 -> if(check){
                typeOfService += " Hair Treatment : "
            }else{
                typeOfService = ""
            }

            R.id.cbx2 -> if(check){
                typeOfService += " Facial : "
            }else{
                typeOfService = ""
            }
            R.id.cbx3 -> if(check){
                typeOfService += " Manicure & Pedicure : "
            }else{
                typeOfService = ""
            }
            R.id.cbx4 -> if(check){
                typeOfService += " Massage : "
            }else{
                typeOfService = ""
            }




        }


    }


    fun getCurrentTime():String{
        val calender = Calendar.getInstance()
        val mFormat = SimpleDateFormat("EEEE hh:mm a")
        val startDate = mFormat.format(calender.time)
        return startDate
    }


    fun checkConnection():Boolean{
        val conManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiConn = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileConn = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if(wifiConn.isConnected || mobileConn.isConnected){
            return true
        }else{
            Toast.makeText(this,"لا يوجد اتصال بالانترنت",Toast.LENGTH_SHORT).show()
            return false
        }
    }

}

