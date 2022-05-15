package com.interactivestandard.test.presentation.fragment

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.drawToBitmap
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.interactivestandard.test.R
import com.interactivestandard.test.presentation.adapter.DetailsAdapter
import com.interactivestandard.test.presentation.adapter.TableItem
import com.interactivestandard.test.presentation.view.DataPoint
import com.interactivestandard.test.presentation.view.GraphView
import com.interactivestandard.test.presentation.viewmodel.DetailsViewModel
import com.interactivestandard.test.util.write
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.io.File

class DetailsFragment : Fragment(R.layout.fragment_details), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()

    private val viewModel: DetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val save = requireView().findViewById<Button>(R.id.save)
        val cl = requireView().findViewById<CoordinatorLayout>(R.id.cl)
        val progressBar = requireView().findViewById<ContentLoadingProgressBar>(R.id.progress_bar)
        val graphView = requireView().findViewById<GraphView>(R.id.graph)
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler)
        val adapter = DetailsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val count = arguments?.getInt(COUNT_ARGUMENT) ?: 0
        viewModel.setCount(count)
        viewModel.pointsLiveData.observe(viewLifecycleOwner, {
            graphView.setData(it)
            fillList(it, adapter)
            save.isVisible = true
            progressBar.hide()
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            viewModel.errorProcess()
            progressBar.hide()
        })
        save.setOnClickListener {
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            File(dir, "graph_${System.currentTimeMillis()}.png").write(graphView.drawToBitmap())
            Snackbar.make(cl, R.string.graph_save_msg, Snackbar.LENGTH_SHORT).show()
        }
        onSaveInstanceState(Bundle())
    }

    private fun fillList(
        dataPointList: List<DataPoint>,
        adapter: DetailsAdapter
    ) {
        val mutableList = mutableListOf<TableItem>().apply {
            add(
                TableItem(
                    requireContext().getString(R.string.text_x),
                    requireContext().getString(R.string.text_y)
                )
            )
            dataPointList.forEach { dataPoint ->
                add(
                    TableItem(
                        dataPoint.xVal.toString(),
                        dataPoint.yVal.toString()
                    )
                )
            }
        }
        adapter.setData(mutableList.toList())
    }

    companion object {
        private const val COUNT_ARGUMENT = "count_argument"
        fun getInstance(count: Int) = DetailsFragment().apply {
            this.arguments = Bundle().apply {
                putInt(COUNT_ARGUMENT, count)
            }
        }
    }
}