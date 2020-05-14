package com.route.chatappc31.ui.register

import androidx.databinding.ObservableField
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.route.Base.BaseViewModel
import com.route.chatappc31.onlineDataBase.DataHolder
import com.route.chatappc31.onlineDataBase.UsersDao
import com.route.chatappc31.onlineDataBase.model.User


/**
 * Created by Mohamed Nabil Mohamed on 4/17/2020.
 * m.nabil.fci2015@gmail.com
 */
class RegisterViewModel :BaseViewModel<Navigator>(){
    val userName = ObservableField<String>()
    val password = ObservableField<String>()
    val email = ObservableField<String>()
    val phone = ObservableField<String>()

    val userNameError = ObservableField<Boolean>(false)
    val passwordError = ObservableField<Boolean>(false)
    val emailError = ObservableField<Boolean>(false)
    val phoneError = ObservableField<Boolean>(false)

    var auth :FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
    }
    fun register(){
        if (isValidData()){
            //register user in firebase
            showLoading.value=true
            auth.createUserWithEmailAndPassword(email.get()?:"",password.get()?:"")
                .addOnCompleteListener({
                    showLoading.value=false
                    if (it.isSuccessful){
                        val newUser = User()
                        newUser.id = it.result?.user?.uid?:""
                        newUser.name = userName.get()?:""
                        newUser.phone = phone.get()?:""
                        newUser.email = email.get()?:""

                        UsersDao.addUser(newUser, OnCompleteListener {
                            if (it.isSuccessful){
                                DataHolder.dataBaseUser = newUser;
                                DataHolder.authUser = auth.currentUser
                                navigator?.openHomeActivity()
                            }else {
                                message.value = "failed to register user .. try again later "+
                                        it.exception?.localizedMessage
                            }
                        })
                    }else {
                        message.value = it.exception?.localizedMessage
                    }
                })
        }
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
        if (password.get().isNullOrEmpty() || password.get()?.length?:0 < 6){
            //show Error
            passwordError.set(true)
            // message.value = "please enter a valid password ";
            isValid=false
        }else {
            passwordError.set(false)
        }
        if (phone.get().isNullOrEmpty()){
            //show Error
            phoneError.set(true)
            // message.value = "please enter a valid password ";
            isValid=false
        }else {
            phoneError.set(false)
        }
        if (email.get().isNullOrEmpty()){
            //show Error
            emailError.set(true)
            // message.value = "please enter a valid password ";
            isValid=false
        }else {
            emailError.set(false)
        }
        return isValid;
    }
}