package com.csibtn.codapizza.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csibtn.codapizza.model.Topping
import com.csibtn.codapizza.model.ToppingPlacement

@Preview
@Composable
private fun ToppingCallPreview() {
    ToppingCell(topping = Topping.Pepperoni,
        placement = null,
        onClickTopping = {})
}

@Preview
@Composable
private fun ToppingCallWithPlacementPreview() {
    ToppingCell(topping = Topping.Pepperoni,
        placement = ToppingPlacement.Right,
        onClickTopping = {})
}

@Composable
fun ToppingCell(
    topping: Topping,
    placement: ToppingPlacement?,
    onClickTopping: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClickTopping() }
            .padding(vertical = 4.dp, horizontal = 16.dp)

    )
    {
        Checkbox(checked = (placement != null), onCheckedChange = {
            onClickTopping()
        })

        Column(
            modifier = Modifier
                .weight(1f, fill = true)
                .padding(start = 4.dp)
        ) {
            Text(
                text = stringResource(id = topping.toppingName),
                style = MaterialTheme.typography.body1
            )
            if (placement != null) {
                Text(
                    text = stringResource(id = placement.label),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}