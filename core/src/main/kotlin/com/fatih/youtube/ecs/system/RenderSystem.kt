package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.fatih.youtube.ecs.components.GraphicComponent
import com.fatih.youtube.ecs.components.TransformComponent
import com.fatih.youtube.utils.Utils.graphic
import com.fatih.youtube.utils.Utils.log
import com.fatih.youtube.utils.Utils.transform
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.graphics.use

class RenderSystem(
    private val spriteBatch: Batch,
    private val viewPort : Viewport
) : SortedIteratingSystem(
    allOf(TransformComponent::class,GraphicComponent::class).get(),
    compareBy{entity ->
        entity[TransformComponent.mapper]
    }
) {

    override fun update(deltaTime: Float) {
        forceSort()
        viewPort.apply()
        spriteBatch.use(viewPort.camera.combined) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transformComponent = entity.transform
        val graphicComponent = entity.graphic
        if (graphicComponent.sprite.texture == null){
            log.error { "Entity has no texture for rendering entity = $entity" }
            return
        }
        graphicComponent.sprite.apply {
            rotation = transformComponent.rotationDegree
            setBounds(transformComponent.interpolatedPosition.x,transformComponent.position.y,transformComponent.size.x,transformComponent.size.y)
            draw(spriteBatch)
        }
    }
}

