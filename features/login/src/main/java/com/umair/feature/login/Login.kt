package com.umair.feature.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.umair.core.domain.useCases.LoginUseCase
import com.umair.features.dashboard.DashboardActivity

@Composable
fun LoginView(context: Context) {
    LoginScreen(
        context
    )
}

@Composable
fun LoginScreen(context: Context) {
    var emailState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .padding(0.dp, 40.dp, 0.dp, 0.dp),
            painter = painterResource(R.drawable.logo_image),
            contentDescription = ""
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the show", modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = emailState, onValueChange = {
                    emailState = it
                }, label = { Text(text = "Email") }, modifier = Modifier.padding(10.dp)
            )

            TextField(
                value = passwordState, onValueChange = {
                    passwordState = it
                }, label = { Text(text = "password") }, modifier = Modifier.padding(10.dp)
            )
        }
        Button(
            onClick = {
                if (emailState.isNotEmpty() && passwordState.isNotEmpty()) {
                    Toast.makeText(
                        context,
                        "Login Successful {${LoginUseCase().getResults()}}",
                        Toast.LENGTH_SHORT
                    ).show()
                    context.startActivity(Intent(context, DashboardActivity::class.java))

                } else {
                    Toast.makeText(context, "Please enter value", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
        ) {
            Text(text = "Login")
        }
    }
}

