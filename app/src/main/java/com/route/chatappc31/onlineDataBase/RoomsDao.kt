package com.route.chatappc31.onlineDataBase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.route.chatappc31.onlineDataBase.model.Room


/**
 * Created by Mohamed Nabil Mohamed on 5/1/2020.
 * m.nabil.fci2015@gmail.com
 */
class RoomsDao {

    companion object{
        fun addRoom(room: Room,onCompleteListener: OnCompleteListener<Void> ){
           val roomDoc =  DataBase.getRooms()
                .document();
            room.id=roomDoc.id
            roomDoc.set(room)
                .addOnCompleteListener(onCompleteListener)
        }

        fun getRooms(onCompleteListener: OnCompleteListener<QuerySnapshot>){
            DataBase.getRooms()
                .get()
                .addOnCompleteListener(onCompleteListener)

        }
    }
}