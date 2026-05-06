package com.example.week5.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.R
import com.example.week5.data.local.ProductEntity
import com.example.week5.domain.repository.LocalProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * 앱 시작 시 시드 데이터를 한 번만 삽입하는 책임을 가진 ViewModel.
 * 기존 MainActivity.insertInitialProducts() 로직을 옮겨왔습니다.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LocalProductRepository
) : ViewModel() {

    init {
        seedInitialProductsIfNeeded()
    }

    private fun seedInitialProductsIfNeeded() {
        viewModelScope.launch {
            repository.seedIfEmpty(
                listOf(
                    ProductEntity(
                        imgRes = R.drawable.air_jordan,
                        title = "Air Jordan XXXVI",
                        price = "US$185",
                        description = "Best seller shoes",
                        isBestSeller = true,
                        category = "sale"
                    ),
                    ProductEntity(
                        imgRes = R.drawable.air_force,
                        title = "Nike Air Force 1'07",
                        price = "US$115",
                        description = "Classic Nike shoes",
                        isBestSeller = true,
                        isInBag = true,
                        category = null
                    ),
                    ProductEntity(
                        imgRes = R.drawable.socks,
                        title = "Nike Everyday Plus Cushioned",
                        price = "US$10",
                        description = "Comfortable socks",
                        isWishlisted = true,
                        category = "sale"
                    ),
                    ProductEntity(
                        imgRes = R.drawable.nikeelitecrew,
                        title = "Nike Elite Crew",
                        price = "US$16",
                        description = "Sports socks",
                        category = null
                    ),
                    ProductEntity(
                        imgRes = R.drawable.jordan1mid,
                        title = "Air Jordan 1 Mid",
                        price = "US$125",
                        description = "Jordan shoes",
                        isWishlisted = true,
                        category = null
                    )
                )
            )
        }
    }
}
