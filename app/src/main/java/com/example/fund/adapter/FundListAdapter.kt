package com.example.fund.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fund.R
import com.example.fund.sqlite.FundDto
import com.example.fund.util.DateUtil

/**
 *  Created by zhaomeng on 2019/7/4
 */

class FundListAdapter(private val context: Context, private var fundList: List<FundDto>) : BaseAdapter() {

    fun setData(list: List<FundDto>) {
        fundList = list
    }

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
            viewHolder.tvHu.text = String.format("%.2f亿", it.huNum)
            viewHolder.tvShen.text = String.format("%.2f亿", it.shenNum)
            viewHolder.tvTotal.text = String.format("%.2f亿", it.totalNum)
            viewHolder.tvDeal.text = String.format("%.0f元", it.dealNum)
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