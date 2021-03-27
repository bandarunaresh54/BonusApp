package com.citra.app.network

import com.google.gson.GsonBuilder
import com.hawi.weathermap.network.Api
import com.hawi.weathermap.ui.model.CouponModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class APIWrapper private constructor() {

    protected var mAPI: Api

    companion object {
        private var apiWrapper: APIWrapper? = null
        val API_BASE_URL = ""
        val instance: APIWrapper
            get() {
                if (apiWrapper == null) {
                    apiWrapper = APIWrapper()
                }
                return apiWrapper!!
            }
    }

    init {
        val BASE_URL = "https://run.mocky.io/"
        val logging = HttpLoggingInterceptor()
        val httpClient = getOkHttpBuilder().connectTimeout(
            5,
            TimeUnit.MINUTES
        ).readTimeout(5, TimeUnit.MINUTES)
        httpClient.addInterceptor(logging)
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = Retrofit.Builder().client(httpClient.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            ).baseUrl(BASE_URL).build()


        mAPI = builder.create(Api::class.java)

    }

    fun getOkHttpBuilder(): OkHttpClient.Builder =

        // Workaround for the error "Caused by: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException: Could not validate certificate: Certificate expired at".
        getUnsafeOkHttpClient()


    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) = Unit

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) = Unit

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            builder.hostnameVerifier { _, _ -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    fun getBonousData(): Observable<List<CouponModel.CouponModel>> {

        return mAPI.getBonousData()
    }


}