package com.kirollos.moviesapp.domain

import com.kirollos.network.domain.repository.Repository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(language: String, movieId: Int) =
        repository.getMovieDetails(language, movieId)
}