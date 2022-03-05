package net.jahez.jahezchallenge.data.database.mapper

import net.jahez.jahezchallenge.data.database.model.RestaurantFilterParamsDataLayer
import net.jahez.jahezchallenge.data.database.model.RestaurantsEntity
import net.jahez.jahezchallenge.domain.model.RestaurantFilterParams
import net.jahez.jahezchallenge.domain.model.RestaurantItem

internal fun RestaurantsEntity.mapToDomain() = RestaurantItem(
    description, distance, hasOffer, hours, id, image, name, offer, rating
)

internal fun List<RestaurantsEntity>.mapToDomain() = map { it.mapToDomain() }

internal fun RestaurantItem.mapToEntity() = RestaurantsEntity(
    description, distance, hasOffer, hours, id, image, name, offer, rating
)

internal fun List<RestaurantItem>.mapToEntity() = map { it.mapToEntity() }

internal fun RestaurantFilterParams.mapToEntity() =
    RestaurantFilterParamsDataLayer(shouldHasOffer, nearbyRestaurants, highestRating)