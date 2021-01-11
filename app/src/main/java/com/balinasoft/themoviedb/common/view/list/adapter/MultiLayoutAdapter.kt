package com.balinasoft.themoviedb.common.view.list.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.util.*

abstract class MultiLayoutAdapter : RecyclerView.Adapter<SimpleViewHolder<*>> {

    private val viewIdStartValue: Int = 0

    private val viewIdGenerator: ViewIdGenerator

    constructor(viewIdStartValue: Int) : super() {
        viewIdGenerator = ViewIdGenerator(viewIdStartValue)
    }

    constructor() : super() {
        viewIdGenerator = ViewIdGenerator(0)
    }

    protected val data = ArrayList<Any>()
    private val mCellInfoMap = Hashtable<Class<out Any>, CellInfo>()

    private class ViewIdGenerator(startValue: Int) {
        private var viewIdCount: Int = startValue
        fun generate(): Int {
            viewIdCount++
            return viewIdCount
        }
    }

    protected fun registerCell(
        objectClass: Class<out Any>,
        builder: (parent: ViewGroup) -> SimpleViewHolder<*>
    ): Int {

        val viewId = viewIdGenerator.generate()

        val generator = object : BindInterface {
            override fun createViewHolder(parent: ViewGroup): SimpleViewHolder<*> {
                return builder.invoke(parent)
            }
        }

        val cellInfo = CellInfo(generator, viewId)
        mCellInfoMap[objectClass] = cellInfo

        return viewId
    }

    interface BindInterface {
        fun createViewHolder(parent: ViewGroup): SimpleViewHolder<*>
        //fun onBindView(holder: RecyclerView.ViewHolder, obj: Any)
    }

    class CellInfo(
        val bindInterface: BindInterface,
        val layoutId: Int

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder<*> {

        val cellInfo = mCellInfoMap.entries.find { it -> it.value.layoutId == viewType }

        if (cellInfo != null) {
            return cellInfo.value.bindInterface.createViewHolder(parent)
        } else {

            val textView = TextView(parent.context)
            textView.text = "VIEW NOT EXIST"
            val o = object : SimpleViewHolder<Any>(textView) {

            }
            return o
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleViewHolder<*>, position: Int) {
        val item = getItemAt(position)
        (holder as SimpleViewHolder<Any>).onBindViewHolder(item)
    }

    protected fun getItemAt(position: Int): Any {
        return data.get(position)
    }


    protected fun getCellInfo(someModel: Any): CellInfo? {
        for (entry in mCellInfoMap.entries) {
            if (entry.key.isInstance(someModel))
                return entry.value
        }
        return null
    }

    override fun getItemViewType(position: Int): Int {
        return getCellInfo(data[position])?.layoutId ?: -1
    }

    fun update() {
        notifyItemRangeChanged(0, data.size)
    }

    fun onItemUpdated(any: Any) {
        val index = data.indexOf(any)
        notifyItemChanged(index)
    }
}
