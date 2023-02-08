package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                ParentComposable()
            }
        }
    }
}

@Preview
@Composable
fun ParentComposable() {
    var value by rememberSaveable { mutableStateOf(1) }
    val imageResource = when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        TopComposable(value)
        MiddleComposable(imageResource, onClick = {value = (1..6).random()})
        BottomComposable(value)
    }
}

@Composable
fun MiddleComposable(imageResource: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(R.string.dice_image)
        )
        Button(onClick = onClick, modifier = Modifier.width(100.dp)) {
            Text(stringResource(R.string.roll), fontSize = 25.sp)
        }
    }
}

@Composable
fun TopComposable(value: Int) {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        Text(text = "You have rolled $value", fontSize = 30.sp)
    }
}

@Composable
fun BottomComposable(value: Int) {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        val comment = when (value) {
            1 -> "Fur real?"
            2 -> "Noooo!"
            3 -> "Hummmmm"
            4 -> "Nah man!"
            5 -> "Missed it...shocks!"
            else -> "Jackpot!!!!"
        }
        Text(text = comment, fontSize = 30.sp)
    }
}

