package com.hawi.weathermap.ui.view.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hawi.weathermap.R
import com.hawi.weathermap.databinding.BonuscouponRowviewBinding
import com.hawi.weathermap.ui.model.CouponModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class BonusVerticalAdapter(mcontext: Context, mList: List<CouponModel.CouponModel>) :
    RecyclerView.Adapter<BonusVerticalAdapter.FAQViewHolder>() {

    private var context: Context? = mcontext
    private var mList: List<CouponModel.CouponModel> = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {

        val binding = DataBindingUtil.inflate<BonuscouponRowviewBinding>(
            LayoutInflater.from(context),
            R.layout.bonuscoupon_rowview, parent, false
        )
        return FAQViewHolder(binding)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        holder.bind(mList[position])
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    inner class FAQViewHolder(@param:NonNull var binding: BonuscouponRowviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var viewBinding: BonuscouponRowviewBinding = binding

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: CouponModel.CouponModel) {
            binding.ribbinmsgTxt.text = item.ribbon_msg
            binding.codeTxt.text = item.code
            binding.minSlabTxtTxt.text = "\u20B9" + (item.slabs.get(0).min).toInt()
            val totalpercent = item.slabs.get(0).wagered_percent + item.slabs.get(0).otc_percent
            val totalvalue = (item.slabs.get(0).wagered_max + item.slabs.get(0).otc_max).toInt()
            binding.slabPercent.text = ("Get " + totalpercent + "%"
                    + " Upto ₹" + totalvalue)
            binding.firstcolomnDataone.text =
                ">=" + item.slabs.get(0).min + " " + " & <" + item.slabs.get(
                    0
                ).max
            binding.firstcolomn1Dataone.text =
                ">=" + item.slabs.get(0).min + " " + " & <" + item.slabs.get(
                    0
                ).max
            binding.firstcolomn2Dataone.text =
                ">=" + item.slabs.get(0).min + " " + " & <" + item.slabs.get(
                    0
                ).max
            //   binding?.validTxt.setText("Valid till" + dateFormat(item.valid_until))

            binding.secondcolomnDatatwo.text =
                item.slabs.get(0).wagered_percent.toString() + "% " + "(Max. ₹" + item.slabs.get(
                    0
                ).wagered_max
            binding.secondcolomn1Datatwo.text =
                item.slabs.get(0).wagered_percent.toString() + "% " + "(Max. ₹" + item.slabs.get(
                    0
                ).wagered_max
            binding.secondcolomn2Datatwo.text =
                item.slabs.get(0).wagered_percent.toString() + "% " + "(Max. ₹" + item.slabs.get(
                    0
                ).wagered_max

            binding.thirdcolomnDatathree.text =
                item.slabs.get(0).otc_percent.toString() + "% " + "(Max. ₹" + item.slabs.get(
                    0
                ).otc_max
            binding.thirdcolomn1Datathree.text =
                item.slabs.get(0).otc_percent.toString() + "% " + "(Max. ₹" + item.slabs.get(
                    0
                ).otc_max
            binding.thirdcolomn2Datathree.text =
                item.slabs.get(0).otc_percent.toString() + "% " + "(Max. ₹" + item.slabs.get(
                    0
                ).otc_max


            binding.forevery.text = Html.fromHtml(
                "For every " + "<font color=\"#FFFF00\">" + "₹" + item.wager_to_release_ratio_numerator + "</font>" + " bet " +
                        "<font color=\"#FFFF00\">" + "₹" + item.wager_to_release_ratio_denominator + "</font>"
                        + " will be <br> released from the bonus amount"
            )

            binding.forexpire.text =
                Html.fromHtml("Bonus expiry " + "<font color=\"#FFFF00\">" + item.wager_bonus_expiry + " days" + "</font>" + "<br> from the issue")

        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormat(timeData: String): String {
        val inputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
        val date: LocalDate = LocalDate.parse(timeData, inputFormatter)
        val formattedDate: String = outputFormatter.format(date)
        return formattedDate
    }

}