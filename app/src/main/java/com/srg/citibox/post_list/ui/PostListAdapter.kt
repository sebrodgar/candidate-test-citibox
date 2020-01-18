package com.srg.citibox.post_list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srg.citibox.R
import com.srg.citibox.common.data.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class PostListAdapter(private var postList: List<Post>?,
                      private val postClickedListener: (Post) -> Unit) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(postView)
    }

    override fun getItemCount(): Int = postList?.size ?: 0

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postList?.let { it[position] }
        item?.let { holder.bind(it) }
    }

    fun setPosts(newPosts: List<Post>?) {
        newPosts?.let {
            this.postList = newPosts
            notifyDataSetChanged()
        }
    }


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(postItem: Post) = with(itemView) {
            postTitle.text = postItem.title
            setOnClickListener {
                postClickedListener(postItem)
            }

        }

    }
}

// Item select interface of adapter
interface OnSelectItemListener {
    fun onItemSelected(post: Post)
}