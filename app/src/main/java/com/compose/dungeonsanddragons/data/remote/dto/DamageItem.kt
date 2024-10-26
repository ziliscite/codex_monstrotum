package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DamageItem(

	@field:SerializedName("damage_type")
	val damageType: DamageType,

	@field:SerializedName("damage_dice")
	val damageDice: String
)