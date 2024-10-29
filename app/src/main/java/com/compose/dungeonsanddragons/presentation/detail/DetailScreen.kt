package com.compose.dungeonsanddragons.presentation.detail

import android.content.Context
import android.content.Intent
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
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

    LaunchedEffect(index) {
        event(DetailEvent.GetMonster(index = index))
    }

    when (val monsterResult = state.monster) {
        is MonsterResult.Success -> {
            val monster = monsterResult.data
            DetailContent(
                modifier = modifier,
                monster = monster,
                navigateUp = navigateUp,
                event = event,
                context = context
            )
        }
        is MonsterResult.Failed -> {
            EmptyScreen(errorString = monsterResult.error)
        }
        is MonsterResult.Loading -> {
            DetailShimmerEffect()
        }
    }
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    monster: Monster,
    navigateUp: () -> Unit,
    event: (DetailEvent) -> Unit,
    context: Context
) {
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
                card = false
            )
        }
        Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))

        // Monster name and affiliation
        MonsterProfile(modifier = Modifier.padding(horizontal = Dimens.extraSmallPadding), monster = monster)

        Spacer(modifier = Modifier.height(Dimens.smallPaddingOne))

        // Stats section
        MonsterStats(modifier = Modifier.padding(horizontal = Dimens.extraSmallPadding), monster = monster)
    }
}
