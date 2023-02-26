package com.example.usermvvmapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usermvvmapp.R
import com.example.usermvvmapp.ShowUserDetails
import com.example.usermvvmapp.extension.ExtensionClass.getAllName
import com.example.usermvvmapp.extension.ExtensionClass.getUserLocation
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView

class AdapterUser(private val context: Context, private val users: List<com.example.usermvvmapp.model.Result>) : RecyclerView.Adapter<AdapterUser.ViewHolderUser>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        return ViewHolderUser(LayoutInflater.from(context).inflate(R.layout.user_details, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        val currentPosition = users[position]
        holder.name.text = currentPosition.name?.getAllName()
        holder.email.text = currentPosition.email
        holder.phone.text = currentPosition.phone

        Glide.with(context).load(currentPosition.picture?.large).into(holder.image)

        holder.cardView.setOnClickListener {
            val intent = Intent(context, ShowUserDetails::class.java)
            intent.putExtra("image", currentPosition.picture?.large)
            intent.putExtra("email", currentPosition.email.toString())
            intent.putExtra("name", currentPosition.name?.getAllName().toString())
            intent.putExtra("phone", currentPosition.phone.toString())
            intent.putExtra("gender", currentPosition.gender)
            intent.putExtra("location", currentPosition.location?.getUserLocation().toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolderUser(view : View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.name)
        val email: TextView = view.findViewById(R.id.email)
        val phone: TextView = view.findViewById(R.id.phone)
        val image: ShapeableImageView = view.findViewById(R.id.image)
        val cardView : MaterialCardView = view.findViewById(R.id.cardView)

    }
}