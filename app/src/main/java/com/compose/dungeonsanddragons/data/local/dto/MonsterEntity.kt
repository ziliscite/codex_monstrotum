package com.compose.dungeonsanddragons.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.compose.dungeonsanddragons.util.MonsterItem

@Entity(tableName = "monsterentity")
data class MonsterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val index: String,

    val url: String,

    val size: String,
    override val name: String,
    val alignment: String,
    val type: String,

    val xp: Int,

    val hitPoints: Int,
    val hitPointsRoll: String,
    val hitDice: String,

    val languages: String,

    val charisma: Int,
    val wisdom: Int,
    val constitution: Int,
    val strength: Int,
    val intelligence: Int,
    val dexterity: Int,

    val damageImmunities: List<String>,
    val damageVulnerabilities: List<String>,
    val damageResistances: List<String>,
) : MonsterItem
