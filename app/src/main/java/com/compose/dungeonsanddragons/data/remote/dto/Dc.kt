package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Dc(

	@field:SerializedName("success_type")
	val successType: String,

	@field:SerializedName("dc_value")
	val dcValue: Int,

	@field:SerializedName("dc_type")
	val dcType: DcType
)