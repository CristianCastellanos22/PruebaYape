package com.cristian.pruebayape.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.cristian.pruebayape.R
import com.cristian.pruebayape.databinding.ActivityDetailRecipiesBinding
import com.cristian.pruebayape.domain.models.IngredientsUI
import com.cristian.pruebayape.ui.views.IngredientsAdapter

class DetailRecopiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipiesBinding
    private val adapter: IngredientsAdapter = IngredientsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecycler()
        getData()
    }

    private fun getData() {
        intent.extras?.let {
            val listIngredients = intent.getParcelableArrayListExtra<IngredientsUI>("recipeData")
            println("Los ingredientes son: $listIngredients")

            with(binding) {
                imgRecipe.load(it.getString("recipeStrMealThumb"))
                idCountry.text =
                    "${getString(R.string.country)}: ${it.getString("recipeNameArea")}"
                adapter.submitList(listIngredients)
                recyclerIngredients.adapter = adapter
                txtDetailsInstructions.text = it.getString("recipeStrInstructions")
            }
            val lat = it.getDouble("recipeLat")
            println("Latitud: $lat")
            val lng = it.getDouble("recipeLng")
            println("Longitud: $lng")
            viewMap(lat, lng)
        }
    }

    private fun configRecycler() {
        binding.recyclerIngredients.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerIngredients.setHasFixedSize(true)
    }

    private fun viewMap(lat: Double?, lng: Double?) {
        binding.btnMaps.setOnClickListener {

            // Crea una URI con las coordenadas
            val gmmIntentUri = Uri.parse("geo:$lat,$lng")

            // Crea un Intent para abrir la aplicación de Google Maps
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

            // Especifica que deseas abrir Google Maps
            mapIntent.setPackage("com.google.android.apps.maps")

            // Verifica si la aplicación de Google Maps está instalada
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, "App no instalada", Toast.LENGTH_SHORT).show()
            }
        }
    }

}