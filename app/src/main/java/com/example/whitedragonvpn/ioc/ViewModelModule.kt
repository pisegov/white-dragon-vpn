package com.example.whitedragonvpn.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    fun baseViewModel(viewModel: BaseViewModel): ViewModel
}