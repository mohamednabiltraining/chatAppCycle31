package com.route.chatappc31.OnlineDataBase

import com.google.firebase.auth.FirebaseUser
import com.route.chatappc31.OnlineDataBase.model.User


/**
 * Created by Mohamed Nabil Mohamed on 5/1/2020.
 * m.nabil.fci2015@gmail.com
 */
class DataHolder{
    companion object{
        var dataBaseUser:User?=null
        var authUser:FirebaseUser?=null
    }
}