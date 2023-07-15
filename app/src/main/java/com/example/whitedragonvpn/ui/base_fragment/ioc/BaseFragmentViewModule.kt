package com.example.whitedragonvpn.ui.base_fragment.ioc

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
interface BaseFragmentViewModule {
    companion object {
        @Provides
        fun activity(fragment: Fragment): Activity {
            return fragment.requireActivity()
        }
    }
}