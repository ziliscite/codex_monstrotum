package com.compose.dungeonsanddragons.presentation.detail

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.EmptyScreen
import com.compose.dungeonsanddragons.presentation.common.MonsterImage
import com.compose.dungeonsanddragons.presentation.detail.components.DetailShimmerEffect
import com.compose.dungeonsanddragons.presentation.detail.components.DetailTopBar
import com.compose.dungeonsanddragons.presentation.detail.components.MonsterProfile
import com.compose.dungeonsanddragons.presentation.detail.components.MonsterStats
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
        Column(
            modifier = modifier
                .padding(horizontal = Dimens.smallPaddingOne)
                .statusBarsPadding()
                .windowInsetsPadding(insets = WindowInsets.safeDrawing)
                .verticalScroll(rememberScrollState()),
        ) {
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

            // Monster name and affiliation
            MonsterProfile(modifier = Modifier.padding(horizontal = Dimens.extraSmallPadding), monster = monster)

            Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))

            // Stats section
            MonsterStats(modifier = Modifier.padding(horizontal = Dimens.extraSmallPadding), monster = monster)
//            MonsterStats(modifier = Modifier.padding(horizontal = Dimens.extraSmallPadding), monster = monster)
//            MonsterStats(modifier = Modifier.padding(horizontal = Dimens.extraSmallPadding), monster = monster)
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
