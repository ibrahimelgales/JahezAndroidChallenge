package net.jahez.jahezchallenge.data.repository

import net.jahez.jahezchallenge.data.database.RestaurantsDao
import net.jahez.jahezchallenge.data.database.mapper.mapToDomain
import net.jahez.jahezchallenge.data.database.mapper.mapToEntity
import net.jahez.jahezchallenge.data.network.model.mapToDomain
import net.jahez.jahezchallenge.data.network.service.ApiService
import net.jahez.jahezchallenge.domain.model.RestaurantFilterParams
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

    override suspend fun getAllRestaurantsWithFilterParams(restaurantFilterParams: RestaurantFilterParams) =
        let {
            val restaurantFilterParamsAsEntity = restaurantFilterParams.mapToEntity()
            restaurantsDao.getAllRestaurantsWithFilter(
                highestRating = restaurantFilterParamsAsEntity.highestRating,
                nearbyRestaurants = restaurantFilterParamsAsEntity.nearbyRestaurants,
                shouldHasOffer = restaurantFilterParamsAsEntity.shouldHasOffer,
            ).mapToDomain()
        }
}
