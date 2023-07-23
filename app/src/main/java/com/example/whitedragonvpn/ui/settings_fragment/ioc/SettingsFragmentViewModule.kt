package com.example.whitedragonvpn.ui.settings_fragment.ioc

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
interface SettingsFragmentViewModule {
    companion object {
        @Provides
        fun activity(fragment: Fragment): Activity {
            return fragment.requireActivity()
        }
    }
}

