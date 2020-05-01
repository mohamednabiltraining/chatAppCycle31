package com.route.chatappc31.ui.addRoom

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ActivityAddRoomBinding
import com.route.notesapp.Base.BaseActivity

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel.navigator =this
    }


    override fun generateViewModel(): AddRoomViewModel =
        ViewModelProvider(this).get(AddRoomViewModel::class.java)


    override fun getLayoutID(): Int = R.layout.activity_add_room
}
