package com.adia.dev.playground.repositories

import com.adia.dev.playground.network.MangaApi

class MangaRepository(private val mangaApi: MangaApi) {
    fun getMangas() {
        mangaApi.getMangas()
    }
}