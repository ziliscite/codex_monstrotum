package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Speed(

	@field:SerializedName("climb")
	val climb: String,

	@field:SerializedName("fly")
	val fly: String,

	@field:SerializedName("burrow")
	val burrow: String,

	@field:SerializedName("walk")
	val walk: String,

	@field:SerializedName("swim")
	val swim: String
)