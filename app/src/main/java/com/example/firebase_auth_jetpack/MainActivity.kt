package com.example.firebase_auth_jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firebase_auth_jetpack.ui.theme.FirebaseAuthJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthJetpackTheme {
                val state = rememberOneTapSignInState()
                val authenticated = remember { mutableStateOf(false) }
                OneTapSignInWithGoogle(
                    state = state,
                    clientId = "917109330393-gp014qelj7auojac9lgmp1luhboutbr2.apps.googleusercontent.com",
                    onTokenIdReceived = { tokenId ->
                        authenticated.value = true
                        Log.d("MainActivity", tokenId)
                    },
                    onDialogDismissed = { message ->
                        Log.d("MainActivity", message)
                    }
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        state.open()
                    }, enabled = !state.opened) {
                        Text("Sign in")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    if (authenticated.value) {
                        Text("Successfully Authenticated")
                    }
                }
            }
        }
    }
}
