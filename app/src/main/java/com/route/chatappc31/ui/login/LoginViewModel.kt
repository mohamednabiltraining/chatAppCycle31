package com.route.chatappc31.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.toObject
import com.route.Base.BaseViewModel
import com.route.chatappc31.OnlineDataBase.DataHolder
import com.route.chatappc31.OnlineDataBase.UsersDao
import com.route.chatappc31.OnlineDataBase.model.User


/**
 * Created by Mohamed Nabil Mohamed on 4/5/2020.
 * m.nabil.fci2015@gmail.com
 */

class LoginViewModel :BaseViewModel<Navigator>() {

    val userName = ObservableField<String>()
    val password = MutableLiveData<String>()
    val userNameError = ObservableField<Boolean>(false)
    val passwordError = ObservableField<Boolean>(false)

    val authUser  = MutableLiveData<FirebaseUser>()
    private  var auth:FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser!=null){
//            authUser.value = auth.currentUser
            UsersDao.getUser(auth.currentUser?.uid?:"",
                OnCompleteListener {
                    if (it.isSuccessful) {
                        it.result?.toObject<User>()
                        val DatabaseUser = it.result?.toObject(User::class.java)
                        DataHolder.dataBaseUser = DatabaseUser;
                        DataHolder.authUser = auth.currentUser
                        authUser.value = auth.currentUser
                    }else {
                        message.value = it.exception?.localizedMessage
                    }

                })
        }
    }
    fun Login(){
        // validation
        //call login api
        if (isValidData()){
            //call login api
            //show loading
            showLoading.value=true
            auth.signInWithEmailAndPassword(userName.get()?:"", password.value?:"")
                .addOnCompleteListener(OnCompleteListener {
                    showLoading.value=false
                    if (it.isSuccessful){
                        //navigatge to home screen
                        UsersDao.getUser(auth.currentUser?.uid?:"",
                            OnCompleteListener {
                                if (it.isSuccessful) {
                                    val DatabaseUser = it.result?.toObject(User::class.java)
                                    DataHolder.dataBaseUser = DatabaseUser;
                                    DataHolder.authUser = auth.currentUser
                                    authUser.value = auth.currentUser
                                }else {
                                    message.value = it.exception?.localizedMessage
                                }

                            })

                    }else {
                        message.value = it.exception?.localizedMessage
                    }
                })

        }
    }

    fun register(){
        navigator?.openRegister()
    }
    fun isValidData():Boolean{
        var isValid = true;
        if (userName.get().isNullOrBlank()){
            //show Error
            userNameError.set(true)
           // message.value = "please enter a valid user name ";
            isValid=false;
        }else {
            userNameError.set(false)
        }
        if (password.value.isNullOrEmpty()){
            //show Error
            passwordError.set(true)
           // message.value = "please enter a valid password ";
            isValid=false
        }else {
            passwordError.set(false)
        }
        return isValid;
    }


}