package com.example.fund.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.sql.SQLException

/**
 *  Created by zhaomeng on 2019/7/4
 */
class FundSQLiteOpenHelper(context: Context) :
    OrmLiteSqliteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION) {

    companion object {
        private const val DATA_BASE_NAME = "FundInfo"
        private const val DATA_BASE_VERSION = 1
    }

    private var fundDao: FundDao? = null

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        try {
            TableUtils.createTable<FundDto>(connectionSource, FundDto::class.java)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    override fun onUpgrade(
        database: SQLiteDatabase,
        connectionSource: ConnectionSource,
        oldVersion: Int,
        newVersion: Int
    ) {

    }

    @Throws(SQLException::class)
    fun getFundDao(): FundDao? {
        if (fundDao == null) {
            fundDao = getDao<FundDao, FundDto>(FundDto::class.java)
        }
        return fundDao
    }
}