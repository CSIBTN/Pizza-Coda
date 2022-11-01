package com.csibtn.codapizza.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csibtn.codapizza.R
import com.csibtn.codapizza.model.Pizza
import com.csibtn.codapizza.model.Topping
import java.text.NumberFormat


@Preview
@Composable
fun PizzaBuildingScreen(modifier: Modifier = Modifier) {
    var pizza by rememberSaveable {
        mutableStateOf(Pizza())
    }
    Column(modifier = modifier) {
        ToppingsList(
            pizza = pizza,
            onEditPizza = { pizza = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )
        OrderButton(
            pizza = pizza,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}


@Composable
private fun ToppingsList(
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit,
    modifier: Modifier = Modifier
) {

    var toppingBeingAdded by rememberSaveable {
        mutableStateOf<Topping?>(null)
    }
    toppingBeingAdded?.let { topping ->
        ToppingPlacementDialog(
            onDismissRequest = {
                toppingBeingAdded = null
            }, topping = topping,
            onSetToppingPlacement = { placement ->
                onEditPizza(pizza.withTopping(topping, placement))
            }
        )
    }


    LazyColumn(modifier = modifier) {
        item {
            PizzaHeroImage(
                pizza = pizza,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(Topping.values()) { topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    toppingBeingAdded = topping
                })
        }
    }
}

@Composable
private fun OrderButton(
    pizza: Pizza,
    modifier: Modifier = Modifier
) {
    Button(modifier = modifier, onClick = { /*TODO*/ }) {
        val currencyFormatter = rememberSaveable {
            NumberFormat.getCurrencyInstance()
        }

        val price = currencyFormatter.format(pizza.price)
        Text(text = stringResource(id = R.string.place_order_button, price).uppercase())
    }

}