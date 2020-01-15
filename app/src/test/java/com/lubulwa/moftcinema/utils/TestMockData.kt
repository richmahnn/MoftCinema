package com.lubulwa.moftcinema.utils

import com.lubulwa.moftcinema.remote.model.MoftMovie
import com.lubulwa.moftcinema.remote.model.MovieResponse
import java.util.concurrent.ThreadLocalRandom

object TestMockData {
    fun getTrendingMovies(): MovieResponse {
        val movies = ArrayList<MoftMovie>()
        val genresId = arrayListOf(35, 18, 10749)
        movies.add(
            MoftMovie(
                false,
                "/pVGzV02qmHVoKx9ehBNy7m2u5fs.jpg",
                genresId,
                19404,
                "hi",
                "िलवाले दुल्हनिया ले जायेंगे",
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                23.112,
                "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                "1995-10-20",
                "Dilwale Dulhania Le Jayenge",
                false,
                8.9,
                2184
            )
        )
        return MovieResponse(randomInt(), movies, randomInt(), randomInt())
    }

    fun getTrendingMoviesEmptyList(): MovieResponse {
        return MovieResponse(randomInt(), emptyList(), randomInt(), randomInt())
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }


}