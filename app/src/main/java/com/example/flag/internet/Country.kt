package com.example.flag.internet


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllData(val error: Boolean, val msg: String, val data: List<DataItem>)
data class DataItem(val name: String, var flag: String)