package com.example.whitedragonvpn.ui.base_fragment.ioc

import androidx.lifecycle.LifecycleOwner
import com.example.whitedragonvpn.databinding.FragmentBaseBinding
import com.example.whitedragonvpn.ui.base_fragment.BaseFragmentController
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [BaseFragmentViewModule::class],
    dependencies = [BaseFragmentComponent::class]
)
@BaseFragmentViewScope
interface BaseFragmentViewComponent {
    @Component.Factory
    interface Factory {
        fun create(
            baseFragmentComponent: BaseFragmentComponent,
            @BindsInstance viewBinding: FragmentBaseBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner
        ): BaseFragmentViewComponent
    }

    val baseFragmentViewController: BaseFragmentController

}