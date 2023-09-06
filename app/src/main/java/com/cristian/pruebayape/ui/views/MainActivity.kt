package com.cristian.pruebayape.ui.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristian.pruebayape.data.response.ApiResponseStatus
import com.cristian.pruebayape.databinding.ActivityMainBinding
import com.cristian.pruebayape.ui.views.DetailRecopiesActivity.Companion.RECIPEDATA
import com.cristian.pruebayape.ui.views.DetailRecopiesActivity.Companion.RECIPELAT
import com.cristian.pruebayape.ui.views.DetailRecopiesActivity.Companion.RECIPELNG
import com.cristian.pruebayape.ui.views.DetailRecopiesActivity.Companion.RECIPENAMEAREA
import com.cristian.pruebayape.ui.views.DetailRecopiesActivity.Companion.RECIPENAMEMEAL
import com.cristian.pruebayape.ui.views.DetailRecopiesActivity.Companion.RECIPESTRINSTRUCTIONS
import com.cristian.pruebayape.ui.views.DetailRecopiesActivity.Companion.RECIPESTRMEALTHUMB
import com.cristian.pruebayape.ui.viewmodels.RecipesViewModel
import com.cristian.pruebayape.ui.adapters.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: RecipesAdapter = RecipesAdapter { recipe ->
        startActivity(Intent(this@MainActivity, DetailRecopiesActivity::class.java).apply {
            putExtra(RECIPENAMEMEAL, recipe.nameMeal)
            putExtra(RECIPESTRMEALTHUMB, recipe.strMealThumb)
            putExtra(RECIPENAMEAREA, recipe.nameArea)
            putExtra(RECIPESTRINSTRUCTIONS, recipe.strInstructions)
            putExtra(RECIPEDATA, recipe.data)
            putExtra(RECIPELAT, recipe.lat)
            putExtra(RECIPELNG, recipe.lng)
        })
    }
    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecycler()
        observer()
        searchView()
    }

    private fun configRecycler() {
        with(binding) {
            contentRecycler.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            contentRecycler.setHasFixedSize(true)
        }
    }

    private fun observer() {
        with(binding) {
            viewModel.status.observe(this@MainActivity) {
                with(binding) {
                    when (it) {
                        is ApiResponseStatus.Error -> {
                            loadingWheel.visibility = View.GONE
                            Toast.makeText(this@MainActivity, it.messageId, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                        is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
                    }
                }
            }
            viewModel.recipesList.observe(this@MainActivity) {
                when {
                    !it.isNullOrEmpty() -> {
                        adapter.submitList(it)
                        contentRecycler.adapter = adapter
                    }
                }
            }
            viewModel.filteredRecipesList.observe(this@MainActivity) { filteredRecipes ->
                adapter.submitList(filteredRecipes)
            }
        }
    }

    private fun searchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                filterRecipes(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterRecipes(newText)
                return true
            }
        })
    }

    private fun filterRecipes(query: String?) {
        viewModel.filterRecipes(query)
    }

}