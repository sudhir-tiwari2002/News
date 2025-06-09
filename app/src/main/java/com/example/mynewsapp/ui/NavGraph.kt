package com.example.mynewsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mynewsapp.ui.Composable.DetailScreen
import com.example.mynewsapp.ui.Composable.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{url}") {
        fun createRoute(url: String): String =
            "detail/${java.net.URLEncoder.encode(url, "UTF-8")}"
    }
    object Bookmark : Screen("bookmark")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onArticleClick = { url ->
                if (url.isNotBlank()) {
                    navController.navigate(Screen.Detail.createRoute(url))
                }
            })
        }

        composable(
            route = "detail/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val decodedUrl = java.net.URLDecoder.decode(encodedUrl, "UTF-8")
            DetailScreen(articleUrl = decodedUrl)
        }

//        composable(Screen.Bookmark.route) {
//            BookmarkScreen()
//        }
    }
}