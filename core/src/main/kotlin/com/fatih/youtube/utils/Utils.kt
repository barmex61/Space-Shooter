package com.fatih.youtube.utils

import com.badlogic.ashley.core.Entity
import com.fatih.youtube.SpaceShooter
import com.fatih.youtube.ecs.components.*
import ktx.ashley.get
import ktx.log.Logger
import ktx.log.logger

object Utils {
    val Entity.transform : TransformComponent
        get() = this[TransformComponent.mapper] ?: throw KotlinNullPointerException("There is no transform component")
    val Entity.graphic : GraphicComponent
        get() = this[GraphicComponent.mapper] ?: throw KotlinNullPointerException("There is no graphic component")
    val Entity.facing : FacingComponent
        get() = this[FacingComponent.mapper] ?: throw KotlinNullPointerException("There is no facing component")
    val Entity.player : PlayerComponent
        get() = this[PlayerComponent.mapper] ?: throw KotlinNullPointerException("There is no player component")
    val Entity.remove : RemoveComponent
        get() = this[RemoveComponent.mapper] ?: throw KotlinNullPointerException("There is no player component")
    val Entity.move : MoveComponent
        get() = this[MoveComponent.mapper] ?: throw KotlinNullPointerException("There is no player component")
    val Entity.animation : AnimationComponent
        get() = this[AnimationComponent.mapper] ?: throw KotlinNullPointerException("There is no player component")
    val log : Logger = logger<SpaceShooter>()
    const val UNIT_SCALE = 16f
    const val UPDATE_RATE = 1/25f
    const val V_WIDTH = 9f
    const val V_HEIGHT = 16f
    const val HOR_ACCELERATION = 16.5f
    const val VER_ACCELERATION = 2.25f
    const val MAX_VER_NEG_PLAYER_SPEED = 0.75f
    const val MAX_HOR_SPEED = 5.5f
    const val MAX_VER_POS_PLAYER_SPEED = 5f
    const val DAMAGE_AREA_HEIGHT = 2f
    const val DAMAGE_PER_SECOND = 25f
    const val DEATH_EXPLOSION_DURATION = 0.9f
    const val WINDOW_INFO_UPDATE_RATE = 0.25f
    const val MAX_DELTA_TIME = 1/20f
    const val DEFAULT_FRAME_DURATION = 1 / 20f
}
