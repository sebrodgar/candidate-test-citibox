package com.srg.citibox.common.di.viewmodel

import androidx.lifecycle.ViewModelProvider
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

    /*@Binds
    @IntoMap
    @ViewModelKey()

    TODO Poner los ViewModels de List y Details

    */

}