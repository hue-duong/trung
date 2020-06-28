package com.gmail.hue.duongt.myfriends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class DetailActivity : AppCompatActivity() {
    lateinit var titleView: TextView
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var ratingBar: RatingBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        titleView = findViewById(R.id.title)
        imageView = findViewById(R.id.imageView)
        ratingBar = findViewById(R.id.rating)
        textView = findViewById(R.id.textView)
        val intent: Intent = getIntent()
        val name: String = intent.getStringExtra("name" +
                "")
        var image: Int = intent.getIntExtra("image", 0)
        var description: String = intent.getStringExtra("description")
        var ratingValue: Float = intent.getFloatExtra("rating", 0.0f)

        titleView.text = name
        imageView.setImageResource(image)
        textView.text = description
        ratingBar.rating = ratingValue

        ratingBar.setOnRatingBarChangeListener( RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(
                application,
                "Số lượng đánh giá:" + ratingBar.getRating(),
                Toast.LENGTH_LONG
            ).show()
            Database.openingItem?.rating = rating
            Database.saveRatings()
        }
        )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Database.openingItem = null
    }
}