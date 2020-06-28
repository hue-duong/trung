package com.gmail.hue.duongt.myfriends

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var gridView: GridView
    val items: List<ItemData> = Database.items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //finding listview
        gridView = findViewById(R.id.gridview)
        val adapter = FriendsAdapter(this, items)
        gridView.adapter = adapter
        gridView.onItemClickListener =
            OnItemClickListener { adapterView, view, i, l -> //                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                this.openItemDetail(items.get(i))
            }

    }

    override fun onResume() {
        super.onResume()
        val adapter = FriendsAdapter(this, items)
        gridView.adapter = adapter
    }

    private fun openItemDetail(item: ItemData) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("name", item.name)
        intent.putExtra("image", item.image)
        intent.putExtra("description", item.description)
        intent.putExtra("rating", item.rating)
        Database.openingItem = item
        startActivity(intent)
    }


    class FriendsAdapter : BaseAdapter {
        var friendsList: List<ItemData>
        var context: MainActivity? = null

        constructor(context: MainActivity, friendsList: List<ItemData>) : super() {
            this.context = context
            this.friendsList = friendsList
        }

        override fun getCount(): Int {
            return friendsList.size
        }

        override fun getItem(position: Int): Any {
            return friendsList[position]
        }

        override fun getItemId(position: Int): Long {
            return friendsList[position].id
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val friend = this.friendsList[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var friendView = inflator.inflate(R.layout.row_data, null)
            val name = friendView.findViewById<TextView>(R.id.name)
            val image: ImageView = friendView.findViewById(R.id.images)
            val ratingBar: RatingBar = friendView.findViewById(R.id.rating)
            name.text = friend.name
            image.setImageResource(friend.image)
            ratingBar.rating = friend.rating

            ratingBar.setOnRatingBarChangeListener( RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                Toast.makeText(context!!, "So luong danh gia: " + ratingBar.rating, Toast.LENGTH_LONG).show()
                friend.rating = rating
                Database.saveRatings()
            }
            )

            val button: Button = friendView.findViewById(R.id.button)
            button.setOnClickListener {
                context?.openItemDetail(friend)
            }

            return friendView
        }
    }
}