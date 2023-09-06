package com.cristian.pruebayape.ui.views

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
import com.cristian.pruebayape.ui.adapters.IngredientsAdapter

class DetailRecopiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipiesBinding
    private val adapter: IngredientsAdapter = IngredientsAdapter()
    private val GOOGLEPACKAGE = "com.google.android.apps.maps"
    private val GEO = "geo:"
    private val COMMA = ","
    private val QUERY = "?q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecycler()
        getData()
    }

    private fun getData() {
        intent.extras?.let {
            val listIngredients = intent.getParcelableArrayListExtra<IngredientsUI>(RECIPEDATA)
            with(binding) {
                imgRecipe.load(it.getString(RECIPESTRMEALTHUMB))
                idCountry.text = "${getString(R.string.country)}: ${it.getString(RECIPENAMEAREA)}"
                adapter.submitList(listIngredients)
                recyclerIngredients.adapter = adapter
                txtDetailsInstructions.text = it.getString(RECIPESTRINSTRUCTIONS)
            }
            val lat = it.getDouble(RECIPELAT)
            val lng = it.getDouble(RECIPELNG)
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
            val geoUri = "$GEO$lat$COMMA$lng$QUERY$lat$COMMA$lng"
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            mapIntent.setPackage(GOOGLEPACKAGE)
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, getString(R.string.app_no_instalada), Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val RECIPENAMEMEAL = "recipeNameMeal"
        const val RECIPESTRMEALTHUMB = "recipeStrMealThumb"
        const val RECIPENAMEAREA = "recipeNameArea"
        const val RECIPESTRINSTRUCTIONS = "recipeStrInstructions"
        const val RECIPEDATA = "recipeData"
        const val RECIPELAT = "recipeLat"
        const val RECIPELNG = "recipeLng"
    }

}