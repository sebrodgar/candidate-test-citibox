package com.srg.citibox.common.data.model

import java.io.Serializable

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String

): Serializable