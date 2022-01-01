package com.example.newstart.complex_recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 24,December,2021
 */
class CustomRecyclerViewItemDecorator(
    val context: Context,
    val headerHeight: Int,
    val isSticky: Boolean,
    val callback: SectionCallback
) : RecyclerView.ItemDecoration() {

     var headerView: View?=null
    lateinit var textTitle: TextView
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos=parent.getChildAdapterPosition(view)
        if (callback.isSectionHeader(post = pos)) {
            parent.adapter?.let {
                if(it.getItemViewType(pos)==0)
                    outRect.top = headerHeight

            }
        }
    }

    fun inflateHeader(rv: RecyclerView): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_header, rv, false)

        return view
    }

    override fun onDrawOver(c: Canvas, rv: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, rv, state)
        if (headerView == null) {
            headerView = inflateHeader(rv)
            textTitle = headerView!!.findViewById(R.id.header_text1)
            fixLayoutSize(headerView!!, rv)
        }
        var prevTitle = ""
        for (i in 0 until rv.childCount) {
            val child = rv.getChildAt(i)
            val childPos = rv.getChildAdapterPosition(child)
            val title = callback.getSectionHeaderName(post = childPos)
            if (prevTitle.equals(title, true) || callback.isSectionHeader(childPos)) {
                drawHeader(c, child, headerView!!)
                prevTitle = title
            }
        }
    }

    private fun drawHeader(c: Canvas, child: View?, headerView: View) {
        c.save()
        if (isSticky) {

            c.translate(0f, Math.max(0, child!!.top - headerView.height).toFloat())
        } else {
            c.translate(0f, (child!!.top - headerView.height).toFloat())

        }
        headerView.draw(c)
        c.restore()
    }

    private fun fixLayoutSize(headerView: View, parent: RecyclerView) {
        val width = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val height = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidth = ViewGroup.getChildMeasureSpec(
            width,
            parent.paddingLeft + parent.paddingRight,
            headerView.layoutParams.width
        )
        val childHeight = ViewGroup.getChildMeasureSpec(
            height,
            parent.top + parent.bottom,
            headerView.layoutParams.height
        )
        headerView.measure(childWidth, childHeight)
        headerView.layout(0, 0, headerView.width, headerView.height)

    }

    public interface SectionCallback {
        public fun isSectionHeader(post: Int): Boolean
        public fun getSectionHeaderName(post: Int): String
    }

}