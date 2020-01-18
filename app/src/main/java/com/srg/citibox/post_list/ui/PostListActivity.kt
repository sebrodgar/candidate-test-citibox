package com.srg.citibox.post_list.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.srg.citibox.R
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.di.dagger_activity.ActivityComponent
import com.srg.citibox.common.di.dagger_activity.BaseDaggerActivity
import com.srg.citibox.common.util.Constants
import com.srg.citibox.common.util.extension.openActivity
import com.srg.citibox.databinding.ActivityMainBinding
import com.srg.citibox.post_detail.ui.PostDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */


class PostListActivity : BaseDaggerActivity(), OnSelectItemListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var postListViewModel: PostListViewModel

    private val postAdapter by lazy { PostListAdapter(emptyList()) { post -> onItemSelected(post) } }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInjector<ActivityComponent>(this).inject(this)

        if (savedInstanceState == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            binding.postListViewModel = postListViewModel
            binding.lifecycleOwner = this
            postList.apply { adapter = postAdapter }

            postListViewModel.getPosts()
        }

    }



    override fun onItemSelected(post: Post) {
        val extras = Bundle()
        extras.putSerializable(Constants.POST.value, post)

        openActivity(PostDetailActivity::class.java, extras)
    }

    companion object {
        @BindingAdapter("data")
        @JvmStatic
        fun setRecyclerViewProperties(recyclerView: RecyclerView?, data: List<Post>?) {
            val adapter = recyclerView?.adapter
            if (adapter is PostListAdapter && data != null) {
                adapter.setPosts(data)
            }
        }

        @BindingAdapter("android:visibility")
        @JvmStatic
        fun setVisibility(view: View, value: Boolean) {
            view.visibility = if (value) View.VISIBLE else View.GONE
        }
    }


}