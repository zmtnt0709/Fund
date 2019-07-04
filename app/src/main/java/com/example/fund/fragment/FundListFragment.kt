package com.example.fund.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.fund.FundListAdapter
import com.example.fund.R
import com.example.fund.presenter.FundPresenter
import com.example.fund.sqlite.FundDto

/**
 *  Created by zhaomeng on 2019/7/2
 */
class FundListFragment : Fragment() {

    private val presenter: FundPresenter? by lazy {
        if (context != null) {
            FundPresenter(context!!)
        } else {
            null
        }
    }

    val funList: List<FundDto>? by lazy {
        presenter?.getAllFun()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_fund_list, container, false)
        initView(rootView)
        return rootView
    }

    private fun initView(rootView: View) {
        if (context == null || funList == null) return

        val fundListView: ListView = rootView.findViewById(R.id.fund_list)
        val adapter = FundListAdapter(context!!, funList!!)
        fundListView.adapter = adapter
    }
}