package com.example.aksa.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aksa.R
import com.example.aksa.ui.theme.NonBlack
import com.example.aksa.ui.theme.Nr40
import com.example.aksa.ui.theme.Sc70

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: Int? = null,
    isPassword: Boolean = false,
    showPasswordToggle: Boolean = false,
    visibleIcon: Int = R.drawable.ic_eye_on,
    hiddenIcon: Int = R.drawable.ic_eye_off,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    height: Dp = 50.dp,
    cornerRadius: Dp = 30.dp,
    fontSize: TextUnit = 13.sp,
    fontFamily: FontFamily = FontFamily(Font(R.font.nunito_regular)),
    focusedContainerColor: Color = Color.White,
    unfocusedContainerColor: Color = Color.White,
    focusedIndicatorColor: Color = Sc70,
    unfocusedIndicatorColor: Color = Sc70,
    focusedTextColor: Color = NonBlack,
    unfocusedTextColor: Color = NonBlack,
    placeholderColor: Color = Nr40,
    enabled: Boolean = true
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = when {
        isPassword && !passwordVisible -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }

    val actualKeyboardType = when {
        isPassword -> KeyboardType.Password
        else -> keyboardType
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontSize = fontSize,
                    fontFamily = fontFamily
                )
            )
        },
        leadingIcon = if (leadingIcon != null) {
            {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null
                )
            }
        } else null,
        trailingIcon = if (showPasswordToggle && isPassword) {
            {
                Image(
                    painter = painterResource(
                        id = if (passwordVisible) visibleIcon else hiddenIcon
                    ),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    modifier = Modifier.clickable {
                        passwordVisible = !passwordVisible
                    }
                )
            }
        } else null,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(cornerRadius),
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = actualKeyboardType),
        textStyle = TextStyle(
            fontSize = fontSize,
            fontFamily = fontFamily
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            unfocusedPlaceholderColor = placeholderColor,
            focusedPlaceholderColor = placeholderColor,
            disabledContainerColor = unfocusedContainerColor,
            disabledIndicatorColor = unfocusedIndicatorColor,
            disabledTextColor = unfocusedTextColor,
            disabledPlaceholderColor = placeholderColor
        ),
        enabled = enabled
    )
}
