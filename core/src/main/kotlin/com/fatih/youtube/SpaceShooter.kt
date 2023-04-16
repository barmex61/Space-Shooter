package com.fatih.youtube

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.viewport.FitViewport
import com.fatih.youtube.ecs.system.*
import com.fatih.youtube.screens.BaseScreen
import com.fatih.youtube.screens.GameScreen
import com.fatih.youtube.utils.Utils.V_HEIGHT
import com.fatih.youtube.utils.Utils.V_WIDTH
import com.fatih.youtube.utils.Utils.log
import ktx.app.KtxGame
import ktx.assets.disposeSafely

class SpaceShooter: KtxGame<BaseScreen>() {

    val viewPort = FitViewport(V_WIDTH, V_HEIGHT)
    lateinit var spriteBatch : Batch
    private val textureAtlas : TextureAtlas by lazy { TextureAtlas("graphics/graphics.atlas") }

    val engine : Engine by lazy {
        PooledEngine().apply {
            addSystem(PlayerInputSystem(viewPort))
            addSystem(MoveSystem())
            addSystem(DamageSystem())
            addSystem(PlayerAnimationSystem(textureAtlas.findRegion("ship_base"),textureAtlas.findRegion("ship_left"),textureAtlas.findRegion("ship_right")))
            addSystem(AnimationSystem(textureAtlas))
            addSystem(RenderSystem(spriteBatch,viewPort))
            addSystem(RemoveSystem())
            addSystem(DebugSystem())
        }
    }

    override fun create() {
        Gdx.app.logLevel = LOG_DEBUG
        spriteBatch = SpriteBatch()
        log.debug { "Creating game" }
        addScreen(GameScreen(this))
        setScreen<GameScreen>()
    }

    override fun dispose() {
        super.dispose()
        log.debug { "Sprite in batch ${(spriteBatch as SpriteBatch).maxSpritesInBatch}" }
        spriteBatch.disposeSafely()
        textureAtlas.disposeSafely()
    }
}


