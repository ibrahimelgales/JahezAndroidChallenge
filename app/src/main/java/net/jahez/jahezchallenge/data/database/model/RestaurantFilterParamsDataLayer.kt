package net.jahez.jahezchallenge.data.database.model

internal data class RestaurantFilterParamsDataLayer(
    val shouldHasOffer: Boolean ?= null,
    val nearbyRestaurants: Boolean ?= null,
    val highestRating: Boolean ?= null
)