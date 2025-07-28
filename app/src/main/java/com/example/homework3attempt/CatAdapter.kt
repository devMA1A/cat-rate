package com.example.homework3attempt

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CatAdapter (private val cats: ArrayList<Cats>): RecyclerView.Adapter<CatAdapter.MyViewHolder>() {

        // you provide access to all the views for a data item in a view holder.
       inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            val imageCat = itemView.findViewById<ImageView>(R.id.imageView)
            val nameCat = itemView.findViewById<TextView>(R.id.textView_name)
            var ratingCat = itemView.findViewById<RatingBar>(R.id.ratingBar)
            val rateButton = itemView.findViewById<Button>(R.id.button_rate)
            init {
                // Attach a click listener to the entire row view
                rateButton.setOnClickListener() {
                    val selectedItem = adapterPosition

                    val myIntent = Intent(itemView.context, AnimalRatingActivity::class.java)
                    myIntent.putExtra("Name",
                        cats[adapterPosition].name)
                    myIntent.putExtra("Image", cats[adapterPosition].imageCat)
                    myIntent.putExtra("Rate", cats[adapterPosition].rating)
                    itemView.context.startActivity(myIntent)
                }

                itemView.setOnClickListener {
                    val selectedItem = adapterPosition
                //    Toast.makeText(itemView.context, "Rating is  $selectedItem", Toast.LENGTH_SHORT).show()

                }
            }
        }


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatAdapter.MyViewHolder {


            // create a new view
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
            return MyViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: CatAdapter.MyViewHolder, position: Int) {

            val context = holder.itemView.context
            val currentItem = cats[position]
            holder.ratingCat.rating =  currentItem.rating
            holder.nameCat.text = currentItem.name

            Glide.with(context)
                .load(currentItem.imageCat)
                .into(holder.imageCat)

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount(): Int {
            return cats.size
        }


    }
