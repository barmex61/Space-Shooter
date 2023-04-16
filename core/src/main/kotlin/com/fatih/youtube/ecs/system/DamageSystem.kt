package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.fatih.youtube.ecs.components.PlayerComponent
import com.fatih.youtube.ecs.components.RemoveComponent
import com.fatih.youtube.ecs.components.TransformComponent
import com.fatih.youtube.utils.Utils.DAMAGE_AREA_HEIGHT
import com.fatih.youtube.utils.Utils.DAMAGE_PER_SECOND
import com.fatih.youtube.utils.Utils.DEATH_EXPLOSION_DURATION
import com.fatih.youtube.utils.Utils.player
import com.fatih.youtube.utils.Utils.transform
import ktx.ashley.addComponent
import ktx.ashley.allOf
import kotlin.math.max

class DamageSystem : IteratingSystem(allOf(PlayerComponent::class,TransformComponent::class).exclude(RemoveComponent::class.java).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transformComponent = entity.transform
        val playerComponent = entity.player
        if (transformComponent.position.y <= DAMAGE_AREA_HEIGHT){
            var damage = DAMAGE_PER_SECOND * deltaTime
            if (playerComponent.shield > 0f){
                val blockAmount = playerComponent.shield
                playerComponent.shield = max(0f,playerComponent.shield - damage)
                damage -= blockAmount
                if (damage <= 0f){
                    return
                }
            }
            playerComponent.life -= damage
            if (playerComponent.life <= 0f){
                entity.addComponent<RemoveComponent>(engine){
                    delay = DEATH_EXPLOSION_DURATION
                }
            }
        }
    }
}
