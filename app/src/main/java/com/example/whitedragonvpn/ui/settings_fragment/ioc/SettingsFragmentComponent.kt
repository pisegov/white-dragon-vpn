package com.example.whitedragonvpn.ui.settings_fragment.ioc

import androidx.fragment.app.Fragment
import com.example.whitedragonvpn.ioc.ApplicationComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [SettingsFragmentModule::class], dependencies = [ApplicationComponent::class])
@SettingsFragmentScope
interface SettingsFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            applicationComponent: ApplicationComponent,
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: SettingsViewModel
        ): SettingsFragmentComponent
    }

    fun viewModel(): SettingsViewModel
}