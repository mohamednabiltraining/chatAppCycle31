package com.route.chatappc31.onlineDataBase.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*


/**
 * Created by Mohamed Nabil Mohamed on 5/8/2020.
 * m.nabil.fci2015@gmail.com
 */
class Message (){
    var id= ""
    var content=""
    var senderName=""
    var senderId= ""
    var roomName= ""
    var roomId= ""

    @ServerTimestamp
    var date :Date? = null

}