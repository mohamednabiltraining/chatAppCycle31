package com.route.chatappc31.onlineDataBase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.route.chatappc31.onlineDataBase.model.Message
import com.route.chatappc31.onlineDataBase.model.Room


/**
 * Created by Mohamed Nabil Mohamed on 5/8/2020.
 * m.nabil.fci2015@gmail.com
 */
class MessagesDao {
    companion object{


        fun addMessage(message:Message,onCompleteListener: OnCompleteListener<Void>){
           val ref =  DataBase.getMessages()
                .document(message.roomId);
            val roomMessages = ref.collection(message.roomName);
            val newMessageDoc  = roomMessages.document();
            message.id = newMessageDoc.id
            newMessageDoc.set(message)
                .addOnCompleteListener(onCompleteListener)

        }

        fun listenForRoom(currentRoom: Room?,listener:EventListener<QuerySnapshot>):ListenerRegistration{
            return DataBase.getMessages()
                .document(currentRoom?.id?:"")
                .collection(currentRoom?.name?:"")
                .orderBy("date",Query.Direction.ASCENDING)
                .limitToLast(100)
                .addSnapshotListener(listener)
        }
    }
}