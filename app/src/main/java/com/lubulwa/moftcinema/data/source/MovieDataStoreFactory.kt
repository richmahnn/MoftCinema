package com.lubulwa.moftcinema.data.source

import com.lubulwa.moftcinema.data.repository.MovieDataStore
import javax.inject.Inject

/**
 * Create an instance of a MovieDataStore
 */
open class MovieDataStoreFactory @Inject constructor(
    private val movieRemoteDataStore: MovieRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): MovieDataStore {
//        if (isCached) {
//            return movieCacheDataStore
//        }
        return movieRemoteDataStore
    }

}