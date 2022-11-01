package com.csibtn.codapizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.csibtn.codapizza.ui.AppTheme
import com.csibtn.codapizza.ui.PizzaBuildingScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                PizzaBuildingScreen()
            }
        }
    }


}