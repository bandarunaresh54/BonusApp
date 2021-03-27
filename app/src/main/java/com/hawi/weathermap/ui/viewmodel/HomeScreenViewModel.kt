package com.hawi.weathermap.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.citra.app.network.APIWrapper
import com.hawi.weathermap.ui.model.CouponModel
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomeScreenViewModel : ViewModel() {


    var mutableList: MutableLiveData<List<CouponModel.CouponModel>> = MutableLiveData()
    private val TAG = HomeScreenViewModel::class.java.simpleName


    fun getCaseTypeObservable() {

        APIWrapper.instance.getBonousData().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(getCaseSubTypeSubscriber())
    }


    fun getCaseSubTypeSubscriber(): Subscriber<List<CouponModel.CouponModel>> {
        return object : Subscriber<List<CouponModel.CouponModel>>() {

            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                mutableList.value = null
                e.printStackTrace()

            }

            override fun onNext(CaseSubTypeResponse: List<CouponModel.CouponModel>) =
                if (CaseSubTypeResponse.size > 0) {
                    mutableList.value = CaseSubTypeResponse
                } else {

                }
        }
    }


}