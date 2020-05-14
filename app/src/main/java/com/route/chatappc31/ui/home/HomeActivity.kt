package com.route.chatappc31.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc31.Constants
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ActivityHomeBinding
import com.route.chatappc31.onlineDataBase.model.Room
import com.route.chatappc31.ui.addRoom.AddRoomActivity
import com.route.chatappc31.ui.chat.ChatThreadActivity
import com.route.chatappc31.ui.login.LoginActivity
import com.route.notesapp.Base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(),Navigator {

    val adapter= RoomsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator=this
        viewDataBinding.vm =viewModel
        viewDataBinding.fab.setOnClickListener({
            startActivity(Intent(this,AddRoomActivity::class.java))
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter.onItemClickListener = object :RoomsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: Room) {

                val intent = Intent(this@HomeActivity,ChatThreadActivity::class.java)
                intent.putExtra(Constants.EXTRA_ROOM,item)
                startActivity(intent)
            }
        };
        viewDataBinding.content.roomsRecyclerView.adapter=adapter
        viewModel.roomsData.observe(this, Observer{
            adapter.changeData(it)
        })

    }

    override fun onStart() {
        super.onStart()
        viewModel.getRooms()
    }

    override fun generateViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_home
    }

    override fun logout() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
