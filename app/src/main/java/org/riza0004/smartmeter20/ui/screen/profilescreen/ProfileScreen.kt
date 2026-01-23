package org.riza0004.smartmeter20.ui.screen.profilescreen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.firebase.ui.auth.AuthUI
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.navigation.Screen
import org.riza0004.smartmeter20.ui.component.textfield.CustomTextField
import org.riza0004.smartmeter20.ui.component.button.CustomTextIconButton
import org.riza0004.smartmeter20.ui.component.dialog.DialogTwoButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController
){
    val context = LocalContext.current
    val viewModel: ProfileViewModel = viewModel()
    val user by viewModel.userFlow.collectAsState()
    var showEmail by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var showLogoutDialog by remember { mutableStateOf(false) }
    val logoutText = stringResource(R.string.logout)
    if(user == null){
        navHostController.navigate(Screen.HomeScreen.route)
    }
    user?.let {
        if(showLogoutDialog){
            DialogTwoButton(
                onDismiss = { showLogoutDialog = false },
                title = stringResource(R.string.logout),
                content = stringResource(R.string.logout_content),
                onActionStartButton = {showLogoutDialog = false},
                onActionEndButton = {
                    showLogoutDialog = false
                    AuthUI.getInstance().signOut(context)
                    Toast.makeText(context, logoutText, Toast.LENGTH_SHORT).show()
                                    },
                startButtonText = stringResource(R.string.close),
                endButtonText = stringResource(R.string.logout),
                startButtonColor = colorResource(R.color.white),
                endButtonColor = colorResource(R.color.red_danger),
                startButtonContentColor = colorResource(R.color.main),
                endButtonContentColor = colorResource(R.color.white),
                startBorderButton = BorderStroke(1.dp, colorResource(R.color.main)),
            )
        }
        LaunchedEffect(true) {
            name = "${it.displayName}"
        }
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
                                text = stringResource(R.string.profile),
                                style = MaterialTheme.typography.headlineMedium,
                                color = colorResource(R.color.light_main)
                            )
                        }
                    }
                )
            }
        ) { innerPadding->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(92.dp)
                        .clip(CircleShape)
                        .clickable(onClick = {
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if(showEmail) "${it.email}" else "***",
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(
                        onClick = {
                            showEmail = !showEmail
                        }
                    ) {
                        Icon(
                            painter = if(showEmail) painterResource(R.drawable.eye_on) else painterResource(R.drawable.eye_off),
                            contentDescription = if(showEmail) stringResource(R.string.show) else stringResource(R.string.hide),
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }

                CustomTextField(
                    value = name,
                    onValChange = { value->
                        name = value
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextIconButton(
                    text = stringResource(R.string.usage_report),
                    icon = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = colorResource(R.color.light_main),
                    contentColor = colorResource(R.color.black),
                    onClick = {

                    },
                )
                CustomTextIconButton(
                    text = stringResource(R.string.about_this_app),
                    icon = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = colorResource(R.color.light_main),
                    contentColor = colorResource(R.color.black),
                    onClick = {

                    },
                )
                CustomTextIconButton(
                    text = stringResource(R.string.logout),
                    icon = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = colorResource(R.color.white),
                    contentColor = colorResource(R.color.red_danger),
                    border = BorderStroke(2.dp, colorResource(R.color.red_danger)),
                    onClick = {
                        showLogoutDialog = true
                    },
                )
            }
        }
    }
}