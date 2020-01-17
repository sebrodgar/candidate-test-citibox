package com.srg.citibox.common.data.model

import java.io.Serializable

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

data class User(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company

) : Serializable