package org.riza0004.smartmeter20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.riza0004.smartmeter20.navigation.SetupNavGraph
import org.riza0004.smartmeter20.ui.theme.SmartMeter20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartMeter20Theme {
                SetupNavGraph()
            }
        }
    }
}