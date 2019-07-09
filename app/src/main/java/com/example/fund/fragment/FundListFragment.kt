package com.example.fund.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.example.fund.adapter.FundListAdapter
import com.example.fund.R
import com.example.fund.interf.IActivityResult
import com.example.fund.presenter.FundPresenter
import com.example.fund.sqlite.FundDto

/**
 *  Created by zhaomeng on 2019/7/2
 */
class FundListFragment : Fragment(), IActivityResult {

    private lateinit var foreignCapitalNum: TextView
    private lateinit var fundNum: TextView
    private lateinit var differenceValueNum: TextView
    private lateinit var fundListView: ListView
    private lateinit var adapter: FundListAdapter

    private val presenter: FundPresenter? by lazy {
        if (context != null) {
            FundPresenter(context!!)
        } else {
            null
        }
    }

    private val funList: List<FundDto>?
        get() {
            return presenter?.getAllFun()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_fund_list, container, false)
        initView(rootView)
        return rootView
    }

    private fun initView(rootView: View) {
        if (context == null || funList == null) return

        fundListView = rootView.findViewById(R.id.fund_list)
        adapter = FundListAdapter(context!!, funList!!)
        fundListView.adapter = adapter
        foreignCapitalNum = rootView.findViewById(R.id.foreign_capital_num)
        fundNum = rootView.findViewById(R.id.fund_num)
        differenceValueNum = rootView.findViewById(R.id.difference_value_num)
        setText()
    }

    override fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        refresh()
    }

    private fun refresh() {
        refreshList()
        setText()
    }

    private fun refreshList() {
        adapter.setData(funList ?: emptyList())
        adapter.notifyDataSetChanged()
    }

    private fun setText() {
        foreignCapitalNum.text = String.format("%.2f亿", presenter?.getTotalForeignCapitalNum())
        fundNum.text = String.format("%.2f元", presenter?.getTotalFundNum())
        differenceValueNum.text = String.format("%.2f", presenter?.getDiffNum())
    }
}