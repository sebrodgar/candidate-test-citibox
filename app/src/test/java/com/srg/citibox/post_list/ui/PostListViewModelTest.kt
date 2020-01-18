package com.srg.citibox.post_list.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.srg.citibox.common.TestData
import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.data.repository.PostListDataRepository
import com.srg.citibox.post_list.domain.repository.PostListRepository
import com.srg.citibox.post_list.domain.usecase.GetAllPostList
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
class PostListViewModelTest : TestData() {

    lateinit var cloudPostListDataSource: CloudPostListDataSource
    lateinit var postListRepository: PostListRepository

    lateinit var getAllPostList: GetAllPostList

    lateinit var postListViewModel: PostListViewModel

    private lateinit var expectedResponse: Response<List<Post>>


    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
        cloudPostListDataSource = CloudPostListDataSource(clientApi)
        postListRepository = PostListDataRepository(cloudPostListDataSource)

        getAllPostList = GetAllPostList(postListRepository)

        postListViewModel = PostListViewModel(getAllPostList)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `get post successful response`() {
        runBlocking(postListViewModel.viewModelScope.coroutineContext) {

            expectedResponse = Response.success(200, mockPostList)

            `when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

            postListViewModel.getPosts()

        }

        postListViewModel.posts.observeForever {
            // This is because posts is initialized with empty list.
            if(it.isNotEmpty()){
                assertEquals(it, mockPostList)
                assertEquals(it.first().title, mockPostList.first().title)
            }

        }

    }

    @Test
    fun `get post error response`() {

        runBlocking(postListViewModel.viewModelScope.coroutineContext) {

            expectedResponse = responseErrorPost

            `when`(clientApi.getAllPosts()).thenReturn(expectedResponse)

            postListViewModel.getPosts()

        }

        postListViewModel.errorLive.observeForever {
            assertNotNull(it.errorType)
            assertEquals(it.errorType, CitiboxError.CitiboxErrorType.INTERNAL_SERVER_ERROR)
        }

    }




}