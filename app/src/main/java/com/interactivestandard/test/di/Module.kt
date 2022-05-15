package com.interactivestandard.test.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.interactivestandard.test.data.Repository
import com.interactivestandard.test.data.RepositoryImpl
import com.interactivestandard.test.data.getInteractiveStandardService
import com.interactivestandard.test.presentation.fragment.DetailsFragment
import com.interactivestandard.test.presentation.fragment.ErrorFragment
import com.interactivestandard.test.presentation.fragment.MainFragment
import com.interactivestandard.test.presentation.viewmodel.DetailsViewModel
import com.interactivestandard.test.presentation.viewmodel.ErrorViewModel
import com.interactivestandard.test.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { getInteractiveStandardService() }

    single { Cicerone.create(Router()) }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
}

val detailsFragmentModule = module {
    scope<DetailsFragment> {
        viewModel { DetailsViewModel(get(), get()) }
        scoped <Repository> { RepositoryImpl(get()) }
    }
}

val mainFragmentModule = module {
    scope<MainFragment> {
        viewModel { MainViewModel(get()) }
    }
}

val errorFragmentModule = module {
    scope<ErrorFragment> {
        viewModel { ErrorViewModel(get()) }
    }
}

