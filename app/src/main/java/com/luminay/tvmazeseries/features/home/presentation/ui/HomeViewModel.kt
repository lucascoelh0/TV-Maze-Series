package com.luminay.tvmazeseries.features.home.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.models.Resource
import com.example.core.models.Status
import com.example.domain.models.ShowModel
import com.example.domain.usecases.IShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val showsUseCase: IShowsUseCase,
) : ViewModel() {

    private val _isFirstLoadDone = MutableStateFlow(true)
    val isFirstLoadDone = _isFirstLoadDone.asStateFlow()

    private val _allShows = MutableStateFlow<Resource<List<ShowModel>>>(Resource.loading(null))
    val allShows: Flow<Resource<List<ShowModel>>> = _allShows.asStateFlow()

    private val _paginationStatus = MutableStateFlow<Resource<List<ShowModel>>>(Resource.loading(null))
    val paginationStatus: Flow<Resource<List<ShowModel>>> = _paginationStatus.asStateFlow()

    lateinit var pressedShow: ShowModel

    private var currentPage = 1
    private var isPageLoading = false

    init {
        fetchData()
    }

    fun fetchData() {
        if (isPageLoading) return
        isPageLoading = true
        currentPage = 1

        viewModelScope.launch {
            flow {
                emit(Resource.loading(null))
                emitAll(showsUseCase.getShows(1))
            }.collect { result ->
                if (result.status == Status.SUCCESS) {
                    currentPage++
                }
                _allShows.value = result
                isPageLoading = false
                _isFirstLoadDone.value = false
            }
        }
    }

    fun fetchNextPage() {
        if (isPageLoading) return
        isPageLoading = true

        viewModelScope.launch {
            flow {
                emit(Resource.loading(null))
                emitAll(showsUseCase.getShows(currentPage))
            }.collect { result ->
                if (result.status == Status.SUCCESS && result.data?.isNotEmpty() == true) {
                    val updatedList = _allShows.value.data.orEmpty() + result.data.orEmpty()
                    _allShows.value = Resource.success(updatedList)
                    currentPage++
                }
                _paginationStatus.value = result
                isPageLoading = false
            }
        }
    }
}
