package net.jahez.jahezchallenge.domain.usecase

import net.jahez.jahezchallenge.core.utils.AppException
import net.jahez.jahezchallenge.core.utils.Resource
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import javax.inject.Inject

internal class UseCaseGetAllRestaurants @Inject constructor(private val iRepositoryRestaurants: IRepositoryRestaurants) {

    suspend fun getAllRestaurants() = try {
        Resource.Success(iRepositoryRestaurants.getAllRestaurants())
    } catch (e: Exception) {
        Resource.Error(AppException(e))
    }
}