package com.cristian.pruebayape.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.cristian.pruebayape.R
import com.cristian.pruebayape.databinding.ActivityDetailRecipiesBinding
import com.cristian.pruebayape.domain.models.OriginUI
import com.cristian.pruebayape.domain.models.RecipesUI
import com.cristian.pruebayape.ui.adapters.IngredientsAdapter
import com.cristian.pruebayape.ui.views.MapScreenActivity.Companion.ORIGIN_UI

class DetailRecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipiesBinding
    private val adapter: IngredientsAdapter = IngredientsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecycler()
        getData()
    }

    private fun configRecycler() {
        with(binding) {
            binding.recyclerIngredients.layoutManager =
                LinearLayoutManager(this@DetailRecipesActivity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerIngredients.setHasFixedSize(true)
            recyclerIngredients.adapter = adapter
        }
    }

    private fun getData() {
        intent.extras?.let {
            it.getParcelable<RecipesUI>(RECIPE_NAME_MEAL)?.apply {
                with(binding) {
                    imgRecipe.load(thumbnail) {
                        placeholder(R.drawable.baseline_image)
                            .error(R.drawable.baseline_error_outline)
                    }
                    idCountry.text =
                        "${getString(R.string.country)}: $area"
                    adapter.submitList(ingredients)
                    txtDetailsInstructions.text = instructions
                }
                viewMap(originUI)
            }
        }
    }

    private fun viewMap(originUI: OriginUI) {
        binding.btnMaps.setOnClickListener {
            startActivity(Intent(this, MapScreenActivity::class.java).apply {
                putExtra(ORIGIN_UI, originUI)
            })
        }
    }

    companion object {
        const val RECIPE_NAME_MEAL = "recipeList"
    }

}
