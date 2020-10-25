package eu.rafaelsantiago.rimluas.data.repository

import eu.rafaelsantiago.rimluas.BuildConfig
import eu.rafaelsantiago.rimluas.data.remote.LuasAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class LuasRepository {
    private val BASE_URL = "http://luasforecasts.rpa.ie/xml/"
    private var luasService: LuasAPI
    private val logging = HttpLoggingInterceptor()

    init {
        val client = OkHttpClient.Builder()
        client.interceptors().add(Interceptor { chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder().method(originalRequest.method, originalRequest.body)

            chain.proceed(builder.build())
        })

        logging.level = HttpLoggingInterceptor.Level.NONE
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()

        luasService = retrofit.create(LuasAPI::class.java)
    }

    suspend fun getStopForecast(luasStopAbbrev: String) = luasService.getStopForecast(luasStopAbbrev)
}