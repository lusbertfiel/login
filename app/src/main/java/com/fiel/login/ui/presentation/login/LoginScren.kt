package com.fiel.login.ui.presentation.login

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fiel.login.R
import com.fiel.login.Screens
import com.fiel.login.ui.domain.model.DataResponse

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "MT", fontSize = 70.sp, fontWeight = FontWeight.Bold)
        Text(text = "Inicia con tu cuenta")
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Email") },
            leadingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = "") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            value = viewModel.email,
            onValueChange = {
                viewModel.email = it
                viewModel.valideEmail()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Password") },
            leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = "") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            value = viewModel.password,
            visualTransformation = if (viewModel.visible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {
                viewModel.password = it
                viewModel.validePassword()
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { viewModel.visible = !viewModel.visible },
                    painter = painterResource(id = if (!viewModel.visible) R.drawable.ic_visibility_off else R.drawable.ic_visibilit),
                    contentDescription = ""
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = viewModel.enable,
            onClick = {
            viewModel.login()
            }) {
            Text(text = "Iniciar Sesion")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "No tienes cuenta? ", color = Color.Gray)
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.Register.route)
                },
                text = "Registrate", fontWeight = FontWeight.Bold, color = Color.Red.copy(0.7f)
            )
        }

        when (viewModel.stateLogin) {
            DataResponse.Cargando -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            is DataResponse.Error -> {
                Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_SHORT).show()
            }
            is DataResponse.Success -> {
                LaunchedEffect(Unit ){
                    navController.navigate(Screens.Home.route){
                        popUpTo(Screens.Login.route){inclusive =true}
                    }
                }
            }
            else -> {}
        }
    }
}