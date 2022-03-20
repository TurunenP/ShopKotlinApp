package com.example.shopkotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private var itemList: MutableList<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ItemViewHolder {

        val layoutView:View = LayoutInflater.from(parent.context).
                inflate(R.layout.item_card_view, parent, false)
        return ItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       holder.itemImage.setImageResource(itemList[position].img)
      //  Picasso.get().load(itemList[position].img)
      //  Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/shopkotlinapp.appspot.com/o/jacket.jpg?alt=media&token=041bc276-1b03-4a62-adb4-537a00fc217b")
       //     .into(holder.itemImage)
        holder.itemTitle.text = itemList[position].title
        holder.itemPrice.text = itemList[position].price
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.item_image)
        var itemTitle: TextView = view.findViewById(R.id.item_title)
        var itemPrice: TextView = view.findViewById(R.id.item_price)

    }
}