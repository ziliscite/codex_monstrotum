package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Usage(

	@field:SerializedName("times")
	val times: Int,

	@field:SerializedName("rest_types")
	val restTypes: List<Any>,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("min_value")
	val minValue: Int,

	@field:SerializedName("dice")
	val dice: String
)