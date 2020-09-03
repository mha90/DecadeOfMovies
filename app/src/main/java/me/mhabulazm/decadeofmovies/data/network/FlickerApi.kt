package me.mhabulazm.decadeofmovies.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.mhabulazm.decadeofmovies.data.network.response.FlickerResponse
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FlickerService {

    @GET(".")
    fun getMovieImages(@QueryMap map: Map<String, String>): Call<FlickerResponse>

}


object FlickerApi {

    private const val BASE_URL = "https://api.flickr.com/services/rest/"
    private var service: FlickerService? = null

    fun getService(): FlickerService {
        if (service == null) {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(createOkHttpClient())
                .build()
            service = retrofit.create(FlickerService::class.java)
        }
        return service!!
    }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        val interceptor = Interceptor { chain ->

            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()

            val url: HttpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "a087852d25dd90ad99faada59b785f47")
                .build()
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }
        okHttpClient.interceptors().add(interceptor)

        return okHttpClient.build()
    }

}