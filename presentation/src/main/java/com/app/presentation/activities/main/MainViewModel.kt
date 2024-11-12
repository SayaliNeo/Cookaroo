package com.app.presentation.activities.main

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.app.presentation.R
import com.app.presentation.base.BaseViewModel
import com.app.presentation.model.DishAnalysisDetails
import com.domain.common.ResponseState
import com.domain.model.Dish
import com.domain.model.DishCollection
import com.domain.model.Ingredient
import com.domain.usecase.FetchDishCollectionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val fetchCollectionUseCase: FetchDishCollectionUseCase
) : BaseViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery.asStateFlow()

    private val _dishCollection: MutableStateFlow<DishCollection?> = MutableStateFlow(null)

    private val _carouselData: MutableStateFlow<List<Dish>> = MutableStateFlow(emptyList())
    val carouselData get() = _carouselData.asStateFlow()

    private val _ingredientList: MutableStateFlow<List<Ingredient>> = MutableStateFlow(emptyList())
    val ingredientList get() = _ingredientList.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    @StringRes
    private val _isError: MutableStateFlow<Int?> = MutableStateFlow(null)
    val isError get() = _isError.asStateFlow()

    init {
        fetchDishCollection()
    }

    private var currentCarouselIndex = -1


    fun onCarouselChange(selectedIndex: Int) {
        currentCarouselIndex = selectedIndex
        _searchQuery.value = ""
        getIngredientsList()
    }


    fun fetchDishCollection() {
        _isLoading.value = true
        viewModelScope.launch {
            fetchCollectionUseCase().catch { cause ->
                _isLoading.value = false
                _isError.value = R.string.generic_error_message
            }.onEach { responseState ->
                when (responseState) {
                    is ResponseState.SuccessState -> {
                        _isError.value = null
                        _dishCollection.value = responseState.data
                        setCarouselData()
                        _isLoading.value = false
                    }

                    is ResponseState.ErrorState -> {
                        _dishCollection.value = null
                        _isError.value = responseState.message
                        _isLoading.value = false
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun setCarouselData() = viewModelScope.launch {
        val carouselData = _dishCollection.value?.dishes?: emptyList()
        _carouselData.emit(carouselData)
    }


    fun getIngredientsList() = viewModelScope.launch {
        if (currentCarouselIndex < 0 || _carouselData.value.isEmpty()) {
            return@launch
        }
        val ingredientList = _dishCollection.value?.dishes?.get(currentCarouselIndex)?.ingredientList
            ?: emptyList()
        _ingredientList.emit(ingredientList)
    }

    fun filterIngredientList(searchQuery: String) {
        viewModelScope.launch {
             if (searchQuery.isNotEmpty()) {
                 val filteredData = _dishCollection.value?.dishes?.get(currentCarouselIndex)?.ingredientList?.filter { ingredient ->
                    ingredient.name.contains(searchQuery, ignoreCase = true)
                } ?: emptyList()
                _ingredientList.emit(filteredData)
            } else {
                getIngredientsList()
            }
        }
    }

    fun getBottomSheetAnalysis(): DishAnalysisDetails {
        return DishAnalysisDetails(
            ingredientCount = _dishCollection.value?.dishes?.get(currentCarouselIndex)?.ingredientList?.count()
                ?: 0,
            topCharacters = getTopFindings()
        )
    }

    private fun getTopFindings(
        numberOfCharacters: Int = 3,
    ):List<Pair<Char, Int>> {
        val characters =
            _dishCollection.value?.dishes?.get(currentCarouselIndex)?.ingredientList?.flatMap { ingredient ->
                ingredient.name.toCharArray().toList()
            }?: emptyList()

        val sortedList = characters
            .filterNot { it.isWhitespace() }
            .groupingBy { it }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(numberOfCharacters)
            .map { it.toPair() }

        return sortedList
    }


}