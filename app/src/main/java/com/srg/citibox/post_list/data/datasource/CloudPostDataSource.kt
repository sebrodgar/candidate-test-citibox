package com.srg.citibox.post_list.data.datasource

import com.srg.citibox.common.di.dagger_application.CitiboxApplication
import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

class CloudPostDataSource:
    PostListDataSource {


    override suspend fun getAllPosts(onResult: (data: List<Post>?, error: CitiboxError?) -> Unit) {

        /*CitiboxApplication.apiSetup.getAllPosts()
        //CitiboxApplication.apiSetup.getAllPosts()
        CoroutineScope(Dispatchers.IO).launch {
            val response = CitiboxApplication.apiSetup.getAllPosts()
            withContext(Dispatchers.Main){
                try {
                    if (response.isSuccessful) {

                    }else{

                    }
                }catch (e: HttpException){

                }catch (e: Throwable){

                }
            }
        }*/
    }
}