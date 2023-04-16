package com.fatih.youtube.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.Viewport
import com.fatih.youtube.SpaceShooter
import com.fatih.youtube.ecs.components.*
import com.fatih.youtube.ecs.types.AnimationType
import com.fatih.youtube.utils.Utils.DAMAGE_AREA_HEIGHT
import com.fatih.youtube.utils.Utils.MAX_DELTA_TIME
import com.fatih.youtube.utils.Utils.V_WIDTH
import com.fatih.youtube.utils.Utils.log
import ktx.ashley.entity
import ktx.ashley.with
import ktx.assets.disposeSafely
import kotlin.math.min

class GameScreen(private val game : SpaceShooter) : BaseScreen(game) {

    override val viewPort: Viewport = game.viewPort

    override fun show() {
        log.debug { "first screen show" }
        game.engine.entity {
            with<TransformComponent> {
                position.set(1f,6f, 0f)
            }
            with<MoveComponent>()
            with<GraphicComponent>()
            with<PlayerComponent>()
            with<FacingComponent>()
        }
        game.engine.entity{
            with<TransformComponent>{
                size.set(V_WIDTH, DAMAGE_AREA_HEIGHT)
            }
            with<AnimationComponent>{
                type = AnimationType.DARK_MATTER
            }
            with<GraphicComponent>()
        }

    }

    override fun render(delta: Float) {
        engine.update(min(MAX_DELTA_TIME,delta))
    }

    override fun dispose() {
        batch.disposeSafely()
    }
}
