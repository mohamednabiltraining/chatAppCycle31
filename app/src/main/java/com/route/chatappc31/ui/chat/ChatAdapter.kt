package com.route.chatappc31.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.chatappc31.R
import com.route.chatappc31.databinding.IncomingMessageBinding
import com.route.chatappc31.databinding.OutgoingMessageBinding
import com.route.chatappc31.onlineDataBase.DataHolder
import com.route.chatappc31.onlineDataBase.model.Message


/**
 * Created by Mohamed Nabil Mohamed on 5/8/2020.
 * m.nabil.fci2015@gmail.com
 */

class ChatAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var messages = listOf<Message>()

    val INCOMING_MESSAGE_TYPE = 1;
    val OUTGOING_MESSAGE_TYPE = 2;

    override fun getItemViewType(position: Int): Int {
        val message= messages.get(position)
        if (message.senderId.equals(DataHolder.dataBaseUser?.id)){
            return OUTGOING_MESSAGE_TYPE
        }
        return INCOMING_MESSAGE_TYPE
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == INCOMING_MESSAGE_TYPE){
            val view = DataBindingUtil.inflate<IncomingMessageBinding>(
                LayoutInflater.from(parent.context), R.layout.incoming_message,parent,false)
            return IncomingMessageViewHolder(view)
        }else {
            val view = DataBindingUtil.inflate<OutgoingMessageBinding>(
                LayoutInflater.from(parent.context), R.layout.outgoing_message,parent,false)
            return OutgoingMessageViewHolder(view)

        }
    }

    fun changeData(messages:List<Message>){
        this.messages=messages;
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType==INCOMING_MESSAGE_TYPE){
            holder as IncomingMessageViewHolder
            holder.bind(messages.get(position))
        }else {
            holder as OutgoingMessageViewHolder
            holder.bind(messages.get(position))
        }
    }

    class IncomingMessageViewHolder(val binding:IncomingMessageBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(item:Message){
            binding.message = item
            binding.executePendingBindings()
        }
    }
    class OutgoingMessageViewHolder(val binding:OutgoingMessageBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(item:Message){
            binding.message = item
            binding.executePendingBindings()
        }
    }


}