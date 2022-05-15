package com.interactivestandard.test.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.interactivestandard.test.data.Repository
import com.interactivestandard.test.data.RepositoryImpl
import com.interactivestandard.test.data.response.CoordinatePoint
import com.interactivestandard.test.presentation.Screens.Error
import com.interactivestandard.test.presentation.view.DataPoint

class DetailsViewModel(
    private val repository: Repository,
    private val router: Router
    ) : ViewModel() {

    private val _pointsLiveData = MutableLiveData<List<DataPoint>>()
    val pointsLiveData: LiveData<List<DataPoint>> = _pointsLiveData

    private val _errorLiveData = MutableLiveData<Unit>()
    val errorLiveData: LiveData<Unit> = _errorLiveData

    @SuppressLint("CheckResult")
    fun setCount(count: Int) {
        repository.getPoints(count)
            .subscribe({
                _pointsLiveData.value = it.points.toDataPoint()
            }, {
                _errorLiveData.value = Unit
            })
    }

    fun errorProcess() {
        router.replaceScreen(Error())
    }
}

private fun List<CoordinatePoint>.toDataPoint(): List<DataPoint> {
    return this.map { DataPoint(it.x, it.y) }.sortedBy { it.xVal }
}
