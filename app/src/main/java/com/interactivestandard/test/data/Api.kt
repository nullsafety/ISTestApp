package com.interactivestandard.test.data

import com.interactivestandard.test.data.response.PointsResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val baseUrl = "https://hr-challenge.interactivestandard.com/api/test/"

fun getInteractiveStandardService(): InteractiveStandardApi {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient.build())
        .build()

    return retrofit.create(InteractiveStandardApi::class.java)
}

interface InteractiveStandardApi {
    @Headers("accept: application/json")
    @GET("points")
    fun getPointsResponse(@Query("count") counts: Int): Single<PointsResponse>
}