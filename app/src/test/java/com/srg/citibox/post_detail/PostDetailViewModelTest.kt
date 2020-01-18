package com.srg.citibox.post_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Comment
import com.srg.citibox.common.data.model.User
import com.srg.citibox.post_detail.data.datasource.CloudPostDetailDataSource
import com.srg.citibox.post_detail.data.repository.PostDetailDataRepository
import com.srg.citibox.post_detail.domain.repository.PostDetailRepository
import com.srg.citibox.post_detail.domain.usecase.GetAuthorByPost
import com.srg.citibox.post_detail.domain.usecase.GetNumberOfCommentByPost
import com.srg.citibox.post_detail.ui.PostDetailViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.Response

/**
 * Created by Sebastián Rodríguez on 18,January,2020
 */

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class PostDetailViewModelTest : TestData() {

    lateinit var cloudPostDetailDataSource: CloudPostDetailDataSource
    lateinit var postDetailRepository: PostDetailRepository

    lateinit var getAuthorByPost: GetAuthorByPost
    lateinit var getNumberOfCommentByPost: GetNumberOfCommentByPost

    lateinit var postDetailViewModel: PostDetailViewModel

    private lateinit var expectedResponseUsers: Response<List<User>>
    private lateinit var expectedResponseComments: Response<List<Comment>>


    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
        cloudPostDetailDataSource = CloudPostDetailDataSource(clientApi)
        postDetailRepository = PostDetailDataRepository(cloudPostDetailDataSource)

        getAuthorByPost = GetAuthorByPost(postDetailRepository)
        getNumberOfCommentByPost = GetNumberOfCommentByPost(postDetailRepository)

        postDetailViewModel = PostDetailViewModel(getAuthorByPost, getNumberOfCommentByPost)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `get post details successful response`() {
        runBlocking(postDetailViewModel.viewModelScope.coroutineContext) {

            expectedResponseUsers = Response.success(200, mockUserList)
            expectedResponseComments = Response.success(200, mockComments)

            `when`(clientApi.getAllUsers()).thenReturn(expectedResponseUsers)
            `when`(clientApi.getAllComments()).thenReturn(expectedResponseComments)

            postDetailViewModel.getPostDetails(post1)

        }

        postDetailViewModel.author.observeForever {
            assertEquals(it, user1)

        }

        postDetailViewModel.numberOfComment.observeForever {
            assertEquals(it, 1)
        }

    }

    @Test
    fun `get post details error response`() {
        runBlocking(postDetailViewModel.viewModelScope.coroutineContext) {

            expectedResponseUsers = responseErrorUser
            expectedResponseComments = responseErrorComment

            `when`(clientApi.getAllUsers()).thenReturn(expectedResponseUsers)
            `when`(clientApi.getAllComments()).thenReturn(expectedResponseComments)

            postDetailViewModel.getPostDetails(post1)

        }

        postDetailViewModel.errorLive.observeForever {
            assertEquals(it, CitiboxError.internalServerError())

        }


    }
    @Test
    fun `get post details error in users response`() {
        runBlocking(postDetailViewModel.viewModelScope.coroutineContext) {

            expectedResponseUsers = responseErrorUser
            expectedResponseComments = Response.success(200, mockComments)

            `when`(clientApi.getAllUsers()).thenReturn(expectedResponseUsers)
            `when`(clientApi.getAllComments()).thenReturn(expectedResponseComments)

            postDetailViewModel.getPostDetails(post1)

        }

        postDetailViewModel.errorLive.observeForever {
            assertEquals(it, CitiboxError.internalServerError())

        }


    }

}