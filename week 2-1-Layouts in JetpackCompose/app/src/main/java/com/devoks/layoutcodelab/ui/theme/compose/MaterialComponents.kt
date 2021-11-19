package com.devoks.layoutcodelab.ui.theme.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.outlined.More
import androidx.compose.material.icons.sharp.More
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devoks.layoutcodelab.ui.theme.LayoutcodelabTheme

/**
 * Created by okwon on 2021/11/19.
 * Description :
 */

// 5. Material Components

@Composable
fun LayoutsCodelab() {
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
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { Toast.makeText(
                        context,
                        "MoreIcon Toast....",
                        Toast.LENGTH_SHORT
                    ).show()
                    }) {
                        Icon(Icons.Sharp.More, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding).padding(8.dp))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    LayoutcodelabTheme {
        LayoutsCodelab()
    }
}

