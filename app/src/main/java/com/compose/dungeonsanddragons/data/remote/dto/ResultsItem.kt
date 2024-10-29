package com.compose.dungeonsanddragons.data.remote.dto

import com.compose.dungeonsanddragons.util.MonsterItem
import com.google.gson.annotations.SerializedName

data class ResultsItem(

	@field:SerializedName("name")
	override val name: String,

	@field:SerializedName("index")
	override val index: String,

	@field:SerializedName("url")
	val url: String
) : MonsterItem