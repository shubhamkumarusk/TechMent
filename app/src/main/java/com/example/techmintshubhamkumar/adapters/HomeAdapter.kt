package com.example.techmintshubhamkumar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techmintshubhamkumar.R
import com.example.techmintshubhamkumar.databinding.RepoLayoutBinding
import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.models.GitHubResponse

class HomeAdapter(private val onRepoClicked : (GitHubRepo)->Unit):ListAdapter<GitHubRepo,HomeAdapter.HomeViewHolder>(DiffCallback){


    class HomeViewHolder(private val binding:RepoLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(repo:GitHubRepo){
            binding.repoOwnerNameTv.text = repo.owner?.login
            binding.description.text = repo.description
            Glide.with(itemView.context)
                .load(repo.owner?.avatar_url)
                .centerCrop()
                .into(binding.image)

        }
    }

    companion object{
        private val DiffCallback = object :DiffUtil.ItemCallback<GitHubRepo>(){

            override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
                return oldItem.id==newItem.id
            }


            override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
                return oldItem==newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            RepoLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val curr = getItem(position)
        holder.itemView.setOnClickListener {
            onRepoClicked(curr)
        }
        holder.bind(curr)
    }


}