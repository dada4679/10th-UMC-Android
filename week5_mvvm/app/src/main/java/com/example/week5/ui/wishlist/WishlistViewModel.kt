package com.example.week5.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.data.local.ProductEntity
import com.example.week5.domain.repository.LocalProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: LocalProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // Flow를 구독하기 때문에 onResume에서 다시 읽지 않아도 자동 갱신됩니다.
            repository.getWishlistStream().collect { items ->
                _uiState.update { it.copy(items = items) }
            }
        }
    }
}

data class WishlistUiState(
    val items: List<ProductEntity> = emptyList()
)
