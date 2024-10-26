package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ActionsItem(

	@field:SerializedName("damage")
	val damage: List<DamageItem>,

	@field:SerializedName("usage")
	val usage: Usage,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("actions")
	val actions: List<ActionsItem>,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("dc")
	val dc: Dc,

	@field:SerializedName("attack_bonus")
	val attackBonus: Int,

	@field:SerializedName("multiattack_type")
	val multiattackType: String,

	@field:SerializedName("action_name")
	val actionName: String,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("type")
	val type: String
)