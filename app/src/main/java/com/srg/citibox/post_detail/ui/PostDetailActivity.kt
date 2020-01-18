package com.srg.citibox.post_detail.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.srg.citibox.R
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.di.dagger_activity.ActivityComponent
import com.srg.citibox.common.di.dagger_activity.BaseDaggerActivity
import com.srg.citibox.common.util.Constants
import com.srg.citibox.databinding.ActivityPostDetailBinding
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

class PostDetailActivity : BaseDaggerActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var postDetailViewModel: PostDetailViewModel

    private lateinit var binding: ActivityPostDetailBinding

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInjector<ActivityComponent>(this).inject(this)

        if (savedInstanceState == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)
            binding.lifecycleOwner = this
            getExtras()

            binding.postDetailViewModel = postDetailViewModel
            postDetailViewModel.getPostDetails(post)
        }

    }

    private fun getExtras() {
        intent.extras?.let {
            post = it.getSerializable(Constants.POST.value) as Post
        }
    }
}