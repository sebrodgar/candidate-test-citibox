package com.srg.citibox.common

import com.srg.citibox.common.data.model.*
import com.srg.citibox.common.data.network.retrofit.ClientApi
import okhttp3.ResponseBody
import org.mockito.Mockito
import retrofit2.Response


/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

abstract class TestData {


    val clientApi: ClientApi = Mockito.mock(ClientApi::class.java)

    val post1: Post = Post(
        id = 1,
        title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        body = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto",
        userId = 1
    )

    val user1 = User(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "mock@gm.com",
        address = Address("kulas light", "apt 556", "madrid", "28923", Geo(-33.9934, 81.8898)),
        phone = "33333333",
        website = "mock.com",
        company = Company("Company", " Multi-layered", "Harness real-time")
    )

    private val comment1: Comment = Comment(
        1,
        1,
        "id labore ex et quam laborum",
        "mock@ll.com",
        "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium"
    )

    val mockPostList: List<Post> = listOf(post1)
    val mockUserList: List<User> = listOf(user1)
    val mockComments: List<Comment> = listOf(comment1)

    val responseErrorUser: Response<List<User>> = Response.error(500, ResponseBody.create(null, "Internal Error"))
    val responseErrorPost: Response<List<Post>> = Response.error(500, ResponseBody.create(null, "Internal Error"))
    val responseErrorComment: Response<List<Comment>> = Response.error(500, ResponseBody.create(null, "Internal Error"))

}