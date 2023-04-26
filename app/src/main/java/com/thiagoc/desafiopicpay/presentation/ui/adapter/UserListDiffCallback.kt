package com.thiagoc.desafiopicpay.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.thiagoc.desafiopicpay.domain.UserDomain

class UserListDiffCallback(
    private val oldList: List<UserDomain>,
    private val newList: List<UserDomain>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username == newList[newItemPosition].username
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}