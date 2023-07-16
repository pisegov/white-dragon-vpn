package com.example.whitedragonvpn.ui.countries_fragment.ioc

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
interface CountriesFragmentViewModule {
    companion object {
        @Provides
        fun activity(fragment: Fragment): Activity {
            return fragment.requireActivity()
        }
    }
}