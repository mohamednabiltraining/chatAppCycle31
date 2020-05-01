package com.route.Base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by Mohamed Nabil Mohamed on 4/17/2020.
 * m.nabil.fci2015@gmail.com
 */
open class BaseViewModel<N> :ViewModel(){

    var navigator:N? =null
    val showLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()

}