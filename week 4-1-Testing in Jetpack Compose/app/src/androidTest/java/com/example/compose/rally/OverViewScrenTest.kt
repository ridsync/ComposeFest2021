package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test
import java.util.*

/**
 * Created by okwon on 2021/11/28.
 * Description :
 */
class OverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun overviewScreen_alertsDisplayed() {
        composeTestRule.setContent {
            RallyTheme {
                OverviewBody()
            }
        }

        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }

    @Test
    fun overviewScreen_alerts_dialog_Displayed() {
        composeTestRule.setContent {
            RallyTheme {
                RallyApp()
            }
        }

        composeTestRule.onRoot().printToLog("exercise_dialog_test")

        composeTestRule
            .onNodeWithContentDescription("ShowAlertDialog")
            .performClick()

        Thread.sleep(1000)

//        composeTestRule
//            .onNodeWithText("Dismiss".uppercase(Locale.getDefault()))
//            .assertIsDisplayed()
//
        composeTestRule
            .onNodeWithTag("AlertDialogTa")
            .assertIsDisplayed()


    }
}
