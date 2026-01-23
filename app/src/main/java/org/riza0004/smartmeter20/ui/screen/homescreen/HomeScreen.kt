package org.riza0004.smartmeter20.ui.screen.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.navigation.Screen
import org.riza0004.smartmeter20.ui.component.button.CustomFloatingActionButton
import org.riza0004.smartmeter20.ui.component.dialog.DialogAddGroup
import org.riza0004.smartmeter20.ui.component.GroupList
import org.riza0004.smartmeter20.ui.component.UsageReport
import org.riza0004.smartmeter20.ui.screen.auth.AuthenticationScreen
import org.riza0004.smartmeter20.ui.theme.SmartMeter20Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController){
    val viewModel: HomeViewModel = viewModel()
    val userFlow by viewModel.userFlow.collectAsState()
    val context = LocalContext.current
    var dialogAddGroupIsOpen by remember { mutableStateOf(false) }
    if(userFlow == null){
        AuthenticationScreen()
    }
    userFlow?.let {
        Scaffold(
            floatingActionButton = {
                CustomFloatingActionButton {
                    dialogAddGroupIsOpen = true
                }
            },
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
                        Box(
                            modifier = Modifier.padding(end = 16.dp).size(32.dp)
                                .clip(CircleShape)
                                .clickable(onClick = {
                                    navHostController.navigate(Screen.ProfileScreen.route)
//                                    AuthUI.getInstance().signOut(context)
                                })
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(it.photoUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                )
            }
        ) {innerPadding->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ){
                Column(
                    modifier = Modifier.wrapContentSize()
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    UsageReport()
                    GroupList()
                }
            }
            if(dialogAddGroupIsOpen){
                DialogAddGroup(
                    onDismiss = {dialogAddGroupIsOpen = false}
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SmartMeter20Theme {
        HomeScreen(rememberNavController())
    }
}