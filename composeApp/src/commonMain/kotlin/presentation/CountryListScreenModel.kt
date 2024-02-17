package presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import data.CountryResponse
import data.Repository
import data.StateAction
import data.onError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class CountryListScreenState {
    data object Loading : CountryListScreenState()
    data class Error(val onError: onError) : CountryListScreenState()
    data class Success(val countryList: List<CountryResponse>?) : CountryListScreenState()
}

/**
 * Additional data states to hold all possible data
 * */

private data class CountryListState(
    val isLoading: Boolean = false,
    val onError: onError? = null,
    val countryList: List<CountryResponse>? = null
) {
    fun toUiState(): CountryListScreenState {
        return when {
            isLoading -> CountryListScreenState.Loading
            onError != null -> CountryListScreenState.Error(onError)
            else -> CountryListScreenState.Success(countryList)
        }
    }
}

class CountryListScreenModel(private val repository: Repository) :
    ScreenModel {

    private val _countryListState = MutableStateFlow(CountryListState())
    private val _countryListScreenState: MutableStateFlow<CountryListScreenState> =
        MutableStateFlow(CountryListScreenState.Loading)
    val countryListScreenState = _countryListScreenState.asStateFlow()

    fun fetchCountries() {
        coroutineScope.launch (Dispatchers.IO) {
            repository.fetchCountries().collect { response ->
                println("Result: $response")
                when (response.status) {
                    StateAction.Status.ERROR -> {
                        _countryListState.update { it.copy(isLoading = false, onError = response.error) }
                    }

                    StateAction.Status.LOADING -> {
                        _countryListState.update { it.copy(isLoading = true) }
                    }

                    else -> {
                        println( response.data?.toString() ?: "")
                        _countryListState.update {
                            it.copy(
                                isLoading = false,
                                onError = null,
                                countryList = response.data
                            )
                        }
                    }
                }
                _countryListScreenState.value =  _countryListState.value.toUiState()
            }
        }
    }
}