package com.route.chatappc31.onlineDataBase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import com.route.chatappc31.onlineDataBase.model.User


/**
 * Created by Mohamed Nabil Mohamed on 5/1/2020.
 * m.nabil.fci2015@gmail.com
 */
class UsersDao {

    companion object{

        fun addUser(user:User,onCompleteListener: OnCompleteListener<Void>){
           val userDoc =  DataBase.getUsers()
                .document(user.id?:"");
            userDoc.set(user)
                .addOnCompleteListener(onCompleteListener)

        }
        fun getUser(userId:String,onCompleteListener: OnCompleteListener<DocumentSnapshot>){
            DataBase.getUsers()
                .document(userId)
                .get()
                .addOnCompleteListener(onCompleteListener)
        }
    }
}