package net.jahez.jahezchallenge.data

import net.jahez.jahezchallenge.data.network.model.mapToDomain
import net.jahez.jahezchallenge.data.network.service.ApiService
import net.jahez.jahezchallenge.domain.model.RestaurantItem
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import javax.inject.Inject


internal class ImpRepositoryRestaurants @Inject constructor(private val apiService: ApiService) :
    IRepositoryRestaurants {

    override suspend fun getAllRestaurants(): List<RestaurantItem> =
        apiService.getAllRestaurants().mapToDomain()
}
