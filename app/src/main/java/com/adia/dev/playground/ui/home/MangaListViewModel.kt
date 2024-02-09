package com.adia.dev.playground.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adia.dev.playground.models.Manga
import com.adia.dev.playground.repositories.MangaRepository
import kotlinx.coroutines.launch

class MangaListViewModel(private val mangaRepository: MangaRepository) : ViewModel() {
    private val _mangas = MutableLiveData<List<Manga>>()
    val mangas: LiveData<List<Manga>> = _mangas

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getMangas() {
        viewModelScope.launch {
            try {
                val mangas = mangaRepository.getMangas(includes = listOf("cover_art"))
                if (mangas.isNullOrEmpty()) {
                    _error.value = "No mangas found"
                    Log.e("MangaListViewModel", "No mangas found")
                    return@launch
                }
                _mangas.value = mangas!!
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("MangaListViewModel", e.message ?: "Error")
            }
        }
    }
}