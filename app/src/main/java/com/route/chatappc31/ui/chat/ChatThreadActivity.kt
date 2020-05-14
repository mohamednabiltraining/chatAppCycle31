package com.route.chatappc31.ui.chat

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.chatappc31.Constants
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ActivityChatThreadBinding
import com.route.chatappc31.onlineDataBase.model.Room
import com.route.notesapp.Base.BaseActivity

class ChatThreadActivity : BaseActivity<ActivityChatThreadBinding,ChatViewModel>() {

    val adapter= ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currentRoom = intent.getSerializableExtra(Constants.EXTRA_ROOM) as Room
        viewDataBinding.vm = viewModel
        viewModel.startRealTimeUpdate()
        viewDataBinding.messagesRecyclerView.adapter=adapter
        (viewDataBinding.messagesRecyclerView.layoutManager as LinearLayoutManager).stackFromEnd=true
        viewModel.messagesMutableLiveData.observe(this, Observer {
            adapter.changeData(it)
        })
     }


    override fun generateViewModel(): ChatViewModel {
        return ViewModelProvider(this).get(ChatViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_chat_thread
    }
}
