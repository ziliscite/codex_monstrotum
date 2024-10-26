package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArmorClassItem(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("value")
	val value: Int
)