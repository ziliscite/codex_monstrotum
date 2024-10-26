package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Senses(

	@field:SerializedName("tremorsense")
	val tremorsense: String,

	@field:SerializedName("darkvision")
	val darkvision: String,

	@field:SerializedName("blindsight")
	val blindsight: String,

	@field:SerializedName("passive_perception")
	val passivePerception: Int,

	@field:SerializedName("truesight")
	val truesight: String
)