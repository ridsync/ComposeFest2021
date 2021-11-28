package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test
import java.util.*

/**
 * Created by okwon on 2021/11/28.
 * Description :
 */
class TobAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun rallyTopAppBarTest_currentTabSelected(){
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { },
                    currentScreen = RallyScreen.Overview
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists(){
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { },
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists_with_useUnmergedTree() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { },
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_Exercise_MyRallyAppTest() {
        composeTestRule.setContent {
            RallyApp()
//            RallyTheme {
//                val allScreens = RallyScreen.values().toList()
//                var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }
//                RallyTopAppBar(
//                allScreens = allScreens,
//                onTabSelected = { screen -> currentScreen = screen },
//                currentScreen = currentScreen
//                )
//            }
        }

        composeTestRule.onRoot().printToLog("exercise_rallyapp_test")

        composeTestRule.onNodeWithContentDescription(RallyScreen.Overview.name).assertIsDisplayed()

        Thread.sleep(1000)

        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).performClick()

        Thread.sleep(1000)

        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsDisplayed()

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

}