package com.interactivestandard.test.presentation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.interactivestandard.test.presentation.fragment.DetailsFragment
import com.interactivestandard.test.presentation.fragment.ErrorFragment
import com.interactivestandard.test.presentation.fragment.MainFragment

object Screens {
    fun Main() = FragmentScreen {
        MainFragment()
    }
    fun Details(count: Int) = FragmentScreen {
        DetailsFragment.getInstance(count)
    }
    fun Error() = FragmentScreen {
        ErrorFragment()
    }
}