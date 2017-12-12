package com.quaner.kwxnews.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.quaner.kwxnews.R
import com.quaner.kwxnews.ui.entity.GankEntity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by wenxin on 2017/12/7.
 */
open class HomeAdapter(private var data: List<GankEntity>, private var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            return HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_header, parent, false))
        }
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val gankEntity = data!![position]
        if (getItemViewType(position) == TYPE_ITEM) {
            var holder: ViewHolder = holder as ViewHolder
            holder.tv_name.text = gankEntity.who ?: "佚名"
            holder.tv_content.text = gankEntity.desc
            holder.itemView.setOnClickListener { v ->
                if (listener != null) {
                    listener!!.onItemClick(data[position], position)
                }
            }
        } else {
            var holder: HeaderViewHolder = holder as HeaderViewHolder
            holder.tv_time.text = SimpleDateFormat("yyyy-MM-dd").format(Date())
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_name: TextView = itemView.findViewById(R.id.tv_name)
        var tv_content: TextView = itemView.findViewById(R.id.tv_content)
    }


    class HeaderViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_time: TextView = itemView.findViewById(R.id.tv_time)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_ITEM
    }

    interface OnItemClickListener {
        fun onItemClick(data: GankEntity, position: Int)
    }

    var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}