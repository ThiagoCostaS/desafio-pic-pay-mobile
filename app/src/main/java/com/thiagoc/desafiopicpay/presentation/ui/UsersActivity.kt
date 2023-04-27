package com.thiagoc.desafiopicpay.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thiagoc.desafiopicpay.databinding.ActivityMainBinding
import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.presentation.ui.adapter.UserListAdapter
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewAction
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewState
import com.thiagoc.desafiopicpay.presentation.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UsersViewModel by viewModel()
    private lateinit var adapterListUser: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        getUsers()
    }

    private fun setupObserver() {
        viewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is UserListViewState.Error -> viewState.message?.let { showToastError(it) }
                is UserListViewState.ShowUsers -> showUsers(viewState.users)
                UserListViewState.Loading -> showLoading()
            }
        }
    }

    private fun getUsers() {
        viewModel.dispatchAction(UserListViewAction.GetUsers)
    }

    private fun showToastError(message: String) {
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.userListProgressBar.visibility = View.GONE
    }

    private fun showUsers(users: List<UserDomain>) {
        hideLoading()
        adapterListUser = UserListAdapter()
        adapterListUser.updateList(users)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = adapterListUser
            layoutManager =
                LinearLayoutManager(this@UsersActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}
