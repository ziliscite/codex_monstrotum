package com.compose.dungeonsanddragons.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.data.remote.dto.ResultsItem
import com.compose.dungeonsanddragons.data.remote.dto.Senses
import com.compose.dungeonsanddragons.data.remote.dto.Speed
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.MonsterImage

@Composable
fun MonsterCard(
    monster: ResultsItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(Dimens.monsterCardSize.first, Dimens.monsterCardSize.second)
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() }
    ) {
        MonsterImage(
            modifier = Modifier.fillMaxSize(),
            index = monster.index,
            context = context
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.monsterCardSize.second / 5)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f)
                        )
                    )
                )
        ) {
            Text(
                text = monster.name,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                maxLines = 1
            )
        }
    }
}

val dummyMonster = Monster(
    hitPoints = 100,
    constitution = 15,
    strength = 18,
    conditionImmunities = emptyList(),
    senses = Senses(
        tremorsense = "60 ft.",
        darkvision = "120 ft.",
        blindsight = "30 ft.",
        passivePerception = 14,
        truesight = "10 ft."
    ),
    challengeRating = 5,
    type = "Dragon",
    legendaryActions = emptyList(),
    speed = Speed(
        climb = "30 ft.",
        fly = "80 ft.",
        burrow = "20 ft.",
        walk = "40 ft.",
        swim = "50 ft."
    ),
    charisma = 10,
    wisdom = 12,
    damageResistances = listOf("fire", "ice"),
    armorClass = emptyList(),
    subtype = "None",
    proficiencies = emptyList(),
    hitDice = "12d8",
    proficiencyBonus = 2,
    specialAbilities = emptyList(),
    image = "images/monsters/ancient-gold-dragon.png",
    languages = "Common, Draconic",
    index = "monster_01",
    damageImmunities = listOf("poison"),
    damageVulnerabilities = listOf("lightning"),
    url = "https://example.com/monster",
    intelligence = 14,
    dexterity = 12,
    hitPointsRoll = "12d8 + 24",
    size = "Large",
    name = "Ancient Gold Dragon",
    xp = 1800,
    alignment = "Chaotic Evil",
    actions = emptyList()
)

val dummyListItem = ResultsItem(
    name = "Ancient Gold Dragon",
    url = "https://example.com/monster",
    index = "ancient-gold-dragon",
)

@Preview
@Composable
fun MonsterCardPreview() {
    MonsterCard(
        monster = dummyListItem,
    ) {}
}
