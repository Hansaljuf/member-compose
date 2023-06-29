package com.capstone.maroonlabkom

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstone.maroonlabkom.ui.Screen.Home.HomeScreen
import com.capstone.maroonlabkom.ui.Screen.about.AboutScreen
import com.capstone.maroonlabkom.ui.Screen.detail.DetailScreen
import com.capstone.maroonlabkom.ui.navigation.Screen
import com.capstone.maroonlabkom.ui.theme.redMaroon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabKomApp(
    navController: NavHostController = rememberNavController(),
){
    Scaffold(
        topBar = { TopBar(navController = navController, modifier = Modifier) },
        modifier = Modifier
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen(
                    modifier = Modifier,
                    navigateToDetail = {memberId->
                        navController.navigate(Screen.Detail.createRoute(memberId))
                    }
                )
            }
            composable(Screen.About.route){
                AboutScreen(
                    modifier = Modifier,
                    name = "Hanif Hanan Al-Jufri",
                    email = "A296DSX1690@bangkit.academy",
                    photoResId = R.drawable.hanif
                )
            }
            composable(
                Screen.Detail.route,
                arguments = listOf(navArgument("id"){type= NavType.IntType})
            ){
                val id = it.arguments?.getInt("id")?:0
                DetailScreen(
                    memberId = id,
                    navigateBack={
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController,modifier:Modifier=Modifier){
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = redMaroon),
        title = {
            Text(text = stringResource(id = R.string.app_name), color = Color.White)
        },
        actions = {
            IconButton(
                onClick = { navController.navigate(Screen.About.route) }
            ) {
                Icon(
                    Icons.Default.AccountCircle,
                    tint = Color.White,
                    contentDescription = "Icon About"
                )
            }
        }
    )
}
