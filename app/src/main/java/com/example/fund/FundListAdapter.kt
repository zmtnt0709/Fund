package com.example.fund

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fund.sqlite.FundDto
import com.example.fund.util.DateUtil
import java.util.zip.Inflater

/**
 *  Created by zhaomeng on 2019/7/4
 */

class FundListAdapter(private val context: Context, private val fundList: List<FundDto>) : BaseAdapter() {

    override fun getItem(position: Int): FundDto? {
        if (position < count) {
            return fundList[position]
        }
        return null
    }

    override fun getItemId(position: Int): Long {
        if (position < count) {
            return fundList[position].date
        }
        return 0L
    }

    override fun getCount(): Int {
        return fundList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        var view: View? = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_fund, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        getItem(position)?.let {
            viewHolder.tvDate.text = DateUtil.formatData(it.date, "yyyy.MM.dd")
            viewHolder.tvHu.text = "${it.huNum}亿"
            viewHolder.tvShen.text = "${it.shenNum}亿"
            viewHolder.tvTotal.text = "${it.totalNum}亿"
            viewHolder.tvDeal.text = "${it.dealNum}元"
        }

        return view!!
    }

    class ViewHolder(root: View) {
        val tvDate: TextView = root.findViewById(R.id.tv_date)
        val tvHu: TextView = root.findViewById(R.id.tv_hu)
        val tvShen: TextView = root.findViewById(R.id.tv_shen)
        val tvTotal: TextView = root.findViewById(R.id.tv_total)
        val tvDeal: TextView = root.findViewById(R.id.tv_deal)
    }
}