package com.thiagoc.desafiopicpay.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagoc.desafiopicpay.R
import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.presentation.ui.adapter.UserListAdapter
import com.thiagoc.desafiopicpay.presentation.viewmodel.MainActivityViewModel
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewAction
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewState

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponentes()
        configureObserver()
        getUsers()

    }

    private fun initComponentes(){
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
    }

    private fun configureObserver() {
        viewModel.viewState.observe(this, Observer { viewState ->
            when (viewState) {
                is UserListViewState.Error -> viewState.message?.let { showToastError(it) }
                is UserListViewState.ShowUsers -> showUsers(viewState.users)
                UserListViewState.Loading -> showLoading()
            }
        })
    }

    private fun getUsers(){
        viewModel.dispatchAction(UserListViewAction.GetUsers)
    }

    private fun showToastError(message: String) {
        TODO("Not yet implemented")
    }



    private fun showLoading() {
        TODO("Not yet implemented")
    }

    private fun showUsers(users: List<UserDomain>) {
        TODO("Not yet implemented")
    }
}
