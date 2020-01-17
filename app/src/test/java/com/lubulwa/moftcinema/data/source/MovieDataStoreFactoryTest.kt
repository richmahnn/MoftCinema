package com.lubulwa.moftcinema.data.source

import com.lubulwa.moftcinema.data.repository.MovieDataStore
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MovieDataStoreFactoryTest {

    @Mock
    private lateinit var dataStore: MovieRemoteDataStore

    private lateinit var factory: MovieDataStoreFactory

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        factory = MovieDataStoreFactory(dataStore)
    }

    @Test
    fun `retrieveDataStore returns movieDataStore`() {
        val result = factory.retrieveDataStore()
        assertThat(result, `is`(instanceOf(MovieDataStore::class.java)))
    }

}