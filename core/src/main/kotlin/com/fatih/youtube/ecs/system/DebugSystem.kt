package com.fatih.youtube.ecs.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IntervalIteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.fatih.youtube.ecs.components.PlayerComponent
import com.fatih.youtube.ecs.components.TransformComponent
import com.fatih.youtube.utils.Utils.WINDOW_INFO_UPDATE_RATE
import com.fatih.youtube.utils.Utils.player
import com.fatih.youtube.utils.Utils.transform
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.getSystem
import kotlin.math.max
import kotlin.math.min

class DebugSystem : IntervalIteratingSystem(allOf(PlayerComponent::class).get(),WINDOW_INFO_UPDATE_RATE) {

    init {
        setProcessing(true)
    }

    override fun processEntity(entity: Entity) {
        val transformComponent = entity.transform
        val playerComponent = entity.player

        when{
            Gdx.input.isKeyPressed(Input.Keys.NUM_1)->{
                transformComponent.position.y = 1f
                playerComponent.life = 1f
                playerComponent.shield = 0f
            }
            Gdx.input.isKeyPressed(Input.Keys.NUM_2)->{
                playerComponent.shield = min(playerComponent.maxShield,playerComponent.shield + 25f)
            }
            Gdx.input.isKeyPressed(Input.Keys.NUM_3)->{
                playerComponent.shield = max(0f,playerComponent.shield - 25f)
            }
            Gdx.input.isKeyPressed(Input.Keys.NUM_4)->{
                engine.getSystem<MoveSystem>().setProcessing(false)
            }
            Gdx.input.isKeyPressed(Input.Keys.NUM_5)->{
                engine.getSystem<MoveSystem>().setProcessing(true)
            }
        }

        Gdx.graphics.setTitle("DM Debug - pos : ${transformComponent.position}, life : ${playerComponent.life} shield : ${playerComponent.shield}")
    }
}
