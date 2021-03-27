package com.hawi.weathermap.ui.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject


class CouponModel {


    data class CouponModel(
        @SerializedName("id") val id: String,
        @SerializedName("valid_from") val valid_from: String,
        @SerializedName("valid_until") val valid_until: String,
        @SerializedName("is_active") val is_active: Boolean,
        @SerializedName("is_deleted") val is_deleted: Boolean,
        @SerializedName("tags") val tags: JSONObject,
        @SerializedName("created_at") val created_at: String,
        @SerializedName("last_updated_at") val last_updated_at: String,
        @SerializedName("code") val code: String,
        @SerializedName("bonus_image_front") val bonus_image_front: String,
        @SerializedName("bonus_image_back") val bonus_image_back: String,
        @SerializedName("user_redeem_limit") val user_redeem_limit: Int,
        @SerializedName("user_limit") val user_limit: Int,
        @SerializedName("tab_type") val tab_type: String,
        @SerializedName("ribbon_msg") val ribbon_msg: String,
        @SerializedName("is_bonus_booster_enabled") val is_bonus_booster_enabled: Boolean,
        @SerializedName("wager_bonus_expiry") val wager_bonus_expiry: Int,
        @SerializedName("wager_to_release_ratio_numerator") val wager_to_release_ratio_numerator: Int,
        @SerializedName("wager_to_release_ratio_denominator") val wager_to_release_ratio_denominator: Int,
        @SerializedName("slabs") val slabs: List<SlabsSubModel>,
        @SerializedName("user_segmentation_type") val user_segmentation_type: String,
        @SerializedName("eligibility_user_levels") val eligibility_user_levels: List<Int>,
        @SerializedName("eligibility_user_segments") val eligibility_user_segments: List<String>,
        @SerializedName("visibility_user_levels") val visibility_user_levels: List<Int>,
        @SerializedName("visibility_user_segments") val visibility_user_segments: List<String>,
        @SerializedName("days_since_last_purchase_min") val days_since_last_purchase_min: Int,
        @SerializedName("_cls") val _cls: String,
        @SerializedName("campaign") val campaign: String,
        @SerializedName("bonus_booster") val bonus_booster: String,


        )


    data class EligibleUserLevel(
        @SerializedName("0") val zero: Int,
        @SerializedName("1") val one: Int
    )


    data class EligibleUserSegments(
        @SerializedName("0") val zero: String,
        @SerializedName("1") val one: String,
        @SerializedName("2") val two: String
    )

    data class VisibilityUserLevel(
        @SerializedName("0") val zero: Int,
        @SerializedName("1") val one: Int
    )


    data class VisibilityUserSegments(
        @SerializedName("0") val zero: String,
        @SerializedName("1") val one: String,
        @SerializedName("2") val two: String
    )


    data class SlabsSubModel(
        @SerializedName("name") val name: String,
        @SerializedName("num") val num: Int,
        @SerializedName("min") val min: Double,
        @SerializedName("max") val max: Double,
        @SerializedName("wagered_percent") val wagered_percent: Double,
        @SerializedName("wagered_max") val wagered_max: Double,
        @SerializedName("otc_percent") val otc_percent: Double,
        @SerializedName("otc_max") val otc_max: Double
    )


}







