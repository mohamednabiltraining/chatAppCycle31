package com.route.chatappc31.ui.addRoom

import android.content.DialogInterface
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ActivityAddRoomBinding
import com.route.notesapp.Base.BaseActivity

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel.navigator =this
        viewDataBinding.vm = viewModel
        subscribeToLiveData()
    }

    fun subscribeToLiveData(){
        viewModel.roomAdded.observe(this, Observer {
            if (it){
                showMessage(null,R.string.room_added_successfully,R.string.ok,
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                        finish()
                    },null,null,false);
            }
        })
    }


    override fun generateViewModel(): AddRoomViewModel =
        ViewModelProvider(this).get(AddRoomViewModel::class.java)


    override fun getLayoutID(): Int = R.layout.activity_add_room
}
