package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProficienciesItem(

	@field:SerializedName("value")
	val value: Int,

	@field:SerializedName("proficiency")
	val proficiency: Proficiency
)