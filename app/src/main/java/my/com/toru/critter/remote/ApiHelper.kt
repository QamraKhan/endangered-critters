package my.com.toru.critter.remote

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
* Singleton Pattern
* */
object ApiHelper {
    private const val BASE_URL = "https://challenge.smove.sg"
    private const val TIMEOUT = 5000L
    private const val TAG = "ApiHelper"

    private var retrofit:Retrofit
    private var okHttpClient:OkHttpClient

    init {
        Log.w(TAG, "init")
        val networkInterceptor = HttpLoggingInterceptor()
        networkInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(networkInterceptor)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .build()

         retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}