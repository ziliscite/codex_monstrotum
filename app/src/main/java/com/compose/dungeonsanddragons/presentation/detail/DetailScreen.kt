package com.compose.dungeonsanddragons.presentation.detail

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.presentation.Dimens
import com.compose.dungeonsanddragons.presentation.common.EmptyScreen
import com.compose.dungeonsanddragons.presentation.common.MonsterImage
import com.compose.dungeonsanddragons.presentation.detail.components.DetailShimmerEffect
import com.compose.dungeonsanddragons.presentation.detail.components.DetailTopBar
import com.compose.dungeonsanddragons.presentation.detail.components.MonsterDetail
import com.compose.dungeonsanddragons.util.Constants
import com.compose.dungeonsanddragons.util.MonsterResult

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    index: String,
    state: DetailState,
    navigateUp: () -> Unit,
    event: (DetailEvent) -> Unit,
) {
    val context = LocalContext.current

    val monster = isMonsterReady(monster = state.monster)

    if (monster == null) {
        event(DetailEvent.GetMonster(index = index))
    } else {
        Column(modifier = modifier.padding(Dimens.smallPaddingOne)) {
            DetailTopBar(
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, Constants.BASE_URL + monster.index)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onBookmarkClick = {
                    event(DetailEvent.SaveArticle)
                },
                onBackClick = navigateUp
            )

            // Image section at the top
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(Dimens.extraSmallPadding))
            ) {
                MonsterImage(
                    modifier = Modifier.fillMaxSize(),
                    index = monster.index,
                    context = context,
                )
            }

            Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))

            // Monster Name and Alignment
            Text(
                text = monster.name,
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = "${monster.size} ${monster.type}, ${monster.alignment}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))

            // Armor Class, Hit Points, and Speed
            Text(
                text = "Armor Class: ${monster.armorClass}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Hit Points: ${monster.hitPoints} (${monster.hitDice})",
                style = MaterialTheme.typography.bodyMedium
            )

//            Text(
//                text = "Speed: ${monster.speed}",
//                style = MaterialTheme.typography.bodyMedium
//            )

            Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))

            // Stats section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "STR: ${monster.strength}", style = MaterialTheme.typography.bodySmall)
                Text(text = "DEX: ${monster.dexterity}", style = MaterialTheme.typography.bodySmall)
                Text(text = "CON: ${monster.constitution}", style = MaterialTheme.typography.bodySmall)
                Text(text = "INT: ${monster.intelligence}", style = MaterialTheme.typography.bodySmall)
                Text(text = "WIS: ${monster.wisdom}", style = MaterialTheme.typography.bodySmall)
                Text(text = "CHA: ${monster.charisma}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun isMonsterReady(monster: MonsterResult<Monster>): Monster? {
    return when(monster) {
        is MonsterResult.Success -> {
            monster.data
        }
        is MonsterResult.Failed -> {
            EmptyScreen(errorString = monster.error)
            null
        }
        is MonsterResult.Loading -> {
            DetailShimmerEffect()
            null
        }
    }
}
