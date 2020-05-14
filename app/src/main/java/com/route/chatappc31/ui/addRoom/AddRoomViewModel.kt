package com.route.chatappc31.ui.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.route.Base.BaseViewModel
import com.route.chatappc31.onlineDataBase.RoomsDao
import com.route.chatappc31.onlineDataBase.model.Room


/**
 * Created by Mohamed Nabil Mohamed on 5/1/2020.
 * m.nabil.fci2015@gmail.com
 */
class AddRoomViewModel :BaseViewModel<Navigator>(){


    val name = ObservableField<String>()
    val desc = ObservableField<String>()
    val descError = ObservableField<Boolean>(false)
    val nameError = ObservableField<Boolean>(false)

    val roomAdded = MutableLiveData<Boolean>()

    fun addRoom(){
        if (validData()){
            showLoading.value=true
            var room = Room()
            room.name=name.get()
            room.description = desc.get()
            RoomsDao.addRoom(room, OnCompleteListener {
                showLoading.value=false
                if (it.isSuccessful){
                    roomAdded.value=true
                }else {
                    message.value = it.exception?.localizedMessage
                }
            })
        }
    }

    private fun validData(): Boolean {
        var valid = true;
        if (name.get().isNullOrBlank()){
            valid = false;
            nameError.set(true)
        }else {
            nameError.set(false)
        }
        if (desc.get().isNullOrBlank()){
            valid= false
            descError.set(true)
        }else {
            descError.set(false)
        }

        return valid
    }

}