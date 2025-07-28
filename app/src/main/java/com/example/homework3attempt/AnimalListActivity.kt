package com.example.homework3attempt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnimalListActivity : AppCompatActivity() {
    val numberOfCats = 20

    val FILE_NAME = "my_data"
    override fun onStart() {
        super.onStart()
        loadData()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)

        // Store the the recyclerView widget in a variable
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val cats = generateCatImg(numberOfCats)

        // specify an viewAdapter for the dataset (we use dummy data containing such as 20 contacts)
        val recyclerViewAdapter = CatAdapter(cats)
        recyclerView.adapter = recyclerViewAdapter

        // A helper function to create specified amount of dummy contact data
        recyclerView.layoutManager = LinearLayoutManager(this)

       val clear =  findViewById<Button>(R.id.buttonClear)
        clear.setOnClickListener{
            val editor = sharedPreferences.edit()
            // Clear the saved data (if previously saved)
            editor.clear()
            // apply changes -- DO NOT FORGET!!!
            editor.apply()
            Toast.makeText(this, "Ratings cleared!", Toast.LENGTH_SHORT).show()
            loadData()


        }
    }


    fun generateCatImg(size: Int): ArrayList<Cats> {
        // Create an ArrayList to store contacts
        val cats = ArrayList<Cats>()
        // The for loop will generate [size] amount of contact data and store in a list
        for (i in 1..size) {
            // i is concatenated to the end of the url to generate unique image each time
            val imageCat = "https://cataas.com/cat?cache=$i"
            val nameCat = Cats("cat-$i", imageCat, 0f)
            cats.add(nameCat)
        }


        // return the list of contacts
        return cats
    }


fun loadData(){
    val cats = ArrayList<Cats>()


    val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
    for(j in 1..numberOfCats){
        val imageCat = "https://cataas.com/cat?cache=$j"
        val rating = sharedPreferences.getString("cat-$j", "")
        val nameCat: Cats
        if(rating != "" && rating != null) {
            nameCat = Cats("cat-$j", imageCat, rating.toFloat())
        }
        else {
             nameCat = Cats("cat-$j", imageCat, 0f)
        }
        cats.add(nameCat)
    }
    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

    // specify an viewAdapter for the dataset (we use dummy data containing such as 20 contacts)
    val recyclerViewAdapter = CatAdapter(cats)
    recyclerView.adapter = recyclerViewAdapter

    // A helper function to create specified amount of dummy contact data
    recyclerView.layoutManager = LinearLayoutManager(this)
}

}










