package com.srg.citibox.common

import com.srg.citibox.common.data.model.Post

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

abstract class TestData {

    private val post1: Post = Post(
        id = 1,
        title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        body = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto",
        userId = 1
    )

    val mockPostList: List<Post> = listOf(post1)
}