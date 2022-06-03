package com.example.salon

class Note {
    var id : String? = null
    var firstImgBad  : String? = null
    var SecondImgBad : String? = null
    var ThirdImgBad : String? = null
    var ThirdImgBadComment : String? = null
    var FourthImgBad : String? = null
    var FourthImgBadComment : String? = null
    var FifthImgBad : String? = null
    var FifthImgBadComment : String? = null
    var customerName : String? = null
    var customerPhoneNumber : String? = null
    var typeOfService : String? = null
    var frindsEn : String? = null
    var timestamp : String? = null

    constructor(){

    }

    constructor(id: String?, firstImgBad: String, SecondImgBad: String,ThirdImgBad: String,
                ThirdImgBadComment: String,
                FourthImgBad: String,
                FourthImgBadComment: String,
                FifthImgBad: String,
                FifthImgBadComment: String,
                customerName: String,
                customerPhoneNumber: String,
                typeOfService: String?,
                frindsEn: String,
                timestamp: String) {
        this.id = id
        this.firstImgBad = firstImgBad
        this.SecondImgBad = SecondImgBad
        this.ThirdImgBad = ThirdImgBad
        this.ThirdImgBadComment = ThirdImgBadComment
        this.FourthImgBad = FourthImgBad
        this.FourthImgBadComment = FourthImgBadComment
        this.FifthImgBad = FifthImgBad
        this.FifthImgBadComment = FifthImgBadComment
        this.customerName = customerName
        this.customerPhoneNumber = customerPhoneNumber
        this.typeOfService = typeOfService
        this.frindsEn = frindsEn
        this.timestamp = timestamp

    }



}