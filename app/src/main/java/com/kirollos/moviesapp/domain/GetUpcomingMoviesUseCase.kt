package com.kirollos.moviesapp.domain

import com.kirollos.dataSource.domain.repository.Repository
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(page: Int) = repository.getUpcomingMovies(page)

}