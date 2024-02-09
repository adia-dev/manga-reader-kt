package com.adia.dev.playground.di.modules

import com.adia.dev.playground.repositories.MangaRepository
import com.adia.dev.playground.ui.home.MangaListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MangaRepository(get()) }
    viewModel { MangaListViewModel(get()) }
}
