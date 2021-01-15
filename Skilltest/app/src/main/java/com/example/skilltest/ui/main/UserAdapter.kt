package com.example.skilltest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.skilltest.data.User
import com.example.skilltest.databinding.UserItemBinding

class UserAdapter : ListAdapter<User, UserAdapter.UserHolder>(DiffCallback()) {

    private var listener: OnItemClickListener? = null

    class UserHolder(
        val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(userId: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val currentUser: User = getItem(position)
        holder.binding.user = currentUser

        this.listener?.let { listener ->
            holder.binding.setClickListener {
                listener.onItemClick(currentUser._id!!)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.externalId == newItem.externalId

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            areItemsTheSame(oldItem, newItem)
                    && oldItem.avatarUrl == newItem.avatarUrl
                    && oldItem.userName == newItem.userName
    }
}