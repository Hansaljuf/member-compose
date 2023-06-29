package com.capstone.maroonlabkom.ui.navigation

sealed class Screen(val route:String) {
    object Home: Screen("home")
    object Detail: Screen("detail/{id}"){
        fun createRoute(id:Int) = "detail/$id"
    }
    object About: Screen("about")
}