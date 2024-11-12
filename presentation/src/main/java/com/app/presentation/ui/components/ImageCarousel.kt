package com.app.presentation.ui.components

import androidx.annotation.LayoutRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.presentation.R

@Composable
fun ImageCarousel(@LayoutRes images: List<Int>, state: PagerState) {
    Column(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.large_margin))) {
        HorizontalPager(state = state) { page ->
            CarouselItem(images[page])
        }

        LazyRow(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_10))
                .align(Alignment.CenterHorizontally),
        ) {
            itemsIndexed(images) { index, _ ->
                PageIndicator(isActive = index == state.currentPage)
            }
        }
    }
}


@Preview
@Composable
fun PreviewImageCarousel() {
    ImageCarousel(
        images = listOf(R.drawable.bg_list_item),
        state = rememberPagerState(
            initialPage = 0,
            pageCount = { 2 }
        )
    )
}