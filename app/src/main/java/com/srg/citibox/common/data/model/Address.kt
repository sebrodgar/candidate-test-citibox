package com.srg.citibox.common.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    @SerializedName("zipcode")
    val zipCode: String,
    val geo: Geo

) : Serializable