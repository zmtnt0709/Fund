package com.example.fund.presenter

import android.content.Context
import android.widget.Toast
import com.example.fund.sqlite.FundDao
import com.example.fund.sqlite.FundDto
import com.example.fund.sqlite.FundSQLiteOpenHelper
import java.lang.Exception

/**
 *  Created by zhaomeng on 2019/7/4
 */

class FundPresenter(private val context: Context) {

    private val fundDao: FundDao?
        get() {
            return try {
                fundSQLiteOpenHelper.getFundDao()
            } catch (e: Exception) {
                null
            }
        }

    private val fundSQLiteOpenHelper by lazy {
        FundSQLiteOpenHelper(context)
    }

    fun isExist(date: Long): Boolean {
        fundDao?.let {
            return it.getBookById(date) != null
        }
        return false
    }

    fun saveDto(fundDto: FundDto): Boolean {
        fundDao?.let {
            val result = it.createOrUpdate(fundDto)
            if (result.isCreated) {
                Toast.makeText(context, "create success", Toast.LENGTH_LONG).show()
            } else if (result.isUpdated) {
                Toast.makeText(context, "update success", Toast.LENGTH_LONG).show()
            }
            return true
        }
        return false
    }

    fun getAllFun(): List<FundDto>? {
        fundDao?.let {
            return it.getAllBookList()
        }
        return null
    }
}