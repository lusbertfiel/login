package com.fiel.login.ui.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fiel.login.Screens

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel= hiltViewModel()) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = viewModel.email!!)
        TextButton(
            border = BorderStroke(1.dp, Color.Red),
            shape = RoundedCornerShape(5.dp),
            onClick = {
                viewModel.logout()
                navController.navigate(Screens.Login.route){
                    popUpTo(Screens.Home.route){inclusive =true}
                }
            }) {
            Text(text = "Cerrar Sesion")
        }
    }
}