package com.app.presentation.navigation

private const val SLASH = "/"

sealed class Navigation(val destination: String) {
    data object Main : Navigation("main")
}
