package com.compose.dungeonsanddragons.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Monster(

	@field:SerializedName("hit_points")
	val hitPoints: Int,

	@field:SerializedName("constitution")
	val constitution: Int,

	@field:SerializedName("strength")
	val strength: Int,

	@field:SerializedName("condition_immunities")
	val conditionImmunities: List<ConditionImmunitiesItem>,

	@field:SerializedName("senses")
	val senses: Senses,

	@field:SerializedName("challenge_rating")
	val challengeRating: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("legendary_actions")
	val legendaryActions: List<LegendaryActionsItem>,

	@field:SerializedName("speed")
	val speed: Speed,

	@field:SerializedName("charisma")
	val charisma: Int,

	@field:SerializedName("wisdom")
	val wisdom: Int,

	@field:SerializedName("damage_resistances")
	val damageResistances: List<String>,

	@field:SerializedName("armor_class")
	val armorClass: List<ArmorClassItem>,

	@field:SerializedName("subtype")
	val subtype: String,

	@field:SerializedName("proficiencies")
	val proficiencies: List<ProficienciesItem>,

	@field:SerializedName("hit_dice")
	val hitDice: String,

	@field:SerializedName("proficiency_bonus")
	val proficiencyBonus: Int,

	@field:SerializedName("special_abilities")
	val specialAbilities: List<SpecialAbilitiesItem>,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("languages")
	val languages: String,

	@field:SerializedName("index")
	val index: String,

	@field:SerializedName("damage_immunities")
	val damageImmunities: List<String>,

	@field:SerializedName("damage_vulnerabilities")
	val damageVulnerabilities: List<String>,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("intelligence")
	val intelligence: Int,

	@field:SerializedName("dexterity")
	val dexterity: Int,

	@field:SerializedName("hit_points_roll")
	val hitPointsRoll: String,

	@field:SerializedName("size")
	val size: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("xp")
	val xp: Int,

	@field:SerializedName("alignment")
	val alignment: String,

	@field:SerializedName("actions")
	val actions: List<ActionsItem>
)