package com.fatih.youtube.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import com.fatih.youtube.ecs.types.FacingDirection
import ktx.ashley.mapperFor

class FacingComponent : Component , Poolable{

    var facingDirection = FacingDirection.DEFAULT

    override fun reset() {
        facingDirection = FacingDirection.DEFAULT
    }

    companion object{
        val mapper = mapperFor<FacingComponent>()
    }
}
