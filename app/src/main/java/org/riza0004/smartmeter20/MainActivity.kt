package org.riza0004.smartmeter20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.riza0004.smartmeter20.ui.theme.SmartMeter20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartMeter20Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(vertical = 16.dp).verticalScroll(
            rememberScrollState()
        )
    ) {
        Column {
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.displaySmall
            )
        }
        Column {
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Column {
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Column {
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Column {
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "Hello $name!",
                modifier = modifier,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartMeter20Theme {
        Greeting("Android")
    }
}