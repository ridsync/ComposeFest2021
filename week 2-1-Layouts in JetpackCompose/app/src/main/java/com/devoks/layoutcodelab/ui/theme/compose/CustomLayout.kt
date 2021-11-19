package com.devoks.layoutcodelab.ui.theme.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.More
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devoks.layoutcodelab.ui.theme.LayoutcodelabTheme

/**
 * Created by okwon on 2021/11/18.
 * Description :
 */

// 7. Create your custom layout

@Composable
fun CustomLayout() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(
                            context,
                            "NavigationIcon Toast....",
                            Toast.LENGTH_SHORT
                        ).show() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "CustomLayout")
                },
                actions = {
                    IconButton(onClick = { Toast.makeText(
                        context,
                        "CheckIcon Toast....",
                        Toast.LENGTH_SHORT
                    ).show()
                    }) {
                        Icon(Icons.Filled.Check, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContentCustomLayout(Modifier.padding(innerPadding).padding(8.dp))
    }
}


@Composable
fun BodyContentCustomLayout(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height + 55
            }
        }
    }
}

@Preview
@Composable
fun MyOwnColumnPreview() {
    LayoutcodelabTheme {
        CustomLayout()
    }
}

