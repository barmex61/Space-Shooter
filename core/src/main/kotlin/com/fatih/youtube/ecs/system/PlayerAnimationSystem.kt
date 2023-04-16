package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.fatih.youtube.ecs.components.FacingComponent
import com.fatih.youtube.ecs.components.GraphicComponent
import com.fatih.youtube.ecs.components.PlayerComponent
import com.fatih.youtube.ecs.types.FacingDirection
import com.fatih.youtube.utils.Utils.facing
import com.fatih.youtube.utils.Utils.graphic
import ktx.ashley.allOf
import ktx.ashley.get

class PlayerAnimationSystem(
    private val defaultRegion : TextureRegion,
    private val leftRegion : TextureRegion,
    private val rightRegion: TextureRegion
) : IteratingSystem(allOf(PlayerComponent::class,FacingComponent::class,GraphicComponent::class).get()),EntityListener{

    private var lastFacingDirection = FacingDirection.DEFAULT

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.addEntityListener(family,this)
    }

    override fun removedFromEngine(engine: Engine) {
        super.removedFromEngine(engine)
        engine.removeEntityListener(this)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val facingComponent = entity.facing
        val graphicComponent = entity.graphic
        if (facingComponent.facingDirection == lastFacingDirection && graphicComponent.sprite.texture != null ){
            return
        }
        lastFacingDirection = facingComponent.facingDirection
        val region = when(facingComponent.facingDirection){
            FacingDirection.LEFT-> leftRegion
            FacingDirection.RIGHT-> rightRegion
            FacingDirection.DEFAULT-> defaultRegion
        }
        graphicComponent.setSpriteRegion(region)
    }

    override fun entityAdded(entity: Entity) {
        entity[GraphicComponent.mapper]?.setSpriteRegion(defaultRegion)
    }

    override fun entityRemoved(entity: Entity) = Unit
}
