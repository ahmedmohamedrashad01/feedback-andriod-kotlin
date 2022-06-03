package com.example.salon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.list_view.view.*

class NoteAdapter(context : Context , noteList : ArrayList<Note>) : ArrayAdapter<Note>(context,0,noteList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(context).inflate(R.layout.list_view,parent,false)

        var note: Note? = getItem(position)
        view.txtName.text = note!!.customerName
        view.txPhone.text = note.customerPhoneNumber.toString()
        view.txDate.text = note!!.timestamp
        view.txOverall.text = note!!.firstImgBad
        view.txQuietness.text = note!!.SecondImgBad
        view.txTemperature.text = note!!.ThirdImgBad
        view.txTemperatureComment.text = note!!.ThirdImgBadComment
        view.txPerformance.text = note!!.FourthImgBad
        view.txPerformanceComment.text = note!!.FourthImgBadComment
        view.txPrice.text = note!!.FifthImgBad
        view.txPriceComment.text = note!!.FifthImgBadComment
        view.txtService.text = note!!.typeOfService
        view.txFriends.text = note!!.frindsEn
        return view
    }

}