package net.jahez.jahezchallenge.data.network.model
data class RestaurantItemResponse(
    val id: Int,
    val description: String,
    val distance: Double,
    val hasOffer: Boolean,
    val hours: String,
    val image: String,
    val name: String,
    val offer: String,
    val rating: String
)



