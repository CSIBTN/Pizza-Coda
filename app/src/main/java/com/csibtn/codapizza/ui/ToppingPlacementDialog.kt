package com.csibtn.codapizza.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.csibtn.codapizza.R
import com.csibtn.codapizza.model.Topping
import com.csibtn.codapizza.model.ToppingPlacement

@Composable
fun ToppingPlacementDialog(
    onDismissRequest: () -> Unit,
    topping: Topping,
    onSetToppingPlacement: (placement: ToppingPlacement?) -> Unit,
) {
    Dialog(onDismissRequest) {
        Card {
            Column {
                val toppingName = topping.name
                Text(
                    text = stringResource(id = R.string.placement_prompt, toppingName),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
                ToppingPlacement.values().forEach { placement ->
                    ToppingPlacementOption(
                        placementName = placement.label,
                        onClick = {
                            onSetToppingPlacement(placement)
                            onDismissRequest()
                        },
                    )
                }
                ToppingPlacementOption(
                    placementName = R.string.placement_none,
                    onClick = {
                        onSetToppingPlacement(null)
                        onDismissRequest()
                    },
                )
            }
        }
    }
}

@Composable
private fun ToppingPlacementOption(
    @StringRes placementName: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = placementName),
            modifier = modifier.padding(8.dp)
        )
    }
}