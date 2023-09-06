package com.cristian.pruebayape

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristian.pruebayape.data.response.ApiResponseStatus
import com.cristian.pruebayape.databinding.ActivityMainBinding
import com.cristian.pruebayape.ui.DetailRecopiesActivity
import com.cristian.pruebayape.ui.viewmodels.RecipesViewModel
import com.cristian.pruebayape.ui.views.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: RecipesAdapter = RecipesAdapter { recipe ->
        startActivity(Intent(this@MainActivity, DetailRecopiesActivity::class.java).apply {
            putExtra("recipeStrMealThumb", recipe.strMealThumb)
            putExtra("recipeNameArea", recipe.nameArea)
            putExtra("recipeStrInstructions", recipe.strInstructions)
            putExtra("recipeData", recipe.data)
            putExtra("recipeLat", recipe.lat)
            putExtra("recipeLng", recipe.lng)
        })
    }
    private val viewModel: RecipesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecycler()
        wheel()
        observer()
    }

    private fun configRecycler() {
        with(binding) {
            contentRecycler.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            contentRecycler.setHasFixedSize(true)
        }
    }

    private fun wheel() {
        viewModel.status.observe(this) {
            with(binding) {
                when (it) {
                    is ApiResponseStatus.Error -> {
                        loadingWheel.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.messageId, Toast.LENGTH_SHORT).show()
                    }
                    is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                    is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
                }
            }
        }
    }

    private fun observer() {
        with(binding) {
            viewModel.recipesList.observe(this@MainActivity) {
                when {
                    !it.isNullOrEmpty() -> {
                        adapter.submitList(it)
                        contentRecycler.adapter = adapter
                    }
                }
            }
        }
    }
}