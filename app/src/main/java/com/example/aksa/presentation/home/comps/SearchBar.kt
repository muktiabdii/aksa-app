package com.example.aksa.presentation.home.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.Sc100
import com.example.aksa.ui.theme.Sc20

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            searchText = newValue
            onValueChange(newValue)
        },
        placeholder = {
            Text(
                text = "Temukan budaya dari berbagai penjuru",
                color = Sc100,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.nunito_medium))
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(25.dp),
                clip = false
            ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Sc100,
            unfocusedBorderColor = Sc100,
            focusedContainerColor = Sc20,
            unfocusedContainerColor = Sc20,
            focusedTextColor = NonBlack,
            unfocusedTextColor = NonBlack
        ),
        shape = RoundedCornerShape(25.dp),
        singleLine = true,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_medium)),
            color = NonBlack
        )
    )
}

@Preview
@Composable
private fun sdf() {
    SearchBar(
        onValueChange = {}
    )
}