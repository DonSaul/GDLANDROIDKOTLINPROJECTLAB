package com.example.recipesapp

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyPress
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipesapp.components.recipes.SearchBarApp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.input.key.*
import androidx.compose.ui.test.onNodeWithTag

@RunWith(AndroidJUnit4::class)
class SearchBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun inSearchBarTest(){
        var subSearch = ""
        var subDiet = ""
        var searching = ""

        val action: (String,String) -> Unit ={ search,dietary ->
            subSearch = search
            subDiet = dietary

        }

        val searchText = "burger"
        composeRule.setContent { SearchBarApp(placeholder = "Search", action = action) }
        composeRule.onNodeWithText("Search").performTextInput(searchText)
        composeRule.onNodeWithText(searchText).performKeyPress(KeyEvent(NativeKeyEvent(android.view.KeyEvent.ACTION_DOWN,android.view.KeyEvent.KEYCODE_ENTER)))

        assert(subSearch == "burger")
    }
}