package com.oklab.devokscomposecodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oklab.devokscomposecodelab.ui.theme.DevOksComposeCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevOksComposeCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {

    //  The remember function works only as long as the composable is kept in the Composition
    // This will save each state surviving configuration changes (such as rotations) and process death.
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(0) }
    var isLazyList by remember { mutableStateOf(false) }

    when (shouldShowOnboarding) {
        0 -> {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = it })
        }
        1 -> {
            Greetings()
        }
        2 -> {
            LazyGreetings()
        }
        3 -> {
            FinishingTouches()
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: (Int) -> Unit) {

    Surface(color = MaterialTheme.colors.onSurface) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = { onContinueClicked.invoke(1) }
            ) {
                Text("Greeting")
            }
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                modifier = Modifier
                    .padding(vertical = 12.dp),
                onClick = { onContinueClicked.invoke(2) }
            ) {
                Text("LazyList")
            }
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                modifier = Modifier
                    .padding(vertical = 12.dp),
                onClick = { onContinueClicked.invoke(3) }
            ) {
                Text("Finishing Touches")
            }
        }
    }
}


@Composable
private fun Greetings(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
//    val expanded = remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var clicks by remember { mutableStateOf(0) }
//    val extraPadding = if (expanded.value) 48.dp else 0.dp
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp
//    )
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "$name", style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                Text(text = "Clicked($clicks), ", )
            }
            OutlinedButton(
                onClick = { expanded = !expanded ; ++clicks},
                colors =  ButtonDefaults.outlinedButtonColors(backgroundColor =  MaterialTheme.colors.surface)
            ) {
                Text(if (expanded) stringResource(R.string.show_less) else stringResource(R.string.show_more))
            }
        }
    }
}

@Composable
private fun LazyGreetings(names: List<String> = List(1000) { "$it" } ) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = "Item $name")
        }
    }
}

@Composable
private fun FinishingTouches(names: List<String> = List(1000) { "$it" } ) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            TouchItems(name = "Item $name")
        }
    }
}


@Composable
private fun TouchItems(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    DevOksComposeCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingsPreviewDark"
)
@Composable
fun GreetingsPreview() {
    DevOksComposeCodelabTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun LazyGreetingsPreview() {
    DevOksComposeCodelabTheme {
        LazyGreetings()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "FinishingTouchesPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun FinishingTouchesPreview() {
    DevOksComposeCodelabTheme {
        FinishingTouches()
    }
}

