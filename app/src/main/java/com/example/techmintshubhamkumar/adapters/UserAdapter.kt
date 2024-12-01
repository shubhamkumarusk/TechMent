package com.example.techmintshubhamkumar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techmintshubhamkumar.databinding.UsersLayoutBinding
import com.example.techmintshubhamkumar.models.Users

class UserAdapter : ListAdapter<Users,UserAdapter.UserViewHolder>(DiffCallBack)  {

    class UserViewHolder(private val binding: UsersLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user:Users){
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .centerCrop()
                .into(binding.image)

            binding.repoOwnerNameTv.text = user.login
        }
    }
    companion object{
        private val DiffCallBack = object : DiffUtil.ItemCallback<Users>(){

            override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
                return oldItem.id==newItem.id
            }


            override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
                return oldItem==newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       return UserViewHolder(
           UsersLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       )
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currUser = getItem(position)
        holder.bind(currUser)
    }
}