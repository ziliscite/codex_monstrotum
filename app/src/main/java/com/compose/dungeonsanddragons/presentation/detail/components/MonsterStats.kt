package com.compose.dungeonsanddragons.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.dungeonsanddragons.R
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.dummyMonster
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme

@Composable
fun MonsterStat(
    title: String,
    value: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    val progress = value / 30f // Since the range is 0-30

    Row(
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically // Aligns text and bar vertically centered
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .widthIn(min = 50.dp)
                .padding(end = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.smallPaddingOne)
                .clip(RoundedCornerShape(12.dp))
                .background(color.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = progress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color)
            )
        }
    }
}

@Composable
fun MonsterStats(
    modifier: Modifier = Modifier,
    monster: MonsterEntity
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.extraSmallPadding)
    ) {
        MonsterStat(
            title = "STR",
            value = monster.strength,
            color = colorResource(id = R.color.red_500)
        )
        MonsterStat(
            title = "DEX",
            value = monster.dexterity,
            color = colorResource(id = R.color.blue_200)
        )
        MonsterStat(
            title = "CON",
            value = monster.constitution,
            color = MaterialTheme.colorScheme.tertiary
        )
        MonsterStat(
            title = "INT",
            value = monster.intelligence,
            color = colorResource(id = R.color.teal_200)
        )
        MonsterStat(
            title = "WIS",
            value = monster.wisdom,
            color = colorResource(id = R.color.amber_500)
        )
        MonsterStat(
            title = "CHA",
            value = monster.charisma,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MonsterStatsPreview() {
//    DungeonsAndDragonsTheme {
//        MonsterStats(monster = dummyMonster)
//    }
//}
