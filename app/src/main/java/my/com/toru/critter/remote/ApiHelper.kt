package my.com.toru.critter.remote

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import my.com.toru.critter.model.CritterNewDB
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
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

    fun getData(successCB:(ArrayList<CritterNewDB>)->Unit,
                failedCB:()->Unit){
        retrofit.create(CritterService::class.java)
                .getCritters("http://35.229.42.247:5000/get_data")
                .enqueue(AdvCallback(failedCB, successCB))
    }
}

interface CritterService{
    @GET
    fun getCritters(@Url url:String): Call<ArrayList<CritterNewDB>>
}