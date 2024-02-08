package com.adia.dev.playground.network

import com.adia.dev.playground.models.Manga
import retrofit2.Call
import retrofit2.http.GET

interface MangaApi {
    @GET("mangas/list")
    fun getMangas(): Call<Manga>
}