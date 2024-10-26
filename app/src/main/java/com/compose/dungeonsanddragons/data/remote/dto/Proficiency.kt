package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Proficiency(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("index")
	val index: String,

	@field:SerializedName("url")
	val url: String
)