package com.kotlinmvp.net

/**
 * easin/11/16.
 * 封装返回的数据
 */
class BaseResponse<T>(val code :Int,
                      val msg:String,
                      val data:T)