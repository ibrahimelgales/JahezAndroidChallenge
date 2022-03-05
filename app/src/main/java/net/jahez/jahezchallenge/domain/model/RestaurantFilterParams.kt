package net.jahez.jahezchallenge.domain.model

import java.lang.reflect.Field

internal data class RestaurantFilterParams(
    var shouldHasOffer: Boolean ?= null,
    var nearbyRestaurants: Boolean ?= null,
    var highestRating: Boolean ?= null
){
    fun isAllFieldsEqualNull(): Boolean {
        val fields: Array<Field> = this.javaClass.declaredFields
        for (f in fields) {
            try {
                val value = f.get(this)
                if (value != null) {
                    return false
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return true
    }
}