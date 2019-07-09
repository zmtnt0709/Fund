package com.example.fund.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.*
import com.example.fund.R
import com.example.fund.model.FundModel
import com.example.fund.presenter.FundPresenter
import com.example.fund.sqlite.FundDto
import com.example.fund.util.DateUtil
import kotlinx.android.synthetic.main.activity_add.*
import java.lang.NumberFormatException

/**
 *  Created by zhaomeng on 2019/6/21
 */
class AddActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var fundModel: FundModel
    private val foreignCapitalTextWatcher by lazy {
        ForeignCapitalTextWatcher()
    }
    private val presenter: FundPresenter by lazy {
        FundPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initData()
        initView()
    }

    private fun initData() {
        fundModel = FundModel()
    }

    private fun initView() {
        tv_select_data.setOnClickListener(this)
        btn_save.setOnClickListener(this)
        tv_hu_num.addTextChangedListener(foreignCapitalTextWatcher)
        tv_shen_num.addTextChangedListener(foreignCapitalTextWatcher)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_select_data -> selectDate()
            R.id.btn_save -> saveFundData()
        }
    }

    private fun selectDate() {
        val layoutView = LayoutInflater.from(this).inflate(R.layout.popup_window_set_data, null)
        layoutView.isFocusable = true
        layoutView.isFocusableInTouchMode = true
        val datePicker = layoutView.findViewById(R.id.read_pages_date) as DatePicker
        val cancel = layoutView.findViewById(R.id.cancel_action) as Button
        val add = layoutView.findViewById(R.id.add_action) as Button
        val pop = PopupWindow(
            layoutView,
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, false
        )
        pop.isFocusable = true
        layoutView.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                pop.dismiss()
                return@OnKeyListener true
            }
            false
        })
        add.setOnClickListener {
            tv_select_data.text = DateUtil.toDateString(datePicker)
            fundModel.fundDatePicker = datePicker
            pop.dismiss()
        }
        cancel.setOnClickListener { pop.dismiss() }
        pop.showAtLocation(layoutView, Gravity.CENTER, 0, 0)
    }

    private fun saveFundData() {
        if (fundModel.fundDatePicker == null) {
            Toast.makeText(this, "请输入日期", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(tv_hu_num.text)) {
            Toast.makeText(this, "请输入外资上证买卖金额", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(tv_shen_num.text)) {
            Toast.makeText(this, "请输入外资深证买卖金额", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(tv_deal_num.text)) {
            Toast.makeText(this, "请输入基金买卖金额", Toast.LENGTH_LONG).show()
            return
        }

        fundModel.huNum = tv_hu_num.text.toString().toFloat()
        fundModel.shenNum = tv_shen_num.text.toString().toFloat()
        fundModel.totalNum = tv_total_num.text.toString().toFloat()
        fundModel.fundDealNum = tv_deal_num.text.toString().toFloat()

        val fundDto = FundDto(fundModel)
        presenter.saveDto(fundDto)
        setResult(Activity.RESULT_OK)
        finish()
    }

    inner class ForeignCapitalTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (TextUtils.isEmpty(tv_hu_num.text) && TextUtils.isEmpty(tv_shen_num.text)) {
                tv_total_num.text = ""
                return
            }

            try {
                val huNum = if (TextUtils.isEmpty(tv_hu_num.text)) 0F else tv_hu_num.text.toString().toFloat()
                val ShenNum = if (TextUtils.isEmpty(tv_shen_num.text)) 0F else tv_shen_num.text.toString().toFloat()
                tv_total_num.text = String.format("%.2f", huNum + ShenNum)
            } catch (ignore: NumberFormatException) {

            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    }
}