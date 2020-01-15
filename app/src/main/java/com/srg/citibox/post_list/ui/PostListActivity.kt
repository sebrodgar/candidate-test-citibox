package com.srg.citibox.post_list.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.srg.citibox.common.di.dagger_activity.ActivityComponent
import com.srg.citibox.common.di.dagger_activity.BaseDaggerActivity
import com.srg.citibox.common.di.viewmodel.ViewModelFactory
import androidx.lifecycle.ViewModelProviders
import com.srg.citibox.R
import com.srg.citibox.common.data.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */


class PostListActivity: BaseDaggerActivity(), OnSelectItemListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var postListViewModel: PostListViewModel

    private val postAdapter by lazy { PostListAdapter(emptyList()) { post -> onItemSelected(post) } }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getInjector<ActivityComponent>(this).inject(this)

        postList.apply { adapter = postAdapter }

        postListViewModel.posts.observe(this, Observer {
            Timber.d("SE ACTUALIZA LOS POSTS")
            setPosts(postListViewModel.posts.value)

        })

        if (savedInstanceState == null){
            postListViewModel.getPosts()
        }


    }

    private fun setPosts(posts: List<Post>?){
        postAdapter.setPosts(posts)
    }

    override fun onItemSelected(post: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}