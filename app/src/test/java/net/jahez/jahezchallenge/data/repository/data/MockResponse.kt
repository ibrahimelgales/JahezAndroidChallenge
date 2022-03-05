package net.jahez.jahezchallenge.data.repository.data

import net.jahez.jahezchallenge.data.database.model.RestaurantsEntity
import net.jahez.jahezchallenge.data.network.model.RestaurantItemResponse


object MockResponse {

    private fun restaurantItemResponse(id: Int) = RestaurantItemResponse(
        id,
        "description",
        3.0,
        true,
        "",
        "",
        "",
        "",
        ""
    )

    private fun restaurantItemDB(id: Int) = RestaurantsEntity(
        id,
        "description",
        "3.0",
        true,
        "",
        "",
        "",
        "",
        ""
    )

    internal fun getListOfRestaurants() = listOf(
        restaurantItemResponse(1),
        restaurantItemResponse(2),
        restaurantItemResponse(3),
    )

    internal fun getListOfCachedRestaurants() = listOf(
        restaurantItemDB(1),
        restaurantItemDB(2)
    )


}
