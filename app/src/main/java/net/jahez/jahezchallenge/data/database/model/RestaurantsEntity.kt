package net.jahez.jahezchallenge.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Entity(tableName = "restaurants")
@TypeConverters(RestaurantEntityTypeConverter::class)
internal data class RestaurantsEntity(
    val description: String,
    val distance: String,
    val hasOffer: Boolean,
    val hours: String,
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val offer: String?,
    val rating: String,
)

internal class RestaurantEntityTypeConverter {
    private val type =
        Types.newParameterizedType(List::class.java, RestaurantsEntity::class.java)
    private val adapter: JsonAdapter<List<RestaurantsEntity>> =
        Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<RestaurantsEntity>): String =
        adapter.toJson(someObjects)
}
