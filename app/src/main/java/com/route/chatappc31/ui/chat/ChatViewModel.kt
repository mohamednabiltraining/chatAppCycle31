package com.route.chatappc31.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.route.Base.BaseViewModel
import com.route.chatappc31.onlineDataBase.DataHolder
import com.route.chatappc31.onlineDataBase.MessagesDao
import com.route.chatappc31.onlineDataBase.model.Message
import com.route.chatappc31.onlineDataBase.model.Room
import java.util.*


/**
 * Created by Mohamed Nabil Mohamed on 5/8/2020.
 * m.nabil.fci2015@gmail.com
 */
class ChatViewModel :BaseViewModel<Navigator>(){

    var currentRoom: Room? = null
    val messageText= ObservableField<String>("")

    fun sendMessage(){
        if (messageText.get().isNullOrBlank()){
            return
        }
        val messageToSend = Message();
        messageToSend.content = messageText.get()?:""
        messageToSend.senderId = DataHolder.dataBaseUser?.id?:""
        messageToSend.senderName = DataHolder.dataBaseUser?.name?:""
        messageToSend.roomId = currentRoom?.id?:""
        messageToSend.roomName = currentRoom?.name ?:""
        messageToSend.date = Date()

        MessagesDao.addMessage(messageToSend, OnCompleteListener {
            if (it.isSuccessful){
                messageText.set("")
            }else {
                message.value = it.exception?.localizedMessage
            }
        })

    }

    val messages = mutableListOf<Message>()

    val messagesMutableLiveData = MutableLiveData<List<Message>>()
    val roomListener = object :EventListener<QuerySnapshot>{
        override fun onEvent(query: QuerySnapshot?, e: FirebaseFirestoreException?) {
            if (e!=null){
                message.value = e.localizedMessage
                return
            }

            for(doc in query!!.documentChanges){
                when(doc.type){
                    DocumentChange.Type.ADDED->{
                        val message = doc.document.toObject(Message::class.java)
                        messages.add(message)
                    }
                }
            }
            messagesMutableLiveData.value = messages
        }
    }

    var listenerRegistration:ListenerRegistration?=null
    fun startRealTimeUpdate(){
        listenerRegistration = MessagesDao.listenForRoom(currentRoom,roomListener)
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }

}