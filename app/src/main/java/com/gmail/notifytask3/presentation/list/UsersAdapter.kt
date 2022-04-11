package com.gmail.notifytask3.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.databinding.ItemListBinding
import com.gmail.notifytask3.util.Constants

class UsersAdapter(
    private val onClick: (User) -> Unit,
    private val usersFetchCallback: () -> Unit
) : ListAdapter<User, UsersAdapter.UsersViewHolder>(ItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UsersViewHolder(ItemListBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClick)
        if (itemCount - position == Constants.USER_PREFETCH_CONT) {
            usersFetchCallback()
        }
    }

    class UsersViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onClick: (User) -> Unit) {
            with(binding) {
                userName.text = user.firstName
                root.setOnClickListener { onClick(user) }
            }
        }
    }
}

object ItemDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}