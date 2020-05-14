package com.route.chatappc31.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.chatappc31.onlineDataBase.model.Room
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ItemRoomBinding


/**
 * Created by Mohamed Nabil Mohamed on 5/8/2020.
 * m.nabil.fci2015@gmail.com
 */

class RoomsAdapter():RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    var rooms=  listOf<Room>();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ItemRoomBinding>(LayoutInflater.from(parent.context),
            R.layout.item_room,parent,false);
        return ViewHolder(view)
    }

    fun changeData(newRooms:List<Room>){
        rooms=newRooms
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rooms.get(position))
        if (onItemClickListener!=null)
            holder.itemView.setOnClickListener({
                onItemClickListener?.onItemClick(position,rooms.get(position))
            })
    }
    var onItemClickListener:OnItemClickListener? =null

    interface OnItemClickListener {
        fun onItemClick(position: Int,item:Room);
    }
    class ViewHolder(val binding :ItemRoomBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: Room){
            binding.item = item
            binding.executePendingBindings()
        }

    }
}