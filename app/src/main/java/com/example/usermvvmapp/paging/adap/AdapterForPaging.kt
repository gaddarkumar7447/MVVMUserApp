package com.example.usermvvmapp.paging.adap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.usermvvmapp.databinding.CharactorLayoutPagingBinding
import com.example.usermvvmapp.paging.pagingmodel.ApiResultData

class AdapterForPaging : PagingDataAdapter<ApiResultData, AdapterForPaging.ImageViewHolder>(differCallback) {

    inner class ImageViewHolder(val binding: CharactorLayoutPagingBinding) : ViewHolder(binding.root)

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<ApiResultData>() {
            override fun areItemsTheSame(oldItem: ApiResultData, newItem: ApiResultData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ApiResultData, newItem: ApiResultData): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = getItem(position)
        with(holder.binding) {
            with(holder.itemView) {
                tvName.text = currentItem?.name
                tvStatus.text = currentItem?.status
                tvGender.text = currentItem?.gender
                tvSpecies.text = currentItem?.species
                Glide.with(context).load(currentItem?.image).into(imageViewPaging)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(CharactorLayoutPagingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

}