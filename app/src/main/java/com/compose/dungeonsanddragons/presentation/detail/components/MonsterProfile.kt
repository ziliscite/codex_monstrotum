package com.compose.dungeonsanddragons.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.dungeonsanddragons.data.local.dto.MonsterEntity
import com.compose.dungeonsanddragons.data.remote.dto.Monster
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.presentation.common.dummyMonster
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme
import java.util.Locale

@Composable
fun MonsterProfile(
    modifier: Modifier = Modifier,
    monster: MonsterEntity
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = monster.name,
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${monster.size} ${monster.type.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )

            VerticalDivider(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.padding(Dimens.extraSmallPadding).height(MaterialTheme.typography.bodyMedium.fontSize.value.dp)
            )

            Text(
                text = monster.alignment.split(" ").joinToString(" ") { s ->
                    s.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                    }
                },
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun MonsterProfilePreview() {
//    DungeonsAndDragonsTheme {
//        MonsterProfile(
//            monster = dummyMonster
//        )
//    }
//}
