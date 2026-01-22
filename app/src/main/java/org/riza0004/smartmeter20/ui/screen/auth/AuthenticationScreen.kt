package org.riza0004.smartmeter20.ui.screen.auth

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import org.riza0004.smartmeter20.R

@Composable
fun AuthenticationScreen(){
    val contract = FirebaseAuthUIActivityResultContract()
    val launcher = rememberLauncherForActivityResult(contract) { }
    Scaffold(
        containerColor = colorResource(R.color.main_background)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Icon(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.size(135.dp).padding(bottom = 8.dp),
                tint = colorResource(R.color.light_main)
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                color = colorResource(R.color.light_main)
            )
            TextButton(
                modifier = Modifier.padding(top = 86.dp),
                onClick = {
                    launcher.launch(getSignInIntent())
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.light_main),
                    contentColor = colorResource(R.color.main_background)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Text(
                modifier = Modifier.padding(top = 224.dp),
                text = stringResource(R.string.version, "2.0.0"),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(R.color.light_main)
            )
        }
    }
}

private fun getSignInIntent(): Intent{
    return AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(
            arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        ).build()
}