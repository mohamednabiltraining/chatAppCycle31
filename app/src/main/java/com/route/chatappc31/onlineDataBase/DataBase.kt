package com.route.chatappc31.onlineDataBase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


/**
 * Created by Mohamed Nabil Mohamed on 5/1/2020.
 * m.nabil.fci2015@gmail.com
 */

class DataBase {

    companion object{
        val USERS_REF = "users";
        val ROOMS_REF = "rooms";
        val MESSAGES = "messages";
        val db = Firebase.firestore

        fun getUsers():CollectionReference{
            return db.collection(USERS_REF);
        }
        fun getRooms():CollectionReference{
            return db.collection(ROOMS_REF);
        }
        fun getMessages():CollectionReference{
            return db.collection(MESSAGES);
        }
    }

}