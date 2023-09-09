package com.cristian.pruebayape.ui.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristian.pruebayape.R
import com.cristian.pruebayape.databinding.ActivityMainBinding
import com.cristian.pruebayape.domain.models.RecipesUI
import com.cristian.pruebayape.ui.adapters.RecipesAdapter
import com.cristian.pruebayape.ui.viewmodels.RecipesViewModel
import com.cristian.pruebayape.ui.viewmodels.Status
import com.cristian.pruebayape.ui.views.DetailRecipesActivity.Companion.RECIPE_NAME_MEAL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipesAdapter
    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        configRecycler()
        observer()
        searchView()
        viewModel.getRecipesCollection()
    }

    private fun configRecycler() {
        with(binding) {
            contentRecycler.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            contentRecycler.setHasFixedSize(true)

            adapter = RecipesAdapter { recipe ->
                startActivity(Intent(this@MainActivity, DetailRecipesActivity::class.java).apply {
                    putExtra(RECIPE_NAME_MEAL, recipe)
                })
            }
            contentRecycler.adapter = adapter
        }
    }

    private fun observer() {
        with(binding) {
            viewModel.status.observe(this@MainActivity) {
                when (it) {
                    is Status.Error -> {
                        loadingWheel.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.messageId, Toast.LENGTH_SHORT).show()
                    }
                    is Status.Loading -> loadingWheel.visibility = View.VISIBLE
                    is Status.Success -> {
                        loadingWheel.visibility = View.GONE
                        (it.data as? List<RecipesUI>)?.let { list ->
                            if (list.isEmpty()) {
                                Toast.makeText(
                                    this@MainActivity,
                                    getString(R.string.no_data),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                adapter.submitList(list)
                            }
                        }
                    }
                    is Status.UpdateData -> {
                        adapter.submitList(it.data as? List<RecipesUI>)
                    }
                }
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
