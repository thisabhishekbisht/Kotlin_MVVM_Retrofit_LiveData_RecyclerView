package com.example.kotlinlearning.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinlearning.model.Data
import com.example.kotlinlearning.model.UsersResponse
import com.example.kotlinlearning.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel(context: String) : ViewModel() {

    //val userData: List<Data>

    lateinit var toast: Toast

    var users = MutableLiveData<List<Data>>()

    fun getUserMutableLiveData(): MutableLiveData<List<Data>> {
        return users
    }

    fun getUsers(page: Int, context: Context) {
        var service = RetrofitInstance.apiInterface
        var call = service.getUsers(page)
        call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) {
                    toast = Toast.makeText(context, "Sucessfull", Toast.LENGTH_SHORT)

                    users.value = response.body()?.data;

                    toast.show()
                } else {
                    toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

}