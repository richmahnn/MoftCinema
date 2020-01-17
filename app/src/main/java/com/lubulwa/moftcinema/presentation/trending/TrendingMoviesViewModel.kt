package com.lubulwa.moftcinema.presentation.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lubulwa.moftcinema.domain.interactor.GetMovies
import com.lubulwa.moftcinema.presentation.resource.MoftResource
import com.lubulwa.moftcinema.presentation.resource.ResourceState
import com.lubulwa.moftcinema.remote.model.MoftMovie
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TrendingMoviesViewModel @Inject constructor(private val getMovies: GetMovies) : ViewModel() {

    private val moviesLiveData = MutableLiveData<MoftResource<List<MoftMovie>>>()
    private var disposable: Disposable? = null

    fun observeTrendingMovies(): LiveData<MoftResource<List<MoftMovie>>> {
        return moviesLiveData
    }

    fun fetchMovies() {
        moviesLiveData.postValue(MoftResource(ResourceState.LOADING, null, null))

        disposable = getMovies.execute().subscribe({
            moviesLiveData.postValue(MoftResource(ResourceState.SUCCESS, it, null))
        }, {
            moviesLiveData.postValue(MoftResource(ResourceState.ERROR, null, it.localizedMessage))
        })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

}
