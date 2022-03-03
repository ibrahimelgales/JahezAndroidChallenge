package net.jahez.jahezchallenge.data.repository

import net.jahez.jahezchallenge.data.database.RestaurantsDao
import net.jahez.jahezchallenge.data.database.model.mapToDomain
import net.jahez.jahezchallenge.data.database.model.mapToEntity
import net.jahez.jahezchallenge.data.network.model.mapToDomain
import net.jahez.jahezchallenge.data.network.service.ApiService
import net.jahez.jahezchallenge.domain.model.RestaurantItem
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import javax.inject.Inject


internal class ImpRepositoryRestaurants @Inject constructor(
    private val apiService: ApiService,
    private val restaurantsDao: RestaurantsDao
) : IRepositoryRestaurants {

    override suspend fun getAllRestaurants(): List<RestaurantItem> = try {
        apiService.getAllRestaurants().mapToDomain().also {
            restaurantsDao.insertRestaurants(it.mapToEntity())
        }
    } catch (e: Exception) {
        restaurantsDao.getAllRestaurants().mapToDomain()
    }
}
