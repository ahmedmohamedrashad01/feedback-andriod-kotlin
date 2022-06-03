package com.example.salon

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_arabic__question.btnSend
import kotlinx.android.synthetic.main.activity_arabic__question.imgFifthAdaa
import kotlinx.android.synthetic.main.activity_arabic__question.imgFifthHarara
import kotlinx.android.synthetic.main.activity_arabic__question.imgFifthHodoo
import kotlinx.android.synthetic.main.activity_arabic__question.imgFifthMada
import kotlinx.android.synthetic.main.activity_arabic__question.imgFifthPrice
import kotlinx.android.synthetic.main.activity_arabic__question.imgFirstAdaa
import kotlinx.android.synthetic.main.activity_arabic__question.imgFirstHarara
import kotlinx.android.synthetic.main.activity_arabic__question.imgFirstMada
import kotlinx.android.synthetic.main.activity_arabic__question.imgFirstPrice
import kotlinx.android.synthetic.main.activity_arabic__question.imgFirstdHodoo
import kotlinx.android.synthetic.main.activity_arabic__question.imgFourthAdaa
import kotlinx.android.synthetic.main.activity_arabic__question.imgFourthHarara
import kotlinx.android.synthetic.main.activity_arabic__question.imgFourthHodoo
import kotlinx.android.synthetic.main.activity_arabic__question.imgFourthMada
import kotlinx.android.synthetic.main.activity_arabic__question.imgFourthPrice
import kotlinx.android.synthetic.main.activity_arabic__question.imgSecondAdaa
import kotlinx.android.synthetic.main.activity_arabic__question.imgSecondHarara
import kotlinx.android.synthetic.main.activity_arabic__question.imgSecondHodoo
import kotlinx.android.synthetic.main.activity_arabic__question.imgSecondMada
import kotlinx.android.synthetic.main.activity_arabic__question.imgSecondPrice
import kotlinx.android.synthetic.main.activity_arabic__question.imgThirdAdaa
import kotlinx.android.synthetic.main.activity_arabic__question.imgThirdHarara
import kotlinx.android.synthetic.main.activity_arabic__question.imgThirdHodoo
import kotlinx.android.synthetic.main.activity_arabic__question.imgThirdMada
import kotlinx.android.synthetic.main.activity_arabic__question.imgThirdPrice
import kotlinx.android.synthetic.main.activity_english__question.*
import kotlinx.android.synthetic.main.phonenumber.view.*
import kotlinx.android.synthetic.main.phonenumber_arabic.*
import kotlinx.android.synthetic.main.phonenumber_arabic.view.btnSaveNameAndPhoneNumber
import kotlinx.android.synthetic.main.phonenumber_arabic.view.txtCustomerName
import kotlinx.android.synthetic.main.phonenumber_arabic.view.txtPhoneNumber
import java.text.SimpleDateFormat
import java.util.*


class Arabic_Question : AppCompatActivity(), View.OnClickListener {

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

    var typeOfService: String? = ""
    var frindsEn: String? = ""
    var frindsEnAll = arrayOf("نعم", "لا")

    val itemList = arrayOf("Very bad service", "Facial", "Manicure & Pedicure", "Massage")

