package com.example.whitedragonvpn.ui.countries_fragment.ioc

import androidx.fragment.app.Fragment
import com.example.whitedragonvpn.ioc.ApplicationComponent
import com.example.whitedragonvpn.ui.countries_fragment.recycler.CountriesAdapter
import com.example.whitedragonvpn.ui.shared_components.BaseViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CountriesFragmentModule::class], dependencies = [ApplicationComponent::class])
@CountriesFragmentScope
interface CountriesFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            applicationComponent: ApplicationComponent,
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: BaseViewModel
        ): CountriesFragmentComponent
    }

    fun fragment(): Fragment
    fun viewModel(): BaseViewModel
    fun adapter(): CountriesAdapter
}