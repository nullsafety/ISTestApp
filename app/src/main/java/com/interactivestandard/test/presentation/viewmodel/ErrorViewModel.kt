package com.interactivestandard.test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.interactivestandard.test.presentation.Screens.Details

class ErrorViewModel(private val router: Router) : ViewModel() {
    fun okay() { router.exit() }
}