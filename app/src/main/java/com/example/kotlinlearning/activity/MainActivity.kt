package com.example.kotlinlearning.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlearning.R
import com.example.kotlinlearning.adapter.UserAdapter
import com.example.kotlinlearning.factory.UserViewModelFactory
import com.example.kotlinlearning.model.Data
import com.example.kotlinlearning.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView);
        lateinit var linearLayoutManager: LinearLayoutManager
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        var viewModelFactory = UserViewModelFactory("Abhishek")
        var viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)



        if (isNetworkConnected()) {

            viewModel.getUsers(1, this)

            val userListUpdateObserver: Observer<List<Data?>?> = object : Observer<List<Data?>?> {
                override fun onChanged(list: List<Data?>?) {
                    adapter = UserAdapter(list, this@MainActivity)
                    recyclerView.adapter = adapter
                }
            }

            viewModel.getUserMutableLiveData().observe(this, userListUpdateObserver)
        } else {

            AlertDialog.Builder(this).setTitle("No Internet Connection")
                    .setMessage("Please check your internet connection and try again")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
        }


    }


    private fun isNetworkConnected(): Boolean {
        //1
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        //3
        val networkCapabilities = if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.getNetworkCapabilities(activeNetwork)
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        //4
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}