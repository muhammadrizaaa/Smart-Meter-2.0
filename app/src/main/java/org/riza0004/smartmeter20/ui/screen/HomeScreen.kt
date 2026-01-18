package org.riza0004.smartmeter20.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.ui.theme.SmartMeter20Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.main_background)
                ),
                title = {
                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.app_icon),
                            contentDescription = stringResource(R.string.app_name),
                            tint = colorResource(R.color.light_main),
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = stringResource(R.string.short_app_name),
                            style = MaterialTheme.typography.headlineMedium,
                            color = colorResource(R.color.light_main)
                        )
                    }
                },
                actions = {
                    Icon(
                        modifier = Modifier.padding(end = 16.dp).size(28.dp),
                        painter = painterResource(R.drawable.profile),
                        contentDescription = stringResource(R.string.profile),
                        tint = colorResource(R.color.light_main)
                    )
                }
            )
        }
    ) {innerPadding->
        Box(modifier = Modifier.padding(innerPadding))
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SmartMeter20Theme {
        HomeScreen(rememberNavController())
    }
}