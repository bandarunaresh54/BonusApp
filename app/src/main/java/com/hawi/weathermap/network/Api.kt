package com.hawi.weathermap.network

import com.hawi.weathermap.ui.model.CouponModel
import retrofit2.http.GET
import rx.Observable

interface Api {


    @GET("v3/4c663239-03af-49b5-bcb3-0b0c41565bd2")
    fun getBonousData(): Observable<List<CouponModel.CouponModel>>

}

