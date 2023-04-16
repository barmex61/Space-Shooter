package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.GdxRuntimeException
import com.fatih.youtube.ecs.components.AnimationComponent
import com.fatih.youtube.ecs.components.GraphicComponent
import com.fatih.youtube.ecs.types.AnimationType
import com.fatih.youtube.utils.Animation2d
import com.fatih.youtube.utils.Utils
import com.fatih.youtube.utils.Utils.animation
import com.fatih.youtube.utils.Utils.graphic
import jdk.jshell.execution.Util
import ktx.ashley.allOf
import ktx.ashley.get
import java.util.EnumMap

class AnimationSystem(private val atlas: TextureAtlas) : IteratingSystem(allOf(AnimationComponent::class,GraphicComponent::class).get()) ,
    EntityListener{

    private val animationCache = EnumMap<AnimationType,Animation2d>(AnimationType::class.java)

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.addEntityListener(family,this)
    }

    override fun removedFromEngine(engine: Engine) {
        super.removedFromEngine(engine)
        engine.removeEntityListener(this)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animationComponent = entity.animation
        val graphicComponent = entity.graphic
        if (animationComponent.type == AnimationType.NONE){
            Utils.log.error { "No type specified for animation component $animationComponent for entity $entity" }
            return
        }
        if (animationComponent.type == animationComponent.animation.type){
            animationComponent.stateTime += deltaTime
        }else{
            animationComponent.stateTime = 0f
            animationComponent.animation = getAnimation(animationComponent.type)
        }
        val frame = animationComponent.animation.getKeyFrame(animationComponent.stateTime)
        graphicComponent.setSpriteRegion(frame)

    }

    override fun entityAdded(entity: Entity) {
        entity[AnimationComponent.mapper]?.let{ animationComponent->
            animationComponent.animation = getAnimation(animationComponent.type)
            val frame = animationComponent.animation.getKeyFrame(animationComponent.stateTime)
            entity[GraphicComponent.mapper]?.let {graphicComponent->
                graphicComponent.setSpriteRegion(frame)
            }
        }
    }

    private fun getAnimation(type: AnimationType): Animation2d {
        var animation = animationCache[type]
        if (animation == null){
            var regions = atlas.findRegions(type.atlasKey)
            if (regions.isEmpty){
                Utils.log.error { "No regions found for ${type.atlasKey}" }
                regions = atlas.findRegions("error")
                if (regions.isEmpty) throw GdxRuntimeException("There is no error region in atlas")
            }else{
                Utils.log.debug { "Adding animation of type ${type} with ${regions.size} regions" }
            }
            animation = Animation2d(type,regions,type.playMode,type.speedRate)
            animationCache[type] = animation
        }
        return animation
    }

    override fun entityRemoved(entity: Entity?) {

    }


}
