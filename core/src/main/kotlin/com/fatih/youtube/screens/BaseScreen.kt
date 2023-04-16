package com.fatih.youtube.screens

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.viewport.Viewport
import com.fatih.youtube.SpaceShooter
import ktx.app.KtxScreen

abstract class BaseScreen(game : SpaceShooter , protected val batch : Batch = game.spriteBatch,protected val engine: Engine = game.engine) : KtxScreen {
    protected abstract val viewPort : Viewport
    override fun resize(width: Int, height: Int) {
        viewPort.update(width,height,true)
    }
}
