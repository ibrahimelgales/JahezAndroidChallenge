package net.jahez.jahezchallenge.data.network.model

import net.jahez.jahezchallenge.domain.model.RestaurantItem

internal fun RestaurantItemResponse.mapToDomain() = RestaurantItem(
    description, "$distance", hasOffer, hours, id, image, name, offer, rating
)

internal fun List<RestaurantItemResponse>.mapToDomain() = map { it.mapToDomain() }