package com.interactivestandard.test.data

import com.interactivestandard.test.data.response.PointsResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface Repository {
    fun getPoints(count: Int): Single<PointsResponse>
}

class RepositoryImpl(private val api: InteractiveStandardApi) : Repository {

    override fun getPoints(count: Int) =
        api.getPointsResponse(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}