package com.fatih.youtube.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import ktx.ashley.mapperFor

class PlayerComponent : Component , Poolable {

    var life : Float = 100f
    var maxLife : Float = 100f
    var shield : Float = 0f
    var maxShield : Float = 100f
    var distance : Float = 0f

    override fun reset() {
        life = 100f
        maxLife = 100f
        shield  = 0f
        maxShield = 100f
        distance = 0f
    }

    companion object{
        val mapper = mapperFor<PlayerComponent>()
    }
}
