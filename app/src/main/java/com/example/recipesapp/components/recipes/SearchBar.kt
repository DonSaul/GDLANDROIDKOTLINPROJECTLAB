package com.example.recipesapp.components.recipes

import SearchViewModel
import android.app.Application
import android.widget.ToggleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.theme.LightBrown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
//import com.example.recipesapp.data.HistoryEx
//import com.example.recipesapp.data.HistorySearch
import com.example.recipesapp.data.local.entities.SearchHistoryEntity
import com.example.recipesapp.ui.theme.MediumBrown
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarApp(placeholder: String, action: (String, String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var active by remember {
        mutableStateOf(false)
    }
    var menuExpanded by remember { mutableStateOf(false) }
    val menuItems = listOf(
        "Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian",
        "Ovo-Vegetarian", "Vegan", "Pescarian", "Paleo", "Primal", "Low FODMAP", "Whole 30"
    )
    val selectedItems = remember { mutableStateOf(List(menuItems.size) { false }) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val offsetWidth = screenWidth - 60.dp
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val searchViewModel: SearchViewModel =
        remember { SearchViewModel(context.applicationContext as Application) }
    val history by searchViewModel.history.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightBrown)
    ) {
        SearchBar(
            modifier = Modifier
                .background(LightBrown)
                .fillMaxWidth()
                .wrapContentHeight(),
            query = text,
            onQueryChange = { text = it },
            onSearch = {

                active = false
                if (text.isNotEmpty()) {
                    val historySearchTemp = SearchHistoryEntity(0, search =  text)
                    searchViewModel.addHistorySearchRecord(historySearchTemp)
                    action(text, selectedItemsToString(selectedItems.value))
                }

            },
            colors = SearchBarDefaults.colors(MediumBrown),
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                IconButton(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier
                ) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
                }
            },
            trailingIcon = {
                Row {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon"
                        )

                    }
                }
            }

        ) {
            if (history.isEmpty()) {
                Text("No hay historial disponible", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn {
                    items(history.size) { item ->
                        Row(modifier = Modifier
                            .padding(all = 14.dp)
                            .clickable {
                                text = history[item].search
                                active = false
                                action(text, selectedItemsToString(selectedItems.value))
                            }) {
                            Icon(
                                modifier = Modifier.padding(end = 10.dp),
                                imageVector = Icons.Default.History,
                                contentDescription = "History Icon"
                            )
                            Text(text = history[item].search)
                        }
                    }
                }
            }
        }

        //Spacer(modifier = Modifier.padding(6.dp, 0.dp))


        /* Filtrado por Dieta - DropDownMenu */
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false },
            offset = DpOffset(
                x = offsetWidth,
                y = 20.dp
            )
        ) {
            menuItems.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { },
                    onClick = {
                        selectedItems.value = selectedItems.value.toMutableList().apply {
                            this[index] = !this[index]
                        }
                    },
                    modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    // First column
                    Column(modifier = Modifier.weight(1f)) {
                        Switch(
                            checked = selectedItems.value[index],
                            onCheckedChange = { isChecked ->
                                selectedItems.value = selectedItems.value.toMutableList().apply {
                                    this[index] = isChecked
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre columnas

                    // Segunda columna
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item)

                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(6.dp, 0.dp))
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {

            menuItems.forEachIndexed { index, item ->

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    // First column
                    Switch(
                        checked = selectedItems.value[index],
                        onCheckedChange = { isChecked ->
                            selectedItems.value = selectedItems.value.toMutableList().apply {
                                this[index] = isChecked
                            }
                        }
                    )


                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre columnas

                    // Segunda columna
                    Text(text = item)


                }
            }
        }
    }
}

@Composable
fun SearchBar2(
    placeholder: String,
    action: (String, String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by remember { mutableStateOf("") }
    var menuExpanded by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val offsetWidth = screenWidth - 60.dp

    val menuItems = listOf(
        "Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian",
        "Ovo-Vegetarian", "Vegan", "Pescarian", "Paleo", "Primal", "Low FODMAP", "Whole 30"
    )

    val selectedItems = remember { mutableStateOf(List(menuItems.size) { false }) }

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
                    action(searchText, selectedItemsToString(selectedItems.value))
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
                action(searchText, selectedItemsToString(selectedItems.value))
            }),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.padding(6.dp, 0.dp))
        IconButton(
            onClick = { menuExpanded = !menuExpanded },
            modifier = Modifier
                .weight(0.2f)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .background(LightBrown)
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
            menuItems.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { },
                    onClick = {
                        selectedItems.value = selectedItems.value.toMutableList().apply {
                            this[index] = !this[index]
                        }
                    },
                    modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = selectedItems.value[index],
                        onCheckedChange = { isChecked ->
                            selectedItems.value = selectedItems.value.toMutableList().apply {
                                this[index] = isChecked
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = item)

                }
            }
        }
    }
}

fun selectedItemsToString(selectedItems: List<Boolean>): String {
    val selectedItemsStringList = mutableListOf<String>()
    val menuItems = listOf(
        "Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian",
        "Ovo-Vegetarian", "Vegan", "Pescarian", "Paleo", "Primal", "Low FODMAP", "Whole 30"
    )
    for ((index, isSelected) in selectedItems.withIndex()) {
        if (isSelected) {
            selectedItemsStringList.add(menuItems[index])
        }
    }

    return selectedItemsStringList.joinToString(", ")
}