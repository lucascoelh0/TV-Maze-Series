package com.luminay.tvmazeseries.features.home.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.constants.EMPTY
import com.example.core.models.Resource
import com.example.core.models.Status
import com.example.domain.models.FavoriteModel
import com.example.domain.models.SearchShowModel
import com.example.domain.models.ShowModel
import com.example.domain.usecases.IFavoriteUseCase
import com.example.domain.usecases.IShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val showsUseCase: IShowsUseCase,
    private val favoriteUseCase: IFavoriteUseCase,
) : ViewModel() {

    private val _isFirstLoadDone = MutableStateFlow(true)
    val isFirstLoadDone = _isFirstLoadDone.asStateFlow()

    private val _allShows = MutableStateFlow<Resource<List<ShowModel>>>(Resource.loading(null))

    private val _paginationStatus = MutableStateFlow<Resource<List<ShowModel>>>(Resource.loading(null))
    val paginationStatus: Flow<Resource<List<ShowModel>>> = _paginationStatus.asStateFlow()

    private val _searchQuery = MutableStateFlow(EMPTY)
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _showFavorites = MutableStateFlow(false)
    val showFavorites: StateFlow<Boolean> = _showFavorites.asStateFlow()

    private val _searchResults = MutableStateFlow<Resource<List<SearchShowModel>>>(Resource.loading(null))

    private val _favorites = MutableStateFlow<List<FavoriteModel>>(emptyList())

    private var currentPage = 1
    private var isPageLoading = false

    init {
        getFavoriteShows()
        fetchData()
    }

    val unifiedShows: Flow<Resource<List<ShowModel>>> = combine(
        _searchQuery,
        _favorites,
        _allShows,
        _searchResults,
        _showFavorites
    ) { query, favorites, allShowsResource, searchResultsResource, showFavorites ->
        val favoriteIds = favorites.map { it.id }.toSet()

        when {
            showFavorites -> processFavorites(allShowsResource, favoriteIds, query)
            query.isNotBlank() && searchResultsResource.status == Status.SUCCESS -> processSearchResults(
                searchResultsResource,
                favoriteIds
            )

            allShowsResource.status == Status.SUCCESS -> processAllShows(allShowsResource, favoriteIds)
            else -> allShowsResource
        }
    }.distinctUntilChanged()

    private fun processFavorites(
        allShowsResource: Resource<List<ShowModel>>,
        favoriteIds: Set<Int>,
        query: String
    ): Resource<List<ShowModel>> {
        val favoriteShows = allShowsResource.data
            ?.asSequence()
            ?.filter { it.id in favoriteIds }
            ?.map { it.copy(isFavorite = true) }
            ?.filter { query.isBlank() || it.name.contains(query, ignoreCase = true) }
            ?.sortedBy { it.name }
            ?.toList() ?: emptyList()
        return Resource.success(favoriteShows)
    }

    private fun processSearchResults(
        searchResultsResource: Resource<List<SearchShowModel>>,
        favoriteIds: Set<Int>
    ): Resource<List<ShowModel>> {
        val searchResults = searchResultsResource.data
            ?.map { it.show.copy(isFavorite = it.show.id in favoriteIds) }
            ?: emptyList()
        return Resource.success(searchResults)
    }

    private fun processAllShows(
        allShowsResource: Resource<List<ShowModel>>,
        favoriteIds: Set<Int>
    ): Resource<List<ShowModel>> {
        val allShows = allShowsResource.data
            ?.map { it.copy(isFavorite = it.id in favoriteIds) }
            ?: emptyList()
        return Resource.success(allShows)
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

    fun shouldShowFavorites(showFavorites: Boolean) {
        _showFavorites.value = showFavorites
    }

    fun addFavorite(showModel: ShowModel) {
        viewModelScope.launch {
            favoriteUseCase.insertFavorite(showModel.id)
        }
    }

    fun removeFavorite(showModel: ShowModel) {
        viewModelScope.launch {
            favoriteUseCase.deleteFavorite(showModel.id)
        }
    }

    private fun getFavoriteShows() {
        viewModelScope.launch {
            flow {
                emitAll(favoriteUseCase.getFavorites())
            }.collect { result ->
                _favorites.value = result
            }
        }
    }
}
