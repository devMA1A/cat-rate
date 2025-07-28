package com.example.homework3attempt

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class AnimalRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animal_rating2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val image = intent.getStringExtra("Image") ?: ""
        val name = intent.getStringExtra("Name")
        val rate = intent.getFloatExtra("Rate", 0f)
        findViewById<RatingBar>(R.id.ratingBar2).rating = rate
        val imageVi = findViewById<ImageView>(R.id.imageView2)
        Glide.with(this)
            .load(image)
            .into(imageVi)

        val nameTextView = findViewById<TextView>(R.id.textViewCatName)
        if(name != null){
            nameTextView.text = name
        }

        val buttonToSave = findViewById<Button>(R.id.buttonSave)
        buttonToSave.setOnClickListener{

//al myIntent = Intent()
            val savedRating =   findViewById<RatingBar>(R.id.ratingBar2).rating.toString()
            if (name != null) {
                saveData(savedRating, name)
            }
            finish() // Stops and closes the second activity
        }
        }

fun saveData(savedRate: String, name: String ) {
    val FILE_NAME = "my_data"

    // Create a SharedPreferences instance using the specified file name and mode
    val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
    // Create an editor to modify SharedPreferences data
   val editor = sharedPreferences.edit()

    editor.putString(name, savedRate)
    editor.apply()
  //  Toast.makeText(this, "The data has been saved!", Toast.LENGTH_SHORT).show()

}
}






