package com.hawi.weathermap.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hawi.weathermap.R
import com.hawi.weathermap.databinding.ActivityMainBinding
import com.hawi.weathermap.ui.model.CouponModel
import com.hawi.weathermap.ui.view.adapter.BonusVerticalAdapter
import com.hawi.weathermap.ui.viewmodel.HomeScreenViewModel
import com.kaopiz.kprogresshud.KProgressHUD

class HomeScreenActivity : AppCompatActivity() {
    var bonusItemList = ArrayList<CouponModel.CouponModel>()
    var binding: ActivityMainBinding? = null
    var bonusVerticalAdapter: BonusVerticalAdapter? = null
    private lateinit var mViewModel: HomeScreenViewModel

    protected var mDialog: KProgressHUD? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mDialog = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setDimAmount(0.5f)
        showLoading()
        initRecyclerData()
        initializeBonousItemList()
    }


    fun initRecyclerData() {
        bonusVerticalAdapter = BonusVerticalAdapter(
            this,
            bonusItemList
        )
        binding?.recyclerView!!.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
    }

    fun initializeBonousItemList() {
        mViewModel.getCaseTypeObservable()
        mViewModel.mutableList.observe(this, Observer {
          it?.let {
              dismissLoading()
              bonusVerticalAdapter = BonusVerticalAdapter(
                  this,
                  it
              )
              binding?.recyclerView!!.adapter = bonusVerticalAdapter

          }
            dismissLoading()

        })
    }


    fun showLoading() {
        if (!mDialog!!.isShowing)
            mDialog!!.show()
    }

    fun dismissLoading() {
        mDialog!!.dismiss()
    }


}