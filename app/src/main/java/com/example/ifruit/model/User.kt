package com.example.ifruit.model

import android.provider.ContactsContract

class User {
    var id: Int = -1
    var name: String?=null
    var email: String?=null
    var password: String?=null


    constructor(){}

    constructor(id:Int,name:String,email: String,password:String){
        this.id=id
        this.name=name
        this.email=email
        this.password=password

    }

}