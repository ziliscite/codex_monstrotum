package com.compose.dungeonsanddragons.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import com.compose.dungeonsanddragons.util.Dimens
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme
import kotlinx.coroutines.launch

@Composable
fun CodexSnackbar(
    modifier: Modifier = Modifier,
    message: String,
    onRefreshClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) { scope.launch {
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Refresh",
            duration = SnackbarDuration.Indefinite
        )
    }}

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier.padding(Dimens.extraSmallPadding),
        snackbar = { snackbarData ->
            Snackbar(
                action = {
                    TextButton(onClick = {
                        onRefreshClick()
                        snackbarData.dismiss()
                    }) {
                        Text(
                            color = MaterialTheme.colorScheme.onBackground,
                            text = snackbarData.visuals.actionLabel ?: "",
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    }
                }
            ) {
                Text(
                    text = snackbarData.visuals.message,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun RefreshableSnackbarPreview() {
    DungeonsAndDragonsTheme {
        var showSnackbar by remember {
            mutableStateOf(true)
        }

        if (showSnackbar) {
            CodexSnackbar(
                message = "Something went wrong.",
                onRefreshClick = {
                    // Perform your refresh action here
                    showSnackbar = false
                }
            )
        }
    }
}