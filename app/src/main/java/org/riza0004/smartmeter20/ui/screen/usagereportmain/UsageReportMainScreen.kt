package org.riza0004.smartmeter20.ui.screen.usagereportmain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseUser
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.navigation.Screen
import org.riza0004.smartmeter20.ui.component.DetailedUsageReport

const val NAV_ID = "navId"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsageReportMainScreen(
    navId: Int,
    navHostController: NavHostController,
    userFlow: FirebaseUser?
){
    if(userFlow == null){
        navHostController.navigate(Screen.HomeScreen.route)
    }
    userFlow?.let {
        Scaffold(
            containerColor = colorResource(R.color.white),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(R.color.main_background)
                    ),
                    title = {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    navHostController.navigateUp()
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_keyboard_arrow_left_24),
                                    contentDescription = stringResource(R.string.app_name),
                                    tint = colorResource(R.color.light_main),
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Text(
                                text = when(navId) {
                                    0 -> it.displayName.toString()
                                    1 -> "group"
                                    else -> "smart meter"
                                },
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.headlineMedium,
                                color = colorResource(R.color.light_main)
                            )
                        }
                    }
                )
            }
        ) { innerPadding->
            DetailedUsageReport(
                type = navId,
                modifier = Modifier  .padding(innerPadding)
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
            )
        }
    }
}