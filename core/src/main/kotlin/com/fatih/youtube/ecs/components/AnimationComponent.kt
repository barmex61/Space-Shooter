package com.fatih.youtube.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import com.fatih.youtube.ecs.types.AnimationType
import com.fatih.youtube.utils.Animation2d
import ktx.ashley.mapperFor

class AnimationComponent : Component , Poolable {

    var type : AnimationType = AnimationType.NONE
    var stateTime = 0f
    lateinit var animation : Animation2d

    override fun reset() {
        type = AnimationType.NONE
        stateTime = 0f
    }

    companion object{
        val mapper = mapperFor<AnimationComponent>()
    }
}
