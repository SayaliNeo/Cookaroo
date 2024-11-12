package com.app.presentation.activities.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.app.presentation.R
import com.app.presentation.adapter.DishCarouselAdapter
import com.app.presentation.adapter.IngredientsListAdapter
import com.app.presentation.base.BaseActivity
import com.app.presentation.databinding.ActivityMainBinding
import com.app.presentation.dialog.DishAnalysisBottomSheet
import com.app.presentation.utils.Extensions.visibilityToggle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var dishCarouselAdapter: DishCarouselAdapter

    private lateinit var ingredientAdapter: IngredientsListAdapter

    override fun provideLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
    }

    override fun initView() {
        initDataAdapter()
        dataBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                clearSearchQuery()
                mainViewModel.onCarouselChange(position)
                mainViewModel.getIngredientsList()
            }
        })

        dataBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                executeSearch(newText)
                return true
            }
        })

        dataBinding.fab.setOnClickListener {
            val dishAnalysisDetails = mainViewModel.getBottomSheetAnalysis()
            val modal = DishAnalysisBottomSheet(dishAnalysisDetails)
            supportFragmentManager.let { modal.show(it, DishAnalysisBottomSheet.TAG) }
        }

    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.carouselData.collectLatest { carouselData ->
                        dataBinding.search.clearFocus()
                        dishCarouselAdapter.submitList(carouselData)
                    }
                }

                launch {
                    mainViewModel.ingredientList.collectLatest { ingredientData ->
                        ingredientAdapter.submitList(ingredientData)
                    }
                }

                launch {
                    mainViewModel.searchQuery.collectLatest {
                        dataBinding.search.setQuery(it, false)
                    }
                }

                launch {
                    mainViewModel.isError.collectLatest { error ->
                        error?.let {
                            Toast.makeText(
                                this@MainActivity,
                                it,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                launch {
                    mainViewModel.isLoading.collectLatest { showProgressBar->
                        dataBinding.progressBar.visibilityToggle(showProgressBar)
                    }
                }
            }
        }
    }

    private fun initDataAdapter() {
        mainViewModel.fetchDishCollection()
        dishCarouselAdapter = DishCarouselAdapter()
        ingredientAdapter = IngredientsListAdapter()
        dataBinding.run {
            viewPager.adapter = dishCarouselAdapter
            itemList.adapter = ingredientAdapter
            itemList.layoutManager = LinearLayoutManager(this@MainActivity)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            }.attach()
        }
    }

    private fun executeSearch(query: String?) {
        query?.let {
            mainViewModel.filterIngredientList(
                it
            )
        }
    }

    private fun clearSearchQuery() {
        dataBinding.search.setQuery("", false)
        dataBinding.search.clearFocus()
    }
}