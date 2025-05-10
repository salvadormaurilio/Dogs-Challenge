package com.example.dogschallenge.data.datasource.remote

import com.example.dogschallenge.core.MockWebServerRule
import com.example.dogschallenge.core.assertThatEquals
import com.example.dogschallenge.core.assertThatIsInstanceOf
import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.data.datasource.remote.retrofit.DogsServiceRetrofit
import com.example.dogschallenge.fakedata.EXPECT_DOGS_ENDPOINT
import com.example.dogschallenge.fakedata.givenDogsResponseFakeData
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogsRemoteDataSourceShould {

    @get:Rule
    val webServerRule = MockWebServerRule()

    private lateinit var dogsRemoteDataSource: DogsRemoteDataSource

    @Before
    fun setup() {
        dogsRemoteDataSource = DogsRemoteDataSourceImpl(
            webServerRule.mockRetrofit().create(DogsServiceRetrofit::class.java)
        )
    }

    @Test
    fun `Get DogsResponse data when fetchDogs is success`(): Unit = runTest {
        val dogsResponse = givenDogsResponseFakeData()
        webServerRule.loadMockResponse(fileName = "dogsResponse.json")

        val result = dogsRemoteDataSource.fetchDogs().lastOrNull()

        webServerRule.assertRequestMethod(
            path = EXPECT_DOGS_ENDPOINT,
            method = MockWebServerRule.GET
        )
        assertThatEquals(result?.getOrNull(), dogsResponse)
    }

    @Test
    fun `Get DogsException data when fetchDogs is failure`() = runTest {
        webServerRule.loadMockResponse(responseCode = 400)

        val result = dogsRemoteDataSource.fetchDogs().lastOrNull()

        webServerRule.assertRequestMethod(
            path = EXPECT_DOGS_ENDPOINT,
            method = MockWebServerRule.GET
        )
        assertThatIsInstanceOf<DataException.DogsException>(result?.exceptionOrNull())
    }
}
