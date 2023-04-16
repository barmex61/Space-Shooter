@file:JvmName("Lwjgl3Launcher")

package com.fatih.youtube.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.fatih.youtube.SpaceShooter

/** Launches the desktop (LWJGL3) application. */
fun main() {
    Lwjgl3Application(SpaceShooter(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("YoutubeDemo")
        setWindowedMode(9*32, 16*32)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
