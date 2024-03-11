package com.kirollos.moviesapp.domain

import com.kirollos.network.domain.repository.Repository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
//        repository.getPopularMovies()
    }
}