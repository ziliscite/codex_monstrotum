package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LegendaryActionsItem(

	@field:SerializedName("damage")
	val damage: List<DamageItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("dc")
	val dc: Dc
)