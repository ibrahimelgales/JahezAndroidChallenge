package net.jahez.jahezchallenge.data.repository.data.repository

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import net.jahez.jahezchallenge.data.database.RestaurantsDao
import net.jahez.jahezchallenge.data.database.mapper.mapToDomain
import net.jahez.jahezchallenge.data.network.model.mapToDomain
import net.jahez.jahezchallenge.data.network.service.ApiService
import net.jahez.jahezchallenge.data.repository.ImpRepositoryRestaurants
import net.jahez.jahezchallenge.data.repository.data.MockResponse
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import java.lang.Exception
import java.net.UnknownHostException

class ImpRepositoryRestaurantsTest {

    //region var
    @MockK
    private lateinit var apiService: ApiService
    @MockK
    private lateinit var restaurantsDao: RestaurantsDao

    private lateinit var impRepositoryRestaurants: ImpRepositoryRestaurants
    //endregion

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        impRepositoryRestaurants = ImpRepositoryRestaurants(apiService, restaurantsDao)
    }

    @Test
    fun `getListOfRestaurants fetches list of RestaurantItemResponse and mapToDomain RestaurantItem`() {
        val remoteResponse = MockResponse.getListOfRestaurants()

        //region given
        coEvery {
            apiService.getAllRestaurants()
        } returns remoteResponse

        coEvery {
            restaurantsDao.insertRestaurants(any())
        } returns Unit
        //endregion
        
        //region when
        val result = runBlocking { impRepositoryRestaurants.getAllRestaurants() }
        //endregion
        
        //region then
        result shouldBeEqualTo remoteResponse.mapToDomain()
        //endregion
    }

    @Test
    fun `when remote listOfRestaurants returns null should returns listOfRestaurants from DB layer`() {

        val cacheResponse = MockResponse.getListOfCachedRestaurants()

        //region given
        coEvery {
            apiService.getAllRestaurants()
        } returns null

        coEvery { restaurantsDao.getAllRestaurants() } returns cacheResponse
        //endregion

        //region when
        val result = runBlocking { impRepositoryRestaurants.getAllRestaurants() }
        //endregion

        //region then
        result shouldBeEqualTo cacheResponse.mapToDomain()
        //endregion
    }

    @Test
    fun `when API throw exception should returns listOfRestaurants from DB layer`() {

        val cacheResponse = MockResponse.getListOfCachedRestaurants()

        //region given
        coEvery {
            apiService.getAllRestaurants()
        } throws Exception()

        coEvery { restaurantsDao.getAllRestaurants() } returns cacheResponse
        //endregion

        //region when
        val result = runBlocking { impRepositoryRestaurants.getAllRestaurants() }
        //endregion

        //region then
        result shouldBeEqualTo cacheResponse.mapToDomain()
        //endregion
    }

}