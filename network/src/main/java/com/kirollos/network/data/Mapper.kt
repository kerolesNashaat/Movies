package com.kirollos.network.data

import com.kirollos.network.data.local.entity.NowPlayingMovieEntity
import com.kirollos.network.data.local.entity.PopularMovieEntity
import com.kirollos.network.data.local.entity.UpcomingMovieEntity
import com.kirollos.network.data.remote.dto.MovieDto
import com.kirollos.network.domain.model.Movie

//Now playing//
fun MovieDto.toNowPlayingMovieEntity() = NowPlayingMovieEntity(
    id = id,
    datesDto = datesDto,
    page = page,
    resultList = resultEntities?.map { it?.toResult() }?.toMutableList(),
    totalPages = totalPages,
    totalResults = totalResults
)

fun NowPlayingMovieEntity.toMovie(): Movie =
    Movie(
        id = id,
        datesDto = datesDto,
        page = page,
        resultList = resultList,
        totalPages = totalPages,
        totalResults = totalResults
    )

//Popular//
fun MovieDto.toPopularMovieEntity() =
    PopularMovieEntity(
        id = 0,
        datesDto = datesDto,
        page = page,
        resultList = resultEntities?.map { it?.toResult() }?.toMutableList(),
        totalPages = totalPages,
        totalResults = totalResults
    )

fun PopularMovieEntity.toMovie(): Movie =
    Movie(
        id = id,
        datesDto = datesDto,
        page = page,
        resultList = resultList,
        totalPages = totalPages,
        totalResults = totalResults
    )

//Upcoming//
fun MovieDto.toUpcomingMovieEntity() =
    UpcomingMovieEntity(
        id = 0,
        datesDto = datesDto,
        page = page,
        resultList = resultEntities?.map { it?.toResult() }?.toMutableList(),
        totalPages = totalPages,
        totalResults = totalResults
    )

fun UpcomingMovieEntity.toMovie(): Movie =
    Movie(
        id = id,
        datesDto = datesDto,
        page = page,
        resultList = resultList,
        totalPages = totalPages,
        totalResults = totalResults
    )