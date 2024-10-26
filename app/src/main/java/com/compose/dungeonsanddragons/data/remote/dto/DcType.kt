package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DcType(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("index")
	val index: String,

	@field:SerializedName("url")
	val url: String
)