package com.example.kotlinlearning.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinlearning.R
import com.example.kotlinlearning.model.Data


class UserAdapter(private val dataSet: List<Data?>? , val context : Context) :
        RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tv_id.text = dataSet?.get(position)?.id.toString()
        viewHolder.tv_userName.text = (dataSet?.get(position)?.first_name ) + dataSet?.get(position)?.last_name
        viewHolder.tv_userEmail.text = dataSet?.get(position)?.email

        if (!dataSet?.get(position)?.avatar.equals(null) ) {
            Glide.with(context)
                    .load(dataSet?.get(position)?.avatar)
                    .into(viewHolder.imageView)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_id: TextView = view.findViewById(R.id.tv_id)
        var tv_userName: TextView = view.findViewById(R.id.tv_userName)
        var tv_userEmail: TextView = view.findViewById(R.id.tv_userEmail)
        var imageView : ImageView = view.findViewById(R.id.img_view)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }
}
