package com.example.fund.interf

import android.content.Intent

/**
 *  Created by zhaomeng on 2019/7/9
 */
interface IActivityResult {
    fun activityResult(requestCode: Int, resultCode: Int, data: Intent?)
}