package net.jahez.jahezchallenge.domain.usecase

import net.jahez.jahezchallenge.core.utils.AppException
import net.jahez.jahezchallenge.core.utils.Resource
import net.jahez.jahezchallenge.domain.enum.AppLanguageDomain
import net.jahez.jahezchallenge.domain.model.RestaurantFilterParams
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import javax.inject.Inject

internal class UseCaseGetAllRestaurants @Inject constructor(private val iRepositoryRestaurants: IRepositoryRestaurants) {

    suspend fun getAllRestaurants(restaurantFilterParams: RestaurantFilterParams? = null) = try {
        val hasFilterParams = restaurantFilterParams?.isAllFieldsEqualNull()?.not() == true
        Resource.Success(
            if (restaurantFilterParams != null && hasFilterParams) iRepositoryRestaurants.getAllRestaurantsWithFilterParams(
                restaurantFilterParams
            ) else iRepositoryRestaurants.getAllRestaurants()
        )
    } catch (e: Exception) {
        AppLanguageDomain.Arabic
        Resource.Error(AppException(e))
    }
}