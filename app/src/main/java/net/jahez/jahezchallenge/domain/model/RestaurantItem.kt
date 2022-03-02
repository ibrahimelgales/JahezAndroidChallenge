package net.jahez.jahezchallenge.domain.model

internal data class RestaurantItem(
    val description: String,
    val distance: String,
    val hasOffer: Boolean,
    val hours: String,
    val id: Int,
    val image: String,
    val name: String,
    val offer: String?,
    val rating: String
)