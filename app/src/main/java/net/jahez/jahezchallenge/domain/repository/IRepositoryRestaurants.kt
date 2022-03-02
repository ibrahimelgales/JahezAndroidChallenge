package net.jahez.jahezchallenge.domain.repository

import net.jahez.jahezchallenge.domain.model.RestaurantItem


internal interface IRepositoryRestaurants {
    suspend fun getAllRestaurants(): List<RestaurantItem>
}
