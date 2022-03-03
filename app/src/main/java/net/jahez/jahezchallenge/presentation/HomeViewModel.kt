package net.jahez.jahezchallenge.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.jahez.jahezchallenge.core.utils.*
import net.jahez.jahezchallenge.domain.model.RestaurantItem
import net.jahez.jahezchallenge.domain.usecase.UseCaseGetAllRestaurants
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val useCaseGetAllRestaurants: UseCaseGetAllRestaurants
) : BaseViewModel<HomeViewModel.ViewState, HomeViewModel.Action>(ViewState.Empty) {

    init {
        getAllRestaurants()
    }


    internal sealed class ViewState : BaseViewState {
        object Empty : ViewState()
        object Loading : ViewState()
        class Loaded(val data: List<RestaurantItem>) : ViewState()
        class Error(val appException: AppException?) : ViewState()
    }

    internal sealed interface Action : BaseAction {
        class AllRestaurantsLoaded(val listOfRestaurants: List<RestaurantItem>) : Action
        class AllRestaurantsFailedToLoaded(val appException: AppException?) : Action
        object AllRestaurantsLoading : Action
        object NoRestaurantsFound : Action
    }


    internal fun getAllRestaurants() {
        viewModelScope.launch {
            sendAction(Action.AllRestaurantsLoading)
            when (val response = useCaseGetAllRestaurants.getAllRestaurants()) {
                is Resource.Success -> {
                    val list = response.data
                    if (list != null && list.isNotEmpty())
                        sendAction(Action.AllRestaurantsLoaded(list))
                    else
                        sendAction(Action.NoRestaurantsFound)
                }

                is Resource.Error ->
                    sendAction(Action.AllRestaurantsFailedToLoaded(response.appException))
            }

        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.AllRestaurantsLoaded -> ViewState.Loaded(
            viewAction.listOfRestaurants
        )
        is Action.AllRestaurantsFailedToLoaded -> ViewState.Error(viewAction.appException)
        is Action.AllRestaurantsLoading -> ViewState.Loading
        is Action.NoRestaurantsFound -> ViewState.Empty
    }
}