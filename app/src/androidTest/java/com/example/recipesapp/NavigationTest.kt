package com.example.recipesapp

import HomeScreen
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipesapp.components.common.BottomNavigation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.navigation.SetUpNavGraph
import junit.framework.TestCase.assertEquals
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController
    private lateinit var splashComponent: SemanticsNodeInteraction

    @Before
    fun setUpNavigation() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            SetUpNavGraph(navController = navController)
        }
    }

    @Test
    fun verify_StartDestinationIsSplashScreen() {
        splashComponent = composeTestRule.onNodeWithTag(
            testTag = "splash_screen",
        )

        splashComponent.assertExists()
    }
}