package com.srg.citibox.common.data.model

import java.io.Serializable

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

data class Comment(
    val id: Long,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String

) : Serializable