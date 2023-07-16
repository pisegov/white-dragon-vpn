package com.example.whitedragonvpn.ui.countries_fragment.ioc

import androidx.lifecycle.LifecycleOwner
import com.example.whitedragonvpn.databinding.FragmentCountriesBinding
import com.example.whitedragonvpn.ui.countries_fragment.CountriesFragmentController
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [CountriesFragmentViewModule::class],
    dependencies = [CountriesFragmentComponent::class]
)
@CountriesFragmentViewScope
interface CountriesFragmentViewComponent {
    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: CountriesFragmentComponent,
            @BindsInstance viewBinding: FragmentCountriesBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner
        ): CountriesFragmentViewComponent
    }


    @CountriesFragmentViewScope
    val countriesViewController: CountriesFragmentController
}