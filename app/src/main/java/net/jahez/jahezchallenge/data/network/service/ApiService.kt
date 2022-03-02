package net.jahez.jahezchallenge.data.network.service

import net.jahez.jahezchallenge.data.network.model.RestaurantItemResponse
import retrofit2.http.GET

interface ApiService {

    @GET("restaurants.json")
    suspend fun getAllRestaurants(): List<RestaurantItemResponse>

}
