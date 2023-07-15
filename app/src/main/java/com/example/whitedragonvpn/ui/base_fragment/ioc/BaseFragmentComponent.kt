package com.example.whitedragonvpn.ui.base_fragment.ioc

import androidx.fragment.app.Fragment
import com.example.whitedragonvpn.ioc.ApplicationComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [BaseFragmentModule::class], dependencies = [ApplicationComponent::class])
@BaseFragmentScope
interface BaseFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            applicationComponent: ApplicationComponent,
            @BindsInstance fragment: Fragment,
        ): BaseFragmentComponent
    }

    fun fragment(): Fragment
}