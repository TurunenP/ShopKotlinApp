package com.example.shopkotlinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class HomePageFragment : Fragment() {

    var itemList = ArrayList<Item>()

     val item1 = Item(R.drawable.jacket, "Jacket", "$100")
    private var database: FirebaseDatabase? = null
    private var reference: DatabaseReference? = null


   val item2 = Item(R.drawable.adidas_shoes, "Nice Adidas", "$100")
    val item3 = Item(R.drawable.shirt, "Shirt", "$100")
     val item4 = Item(R.drawable.shoes, "A pair of shoes", "$100")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        database = FirebaseDatabase.getInstance()
        reference = database?.getReference("items")



        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context,
        2,
        GridLayoutManager.VERTICAL,
        false)

        itemList.add(item1)
        itemList.add(item2)
        itemList.add(item3)
        itemList.add(item4)


        val adapter = ItemAdapter(itemList)
        recyclerView.adapter = adapter

       // view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
        //    Firebase.auth.signOut()
        //    var navLogin = activity as FragmentNav
         //   navLogin.navigateFragment(LoginFragment(), false)
      //  }

        return view
    }
}