package com.luminay.tvmazeseries.features.home.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.constants.EMPTY
import com.example.core.models.Resource
import com.example.core.models.Status
import com.example.domain.models.SearchShowModel
import com.example.domain.models.ShowModel
import com.example.domain.usecases.IShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val showsUseCase: IShowsUseCase,
) : ViewModel() {

    private val _isFirstLoadDone = MutableStateFlow(true)
    val isFirstLoadDone = _isFirstLoadDone.asStateFlow()

    private val _allShows = MutableStateFlow<Resource<List<ShowModel>>>(Resource.loading(null))

    private val _paginationStatus = MutableStateFlow<Resource<List<ShowModel>>>(Resource.loading(null))
    val paginationStatus: Flow<Resource<List<ShowModel>>> = _paginationStatus.asStateFlow()

    private val _searchQuery = MutableStateFlow(EMPTY)
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<Resource<List<SearchShowModel>>>(Resource.loading(null))

    private var currentPage = 1
    private var isPageLoading = false

    @OptIn(ExperimentalCoroutinesApi::class)
    val unifiedShows: Flow<Resource<List<ShowModel>>> = _searchQuery.flatMapLatest { query ->
        if (query.isBlank()) {
            _allShows
        } else {
            _searchResults.map { searchResultsResource ->
                when (searchResultsResource.status) {
                    Status.SUCCESS -> {
                        Resource.success(searchResultsResource.data?.map { it.show } ?: emptyList())
                    }

                    Status.LOADING -> {
                        Resource.loading(null)
                    }

                    else -> {
                        Resource.error(
                            searchResultsResource.message,
                            null,
                            null,
                        )
                    }
                }
            }
        }
    }

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

    fun searchShows() {
        _searchQuery.value.let { currentQuery ->
            if (currentQuery.isBlank()) {
                _searchResults.value = Resource.success(emptyList())
                return
            }

            viewModelScope.launch {
                flow {
                    emit(Resource.loading(null))
                    emitAll(showsUseCase.searchShows(currentQuery))
                }.collect { result ->
                    _searchResults.value = result
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