    // val quietness = arrayOf("Employee","TV","Music","Talk a lot","Location")
    val tempreture = arrayOf("حار جدا", "بارد جدا")
    val tempreture2 = arrayOf("حار", "بارد")
    val staffPerformance = arrayOf("اداء سىء", "لا يوجداهتمام")
    val staffPerformance2 = arrayOf("اداء منخفض", "خبره محدوده")
    val price = arrayOf("السعر مرتفع جدا", "مرتفع قليلا")
    val price2 = arrayOf("مرتفع للغايه", "السعر مرتفع قليلا")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arabic__question)


        btnSend.setOnClickListener {
            if (firstImgBad!!.trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "الرجاء تحديد ملاحظات حول الرضا العام",
                    Toast.LENGTH_LONG
                ).show()
            } else if (SecondImgBad!!.trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "الرجاء تحديد ملاحظات حول  الهدوء",
                    Toast.LENGTH_LONG
                ).show()

            } else if (ThirdImgBad!!.trim().isEmpty()) {
                Toast.makeText(this, "الرجاء تحديد ملاحظات حول  درجة الحراره", Toast.LENGTH_LONG)
                    .show()

            } else if (FourthImgBad!!.trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "الرجاء تحديد ملاحظات حول  اداء الموظفين",
                    Toast.LENGTH_LONG
                ).show()

            } else if (FifthImgBad!!.trim().isEmpty()) {
                Toast.makeText(this, "الرجاء تحديد ملاحظات حول  الاسعار", Toast.LENGTH_LONG).show()

            } else if (firstImgBad.equals("خدمه سيئه جدا") || firstImgBad.equals("خدمه سيئه")
                || SecondImgBad.equals("خدمه سيئه جدا") || SecondImgBad.equals("خدمه سيئه")
                || ThirdImgBad.equals("خدمه سيئه جدا") || ThirdImgBad.equals("خدمه سيئه")
                || FourthImgBad.equals("خدمه سيئه جدا") || FourthImgBad.equals("خدمه سيئه")
                || FifthImgBad.equals("خدمه سيئه جدا") || FifthImgBad.equals("خدمه سيئه")
            ) {
                customerName = ""
                customerPhoneNumber = ""
                typeOfService = ""
                frindsEn = ""
                var alertBuilder = AlertDialog.Builder(this)
                var lay = LayoutInflater.from(this).inflate(R.layout.comment_arabic, null)
                alertBuilder.setView(lay)
                var alertDialog = alertBuilder.create()
                alertDialog.setCanceledOnTouchOutside(false)
                alertDialog.show()

                lay.btnSaveNameAndPhoneNumber.setOnClickListener {
                    if (lay.txtCustomerName.text.trim().isEmpty()) {
                        Toast.makeText(this, "من فضلك ادخل الاسم", Toast.LENGTH_LONG).show()

                    } else if (lay.txtPhoneNumber.text.trim().isEmpty()) {
                        Toast.makeText(this, "من فضلك ادخل رقم الهاتف", Toast.LENGTH_LONG)
                            .show()

                    } else if (typeOfService!!.trim().isBlank()) {

                        Toast.makeText(this, "من فضلك ادخل نوع الخدمه", Toast.LENGTH_LONG).show()
                    } else if (frindsEn!!.trim().isBlank()) {
                        // Toast.makeText(this,"please select answer of last question",Toast.LENGTH_LONG).show()

                        lateinit var dialog: AlertDialog
                        var builder = AlertDialog.Builder(this)
                        builder.setCancelable(false)
                        builder.setTitle("هل ستخبر الاصدقاء عن خدماتنا ؟")
                        builder.setSingleChoiceItems(frindsEnAll, -1) { dialog, which ->
                            frindsEn = frindsEnAll[which]
                         //   Toast.makeText(this, frindsEnAll[which], Toast.LENGTH_LONG).show()
                        }

                        builder.setPositiveButton("موافق", { dialog, which ->
                            dialog.dismiss()
                            if (frindsEn!!.trim().isBlank()) {
                                Toast.makeText(
                                    this,
                                    "من فضلك اختر اجابه لاخر سؤال",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                            //    Toast.makeText(this, typeOfService, Toast.LENGTH_LONG).show()


                              //  Toast.makeText(this, "شكرا", Toast.LENGTH_LONG).show()

                                customerName = lay.txtCustomerName.text.toString()
                                customerPhoneNumber = lay.txtPhoneNumber.text.toString()

                                alertDialog.cancel()
                             //   sendEmail("Dopamine.rak@gmail.com")
                                if(checkConnection()){
                                    var id =   database.push().key
                             var myNote = Note(id,firstImgBad!!,SecondImgBad!!,ThirdImgBad!!,ThirdImgBadComment!!,FourthImgBad!!,FourthImgBadComment!!,FifthImgBad!!,FifthImgBadComment!!,customerName!!,customerPhoneNumber!!,typeOfService,frindsEn!!,getCurrentTime())
                                    database!!.child(id!!).setValue(myNote)

                                    Toast.makeText(this,"تم الارسال",Toast.LENGTH_SHORT).show()
                                   Handler().postDelayed({
                                       var i = Intent(this,MainActivity::class.java)
                                       startActivity(i)
                                   },1000)
                                }

                            }
                        })


                        builder.setNegativeButton("الغاء", { dialog, which ->
                            frindsEn = ""

                            Toast.makeText(this, "من فضلك اختر نعم او لا", Toast.LENGTH_LONG).show()

                            dialog.dismiss()


                        })


                        val alertDialog = builder.create()
                        alertDialog.show()

                    } else {


                    }
                }
            } else {
                var alertBuilder = AlertDialog.Builder(this)
                var lay = LayoutInflater.from(this).inflate(R.layout.phonenumber_arabic, null)
                alertBuilder.setView(lay)
                var alertDialog = alertBuilder.create()
                alertDialog.setCanceledOnTouchOutside(false)
                alertDialog.show()

                lay.btnSaveNameAndPhoneNumber.setOnClickListener {
                   if(lay.txtCustomerName.text.toString().trim().isEmpty() || lay.txtPhoneNumber.text.toString().trim().isEmpty()){
                       Toast.makeText(this,"من فضلك ادخلى الاسم ورقم الهاتف",Toast.LENGTH_SHORT).show()

                   }else{
                      // sendEmail("Dopamine.rak@gmail.com")

                       if(checkConnection()){
                           var id =   database.push().key
                           var myNote = Note(id,firstImgBad!!,SecondImgBad!!,ThirdImgBad!!,ThirdImgBadComment!!,FourthImgBad!!,FourthImgBadComment!!,FifthImgBad!!,FifthImgBadComment!!,lay.txtCustomerName.text.toString()!!,lay.txtPhoneNumber.text.toString()!!,typeOfService,frindsEn!!,getCurrentTime())
                           database!!.child(id!!).setValue(myNote)

                           Toast.makeText(this,"تم الارسال",Toast.LENGTH_SHORT).show()
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

    public fun checkFirstRowArabic(v: View) {
        if (v == imgFirstMada ) {
            imgFirstMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "خدمه سيئه جدا"

        } else if (v == imgSecondMada) {
            imgSecondMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "خدمه سيئه"


        } else if (v == imgThirdMada) {
            imgThirdMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "جيد"

        } else if (v == imgFourthMada) {
            imgFourthMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "جيد جدا"

        } else if (v == imgFifthMada) {
            imgFifthMada.animate().rotationBy(360f).setDuration(1900);
            firstImgBad = "ممتازه"

        }

    }

    ////////////////////////// Second Row /////////////////////////////////

    public fun checkSecondRowArabic(v: View) {
        if (v == imgFirstdHodoo) {
            imgFirstdHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "خدمه سيئه جدا"

        } else if (v == imgSecondHodoo) {
            imgSecondHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "خدمه سيئه"


        } else if (v == imgThirdHodoo) {
            imgThirdHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "جيد"

        } else if (v == imgFourthHodoo) {
            imgFourthHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "جيد جدا"

        } else if (v == imgFifthHodoo) {
            imgFifthHodoo.animate().rotationBy(360f).setDuration(1900);
            SecondImgBad = "ممتازه"

        }
    }

    /////////////////// Third Row //////////////////////////

    public fun checkThirddRowArabic(v: View) {
        if (v == imgFirstHarara) {
            imgFirstHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "خدمه سيئه جدا"
            lateinit var dialog: AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("من فضلك اختر نوع المشكله ")
            builder.setSingleChoiceItems(tempreture, -1) { dialog, which ->
                ThirdImgBadComment = tempreture[which]
                Toast.makeText(this, tempreture[which], Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("موافق", { dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("الغاء", { dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()

        } else if (v == imgSecondHarara) {
            imgSecondHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "خدمه سيئه"
            lateinit var dialog: AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("من فضلك اختر نوع المشكله ")
            builder.setSingleChoiceItems(tempreture2, -1) { dialog, which ->
                ThirdImgBadComment = tempreture2[which]
                Toast.makeText(this, tempreture2[which], Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("موافق", { dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("الغاء", { dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()
        } else if (v == imgThirdHarara) {
            imgThirdHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "جيد"
            //  Toast.makeText(this, ThirdImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFourthHarara) {
            imgFourthHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "جيد جدا"
            //   Toast.makeText(this, ThirdImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFifthHarara) {
            imgFifthHarara.animate().rotationBy(360f).setDuration(1900);
            ThirdImgBad = "ممتازه"
            //  Toast.makeText(this, ThirdImgBad, Toast.LENGTH_SHORT).show()
        }
    }

    //////////// Fourth Row ////////////////

    public fun checkFourthdRowArabic(v: View) {
        if (v == imgFirstAdaa) {
            imgFirstAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "خدمه سيئه جدا"
            lateinit var dialog: AlertDialog
            var builder = AlertDialog.Builder(this)
          //  builder.setTitle("من فضلك اختر نوع المشكله ")
            builder.setSingleChoiceItems(staffPerformance, -1) { dialog, which ->
                FourthImgBadComment = staffPerformance[which]
                Toast.makeText(this, staffPerformance[which], Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("موافق", { dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("الغاء", { dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()

        } else if (v == imgSecondAdaa) {
            imgSecondAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "خدمه سيئه"
            lateinit var dialog: AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("من فضلك اختر نوع المشكله ")
            builder.setSingleChoiceItems(staffPerformance2, -1) { dialog, which ->
                FourthImgBadComment = staffPerformance[which]
                Toast.makeText(this, staffPerformance2[which], Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("موافق", { dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("الغاء", { dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()
        } else if (v == imgThirdAdaa) {
            imgThirdAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "جيد"
            //    Toast.makeText(this, FourthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFourthAdaa) {
            imgFourthAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "جيد جدا"
            //  Toast.makeText(this, FourthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFifthAdaa) {
            imgFifthAdaa.animate().rotationBy(360f).setDuration(1900);
            FourthImgBad = "ممتاز"
            //   Toast.makeText(this, FourthImgBad, Toast.LENGTH_SHORT).show()
        }
    }

    ///////////// Fifth Row ///////////////

    public fun checkFifthdRowArabic(v: View) {
        if (v == imgFirstPrice) {
            imgFirstPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "خدمه سيئه جدا"
            lateinit var dialog: AlertDialog
            var builder = AlertDialog.Builder(this)
          //  builder.setTitle("من فضلك اختر نوع المشكله ")
            builder.setSingleChoiceItems(price, -1) { dialog, which ->
                FifthImgBadComment = price[which]
                Toast.makeText(this, price[which], Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("موافق", { dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("الغاء", { dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()


        } else if (v == imgSecondPrice) {
            imgSecondPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "خدمه سيئه"

            lateinit var dialog: AlertDialog
            var builder = AlertDialog.Builder(this)
           // builder.setTitle("من فضلك اختر نوع المشكله ")
            builder.setSingleChoiceItems(price2, -1) { dialog, which ->
                FifthImgBadComment = price2[which]
                Toast.makeText(this, price2[which], Toast.LENGTH_LONG).show()
            }

            builder.setPositiveButton("موافق", { dialog, which ->
                dialog.dismiss()
            })
            builder.setNegativeButton("الغاء", { dialog, which ->
                dialog.dismiss()

            })

            val alertDialog = builder.create()
            alertDialog.show()

        } else if (v == imgThirdPrice) {
            imgThirdPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "جيد"
            // Toast.makeText(this, FifthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFourthPrice) {
            imgFourthPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "جيد جدا"
            // Toast.makeText(this, FifthImgBad, Toast.LENGTH_SHORT).show()
        } else if (v == imgFifthPrice) {
            imgFifthPrice.animate().rotationBy(360f).setDuration(1900);
            FifthImgBad = "ممتازه"
            //  Toast.makeText(this, FifthImgBad, Toast.LENGTH_SHORT).show()
        }
    }


    private fun sendEmail(recipient: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "ملاحظات العملاء")
        //put the message in the intent
        mIntent.putExtra(
            Intent.EXTRA_TEXT, "ملاحظات الارتياح العام : " + firstImgBad + "\n"
                    + "ملاحظات الهدوء : $SecondImgBad" + "\n"
                    + "ملاحظات درجة الحرارة : $ThirdImgBad" + "\n" + "تعليق عن درجة الحرارة : $ThirdImgBadComment"
                    + "\n" + "ملاحظات عن اداء الموظفه : $FourthImgBad" + "\n" + "التعليق عن اداء الموظفه : $FourthImgBadComment"
                    + "\n" + "ملاحظات عن الاسعار : $FifthImgBad" + "\n" + "التعليق عن الاسعار  : $FifthImgBadComment"
                    + "\n" + "اسم العميله : $customerName" + "\n" + "رقم الهاتف : $customerPhoneNumber"
                    + "\n" + "نوع الخدمه : $typeOfService" + "\n"
                    + "هل ستخبر الاصدقاء : $frindsEn"
        )


        try {

            startActivity(Intent.createChooser(mIntent, "من فضلك اختر ايميل جوجل ..."))
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
        var check: Boolean = v.isChecked
        when (v.id) {
            R.id.cbx1 -> if (check) {
                typeOfService += " علاجات الشعر : "
            } else {
                typeOfService = ""
            }

            R.id.cbx2 -> if (check) {
                typeOfService += " فيشال : "
            } else {
                typeOfService = ""
            }
            R.id.cbx3 -> if (check) {
                typeOfService += " مانيكير بيديكير : "
            } else {
                typeOfService = ""
            }
            R.id.cbx4 -> if (check) {
                typeOfService += " مساج : "
            } else {
                typeOfService = ""
            }


        }


    }





/////////// Data Base /////////////

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




