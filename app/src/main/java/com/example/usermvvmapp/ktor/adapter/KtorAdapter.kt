package com.example.usermvvmapp.ktor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.usermvvmapp.databinding.KtorLayoutBinding
import com.example.usermvvmapp.ktor.model.Post

class KtorAdapter : ListAdapter<Post, KtorAdapter.KtorViewHolder>(diffCallback) {

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<Post?>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KtorViewHolder {
        return KtorViewHolder(KtorLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: KtorViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    class KtorViewHolder(private val ktorBinding: KtorLayoutBinding) : ViewHolder(ktorBinding.root){
        fun bind(post : Post){
            ktorBinding.apply {
                body.text = post.body
            }
        }
    }
}