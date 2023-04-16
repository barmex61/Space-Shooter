package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.fatih.youtube.ecs.components.*
import com.fatih.youtube.ecs.types.FacingDirection
import com.fatih.youtube.utils.Utils.HOR_ACCELERATION
import com.fatih.youtube.utils.Utils.MAX_HOR_SPEED
import com.fatih.youtube.utils.Utils.MAX_VER_NEG_PLAYER_SPEED
import com.fatih.youtube.utils.Utils.MAX_VER_POS_PLAYER_SPEED
import com.fatih.youtube.utils.Utils.UPDATE_RATE
import com.fatih.youtube.utils.Utils.VER_ACCELERATION
import com.fatih.youtube.utils.Utils.V_HEIGHT
import com.fatih.youtube.utils.Utils.V_WIDTH
import com.fatih.youtube.utils.Utils.move
import com.fatih.youtube.utils.Utils.transform
import ktx.ashley.allOf
import ktx.ashley.get
import kotlin.math.max
import kotlin.math.min

class MoveSystem : IteratingSystem(allOf(TransformComponent::class,MoveComponent::class).exclude(RemoveComponent::class.java).get()) {

    private var accumulator = 0f

    override fun update(deltaTime: Float) {

        accumulator += deltaTime
        while (accumulator >= UPDATE_RATE){
            accumulator -= UPDATE_RATE
            entities.forEach { entity ->
                entity[TransformComponent.mapper]?.let {transformComponent->
                    transformComponent.prevPosition.set(transformComponent.position)
                }
            }
            super.update(UPDATE_RATE)
        }
        val alpha = accumulator / UPDATE_RATE
        entities.forEach { entity ->
            entity[TransformComponent.mapper]?.let {transformComponent->
                transformComponent.interpolatedPosition.set(
                    MathUtils.lerp(transformComponent.prevPosition.x,transformComponent.position.x,alpha),
                    MathUtils.lerp(transformComponent.prevPosition.y,transformComponent.position.y,alpha),
                    transformComponent.position.z

                )
            }
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {

        val transformComponent = entity.transform
        val moveComponent = entity.move
        val playerComponent = entity[PlayerComponent.mapper]
        if (playerComponent != null){
            entity[FacingComponent.mapper]?.let {facingComponent ->
                movePlayer(transformComponent,moveComponent,playerComponent,facingComponent,deltaTime)
            }
        }else{
            moveEntity(transformComponent,moveComponent,deltaTime)
        }

    }

    private fun movePlayer(transformComponent: TransformComponent, moveComponent: MoveComponent, playerComponent: PlayerComponent, facingComponent: FacingComponent, deltaTime: Float) {

        moveComponent.speed.x = when(facingComponent.facingDirection){
            FacingDirection.LEFT->{ min(0f,moveComponent.speed.x - HOR_ACCELERATION * deltaTime) }
            FacingDirection.RIGHT->{ max(0f,moveComponent.speed.x + HOR_ACCELERATION * deltaTime) }
            else -> 0f
        }
        moveComponent.speed.x = MathUtils.clamp(
            moveComponent.speed.x,
            -MAX_HOR_SPEED,
            MAX_HOR_SPEED)
        moveComponent.speed.y = MathUtils.clamp(
            moveComponent.speed.y - VER_ACCELERATION * deltaTime ,
            -MAX_VER_NEG_PLAYER_SPEED,
            MAX_VER_POS_PLAYER_SPEED)
        moveEntity(transformComponent,moveComponent,deltaTime)
    }

    private fun moveEntity(transformComponent: TransformComponent, moveComponent: MoveComponent, deltaTime: Float) {
        transformComponent.position.x = MathUtils.clamp(
            transformComponent.position.x + moveComponent.speed.x * deltaTime,
            0f,
                V_WIDTH - transformComponent.size.x
        )
        transformComponent.position.y = MathUtils.clamp(
            transformComponent.position.y + moveComponent.speed.y * deltaTime,
            1f,
            V_HEIGHT + 1f - transformComponent.size.y
        )
    }

}
