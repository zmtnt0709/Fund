package com.example.fund.sqlite

import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.DatabaseTableConfig
import java.sql.SQLException

/**
 *  Created by zhaomeng on 2019/7/4
 */

class FundDao : BaseDaoImpl<FundDto, String> {

    @Throws(SQLException::class)
    constructor(dataClass: Class<FundDto>) : super(dataClass)

    @Throws(SQLException::class)
    constructor(connectionSource: ConnectionSource, dataClass: Class<FundDto>) : super(connectionSource, dataClass)

    @Throws(SQLException::class)
    constructor(connectionSource: ConnectionSource, tableConfig: DatabaseTableConfig<FundDto>) : super(
        connectionSource,
        tableConfig
    )

    fun getAllBookList(): List<FundDto>? {
        var bookList: List<FundDto>? = null
        try {
            bookList = queryForAll()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return bookList
    }

    fun getBookById(date: Long): FundDto? {
        val builder = queryBuilder()
        try {
            builder.where().eq("id", date)
            return queryForFirst(builder.prepare())
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return null
    }
}