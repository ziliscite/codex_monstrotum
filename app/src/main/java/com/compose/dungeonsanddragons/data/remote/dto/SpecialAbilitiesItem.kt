package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SpecialAbilitiesItem(

	@field:SerializedName("usage")
	val usage: Usage,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("desc")
	val desc: String
)