package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.fatih.youtube.ecs.components.RemoveComponent
import com.fatih.youtube.utils.Utils.remove
import ktx.ashley.allOf

class RemoveSystem : IteratingSystem(allOf(RemoveComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val removeComponent = entity.remove
        removeComponent.delay -= deltaTime
        if (removeComponent.delay <= 0f){
            engine.removeEntity(entity)
        }
    }
}
