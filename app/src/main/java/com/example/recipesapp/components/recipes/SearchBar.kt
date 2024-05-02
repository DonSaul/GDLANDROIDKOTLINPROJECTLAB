package com.example.recipesapp.components.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.theme.LightBrown


@Composable
fun SearchBar(
    placeholder: String,
    action: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by remember { mutableStateOf("") }
    var menuExpanded by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val offsetWidth = screenWidth - 60.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightBrown)
            .padding(29.dp)
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text(placeholder) },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    action(searchText)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                action(searchText)
            }),
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { menuExpanded = !menuExpanded },
            modifier = Modifier
                .weight(0.2f)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .background(Color.White)
                .padding(4.dp)
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
        }

        /* Filtrado por Dieta - DropDownMenu */
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            offset = DpOffset(
                x = offsetWidth,
                y = 20.dp
            )
        ) {
            DropdownMenuItem(onClick = {
                menuExpanded = false
            },
                text = { Text(text = "vaors") }
            )
        }
    }
}




