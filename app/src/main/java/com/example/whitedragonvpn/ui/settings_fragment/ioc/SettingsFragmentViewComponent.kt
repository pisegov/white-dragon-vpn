package com.example.whitedragonvpn.ui.settings_fragment.ioc

import androidx.lifecycle.LifecycleOwner
import androidx.preference.PreferenceManager
import com.example.whitedragonvpn.ui.settings_fragment.SettingsFragmentViewController
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [SettingsFragmentViewModule::class],
    dependencies = [SettingsFragmentComponent::class]
)
@SettingsFragmentViewScope
interface SettingsFragmentViewComponent {
    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: SettingsFragmentComponent,
            @BindsInstance preferenceManager: PreferenceManager,
            @BindsInstance lifecycleOwner: LifecycleOwner
        ): SettingsFragmentViewComponent
    }

    val viewController: SettingsFragmentViewController
}