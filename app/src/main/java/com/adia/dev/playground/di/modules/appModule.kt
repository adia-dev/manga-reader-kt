package com.adia.dev.playground.di.modules

import com.adia.dev.playground.repositories.MangaRepository
import org.koin.dsl.module

val appModule = module {
    single { MangaRepository(get()) }
}
