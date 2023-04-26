package com.thiagoc.desafiopicpay.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.thiagoc.desafiopicpay.R
import com.thiagoc.desafiopicpay.databinding.ListItemUserBinding
import com.thiagoc.desafiopicpay.domain.UserDomain
import de.hdodenhof.circleimageview.CircleImageView

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var userList = mutableListOf<UserDomain>()

    fun updateList(newUserList: List<UserDomain>) {
        val oldUserList = mutableListOf<UserDomain>().apply { addAll(userList) }
        val diffResult = DiffUtil.calculateDiff(UserListDiffCallback(oldUserList, newUserList))
        userList.clear()
        userList.addAll(newUserList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView: ListItemUserBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val userName: TextView = itemView.username
        private val name: TextView = itemView.name
        private val imageUser: CircleImageView = itemView.picture
        private val progressBar: ProgressBar = itemView.progressBar

        fun bind(user: UserDomain) {
            userName.text = user.username
            name.text = user.name
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(imageUser, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                    }
                })
        }
    }
}
