package com.interactivestandard.test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.interactivestandard.test.presentation.Screens.Details

class MainViewModel(private val router: Router) : ViewModel() {
    fun applyCount(text: CharSequence?) {
        router.navigateTo(Details(text.toString().toInt()))
    }
}