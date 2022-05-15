package com.interactivestandard.test.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.interactivestandard.test.R

class DetailsAdapter: RecyclerView.Adapter<DetailsAdapter.DetailsVH>() {

    private var dataList: List<TableItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsVH {
        return when (viewType) {
            TITLE -> DetailsTitleVH(inflate(parent, R.layout.item_title))
            else -> DetailsPointVH(inflate(parent, R.layout.item_point))
        }
    }

    private fun inflate(parent: ViewGroup, itemId: Int) = LayoutInflater.from(parent.context)
        .inflate(itemId, parent, false)

    override fun onBindViewHolder(holder: DetailsVH, position: Int) {
        when (holder) {
            is DetailsPointVH -> {
                holder.valueX.text = dataList[position].leftCell
                holder.valueY.text = dataList[position].rightCell
            }
            is DetailsTitleVH -> {
                holder.left.text = dataList[position].leftCell
                holder.right.text = dataList[position].rightCell
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> TITLE
            else -> POINT
        }
    }

    fun setData(data: List<TableItem>) {
        dataList = data
        notifyDataSetChanged()
    }

    companion object {
        const val TITLE = 0
        const val POINT = 1
    }

    abstract class DetailsVH(itemView: View) : RecyclerView.ViewHolder(itemView)
    class DetailsTitleVH(itemView: View) : DetailsVH(itemView) {
        val left: TextView = itemView.findViewById(R.id.left_title)
        val right: TextView = itemView.findViewById(R.id.right_title)
    }
    class DetailsPointVH(itemView: View) : DetailsVH(itemView) {
        val valueX: TextView = itemView.findViewById(R.id.value_x)
        val valueY: TextView = itemView.findViewById(R.id.value_y)
    }
}