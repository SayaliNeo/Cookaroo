package com.app.presentation.ui.main

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.presentation.R
import com.app.presentation.theme.LocalCustomColorPalette
import com.app.presentation.ui.components.DishAnalysisBottomSheet
import com.app.presentation.ui.components.EmptyData
import com.app.presentation.ui.components.FloatingButton
import com.app.presentation.ui.components.ImageCarousel
import com.app.presentation.ui.components.IngredientItem
import com.app.presentation.ui.components.Loader
import com.app.presentation.ui.components.SearchBar
import kotlinx.coroutines.flow.map
import org.koin.compose.viewmodel.koinViewModel


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {

    val sheetState = rememberModalBottomSheetState()
    var isSheetVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val ingredientList by mainViewModel.ingredientList.collectAsState()
    val dishCarouselData by mainViewModel.carouselData.map { list ->
        list.map {
            it.dishImage
        }
    }.collectAsState(initial = emptyList())

    val searchQuery by mainViewModel.searchQuery.collectAsState()
    val showError by mainViewModel.isError.collectAsState()
    val isLoading by mainViewModel.isLoading.collectAsState()
    val focusManager = LocalFocusManager.current
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp.dp

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { dishCarouselData.count() }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            focusManager.clearFocus()
            mainViewModel.onCarouselChange(page)
        }
    }

    LaunchedEffect(showError) {
        showError?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingButton { isSheetVisible = true }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        if (dishCarouselData.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .background(LocalCustomColorPalette.current.screenBackground)
                    .padding(
                        start = dimensionResource(R.dimen.large_margin),
                        end = dimensionResource(R.dimen.large_margin),
                        top = padding
                            .calculateTopPadding()
                            .plus(dimensionResource(id = R.dimen.padding_20)),
                        bottom = padding.calculateBottomPadding()
                    )
            ) {
                item {
                    ImageCarousel(
                        images = dishCarouselData,
                        state = pagerState
                    )
                }

                stickyHeader {
                    Surface(
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.small_margin))
                    ) {
                        SearchBar(searchQuery) { query ->
                            mainViewModel.onSearchTriggered(query)
                        }
                    }
                }

                itemsIndexed(ingredientList) { _, ingredient ->
                    Column(Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_3))) {
                        IngredientItem(ingredient = ingredient)
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = screenHeight * 3 / 4)
                    )
                }
            }
        }
        if (isLoading) {
            Loader()
        }

        if (isSheetVisible) {
            DishAnalysisBottomSheet(
                details = mainViewModel.getBottomSheetAnalysis(),
                onDismiss = { isSheetVisible = false },
                sheetState = sheetState
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}