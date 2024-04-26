package com.example.recipesapp.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String
) {

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = Color.White
        )
    }

    OutlinedTextField(
        value = value,
        label = { Text(text = label, color = Color.White) },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onValueChange = onChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            errorBorderColor = Color.White,
            disabledBorderColor = Color.White,
            disabledPlaceholderColor = Color.White,
            disabledTextColor = Color.White,
            disabledLabelColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            unfocusedPlaceholderColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        textStyle = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Bold
        ),
        leadingIcon = leadingIcon,
        placeholder = { Text(placeholder, color = Color.White) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}