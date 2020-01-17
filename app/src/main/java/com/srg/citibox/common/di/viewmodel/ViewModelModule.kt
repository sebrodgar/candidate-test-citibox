package com.srg.citibox.common.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.srg.citibox.post_detail.ui.PostDetailViewModel
import com.srg.citibox.post_list.ui.PostListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindsPostViewModel(postListViewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel::class)
    abstract fun bindsPostDetailViewModel(postDetailViewModel: PostDetailViewModel) : ViewModel


}