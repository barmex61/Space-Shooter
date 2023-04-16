package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.fatih.youtube.ecs.components.FacingComponent
import com.fatih.youtube.ecs.components.PlayerComponent
import com.fatih.youtube.ecs.components.TransformComponent
import com.fatih.youtube.ecs.types.FacingDirection
import com.fatih.youtube.utils.Utils.facing
import com.fatih.youtube.utils.Utils.transform
import ktx.ashley.allOf

class PlayerInputSystem (private val viewPort : Viewport) : IteratingSystem(allOf(PlayerComponent::class,TransformComponent::class,FacingComponent::class).get()) {

    private val tempVec = Vector2()

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val facingComponent = entity.facing
        val transformComponent = entity.transform
        tempVec.x = Gdx.input.x.toFloat()
        viewPort.unproject(tempVec)
        val diffX = tempVec.x - transformComponent.position.x - transformComponent.size.x * 0.5f
        facingComponent.facingDirection = when {
            diffX < -0.2f -> FacingDirection.LEFT
            diffX > 0.2f -> FacingDirection.RIGHT
            else -> FacingDirection.DEFAULT
        }
    }

}
