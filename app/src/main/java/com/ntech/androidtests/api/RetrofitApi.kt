package com.ntech.androidtests.api

import com.ntech.androidtests.model.ImageResponse
import com.ntech.androidtests.util.Util
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = Util.API_KEY
    ) : Response<ImageResponse>

}