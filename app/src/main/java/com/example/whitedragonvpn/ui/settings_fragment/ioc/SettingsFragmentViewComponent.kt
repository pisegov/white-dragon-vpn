package com.example.whitedragonvpn.ui.settings_fragment.ioc

import androidx.lifecycle.LifecycleOwner
import com.example.whitedragonvpn.databinding.FragmentSettingsBinding
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
            @BindsInstance viewBinding: FragmentSettingsBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner
        ): SettingsFragmentViewComponent
    }

    val viewController: SettingsFragmentViewController
}