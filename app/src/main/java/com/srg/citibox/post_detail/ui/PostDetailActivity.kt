package com.srg.citibox.post_detail.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.srg.citibox.R
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.di.dagger_activity.ActivityComponent
import com.srg.citibox.common.di.dagger_activity.BaseDaggerActivity
import com.srg.citibox.common.util.Constants
import com.srg.citibox.databinding.ActivityPostDetailBinding
import com.srg.citibox.post_list.ui.PostListViewModel
import kotlinx.android.synthetic.main.activity_post_detail.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

class PostDetailActivity: BaseDaggerActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var postDetailViewModel: PostDetailViewModel

    private lateinit var binding: ActivityPostDetailBinding

    private var userId: Long = 0
    private var postId: Long = 0

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)

        getExtras()

        getInjector<ActivityComponent>(this).inject(this)



        postDetailViewModel.author.observe(this, Observer {
            Timber.d("Author: %s", it.toString())




        })

        postDetailViewModel.numberOfComment.observe(this, Observer {
            Timber.d("NUMBER OF COMMENTS: %s", it.toString())
            
        })


        if(savedInstanceState == null) {
            binding.postDetailViewModel = postDetailViewModel
            postDetailViewModel.getPostDetails(post)
        }



    }

    private fun getExtras(){
        intent.extras?.let {
            //userId = it.getLong(Constants.USER_ID.value)
            //postId = it.getLong(Constants.POST_ID.value)
            post = it.getSerializable(Constants.POST.value) as Post
        }
    }
}