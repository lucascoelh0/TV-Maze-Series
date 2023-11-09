package com.luminay.tvmazeseries.features.details.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.models.Resource
import com.example.domain.models.EpisodeModel
import com.example.domain.usecases.IShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val showsUseCase: IShowsUseCase,
) : ViewModel() {

    private val _episodes = MutableStateFlow<Resource<List<EpisodeModel>>>(Resource.loading(null))
    val episodes = _episodes.asStateFlow()

    private val _selectedSeason = MutableStateFlow(1)
    val selectedSeason = _selectedSeason.asStateFlow()

    private val _showDetailsBottomSheet = MutableStateFlow(false)
    val showDetailsBottomSheet = _showDetailsBottomSheet.asStateFlow()

    lateinit var clickedEpisode: EpisodeModel

    fun fetchData(showId: Int) = viewModelScope.launch {
        flow {
            emit(Resource.loading(null))
            emitAll(showsUseCase.getShowEpisodes(showId))
        }.collect {
            _episodes.value = it
        }
    }

    fun groupEpisodesBySeason(episodes: List<EpisodeModel>): Map<Int, List<EpisodeModel>> {
        return episodes.groupBy { it.season }
    }

    fun setSelectedSeason(season: Int) {
        _selectedSeason.value = season
    }

    fun onShowDetailsRequested() {
        _showDetailsBottomSheet.value = true
    }

    fun onDismissRequest() {
        _showDetailsBottomSheet.value = false
    }
}
