package com.example.fund.sqlite

import com.example.fund.model.FundModel
import com.example.fund.util.DateUtil
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 *  Created by zhaomeng on 2019/7/4
 */
@DatabaseTable(daoClass = FundDao::class)
class FundDto() {
    @DatabaseField(id = true)
    var date: Long = 0          //操作日期
    @DatabaseField
    var huNum: Float = 0F       //外资上证交易额
    @DatabaseField
    var shenNum: Float = 0F     //外资深证交易额
    @DatabaseField
    var totalNum: Float = 0F    //外资总交易额
    @DatabaseField
    var dealNum: Float = 0F     //基金交易额

    constructor(fundModel: FundModel) : this() {
        fundModel.fundDatePicker?.let {
            date = DateUtil.toDateLong(it)
        }
        huNum = fundModel.huNum
        shenNum = fundModel.shenNum
        totalNum = fundModel.totalNum
        dealNum = fundModel.fundDealNum
    }
}