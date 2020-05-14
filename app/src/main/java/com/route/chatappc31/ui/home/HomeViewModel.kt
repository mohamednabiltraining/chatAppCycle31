package com.route.chatappc31.ui.home

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.route.Base.BaseViewModel
import com.route.chatappc31.onlineDataBase.DataHolder
import com.route.chatappc31.onlineDataBase.RoomsDao
import com.route.chatappc31.onlineDataBase.model.Room


/**
 * Created by Mohamed Nabil Mohamed on 4/17/2020.
 * m.nabil.fci2015@gmail.com
 */
class HomeViewModel: BaseViewModel<Navigator>(){

    val roomsData = MutableLiveData<List<Room>>()
    init {
        getRooms()
    }

    fun getRooms(){
        RoomsDao.getRooms(OnCompleteListener {
            val documents = it.result?.documents
            val roomsList = mutableListOf<Room>()
            for(document in documents!!){
                val room= document.toObject(Room::class.java)
                if (room==null)continue
                roomsList.add(room)
            }
            roomsData.value = roomsList

        })
    }

    fun logout(){
        FirebaseAuth.getInstance().signOut()
        DataHolder.authUser=null
        DataHolder.dataBaseUser=null
        navigator?.logout()
    }


}