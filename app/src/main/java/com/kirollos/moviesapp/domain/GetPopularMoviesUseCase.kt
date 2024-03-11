package com.kirollos.moviesapp.domain

import com.kirollos.dataSource.domain.repository.Repository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(page: Int) = repository.getPopularMovies(page)
}