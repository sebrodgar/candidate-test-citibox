package com.srg.citibox.common.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

data class Geo(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lng")
    val longitude: Double

) : Serializable