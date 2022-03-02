package net.jahez.jahezchallenge.domain.usecase

import net.jahez.jahezchallenge.core.AppException
import net.jahez.jahezchallenge.core.Resource
import net.jahez.jahezchallenge.domain.model.RestaurantItem
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import javax.inject.Inject

internal class UseCaseGetAllRestaurants @Inject constructor(private val iRepositoryRestaurants: IRepositoryRestaurants) {

    suspend fun getAllRestaurants() = try {
        Resource.Success(iRepositoryRestaurants.getAllRestaurants())
    } catch (e: Exception) {
        Resource.Error(AppException(e))
    }
}