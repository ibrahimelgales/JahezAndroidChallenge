package net.jahez.jahezchallenge.data.network.model
data class RestaurantItemResponse(
    val description: String,
    val distance: Double,
    val hasOffer: Boolean,
    val hours: String,
    val id: Int,
    val image: String,
    val name: String,
    val offer: String,
    val rating: String
)



