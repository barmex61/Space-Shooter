package com.fatih.youtube.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Pool.Poolable
import ktx.ashley.mapperFor

class TransformComponent : Component, Poolable , Comparable<TransformComponent> {

    val position = Vector3()
    val size = Vector2(1f,1f)
    var rotationDegree = 0f
    var prevPosition = Vector3()
    val interpolatedPosition = Vector3()

    override fun reset() {
        position.set(Vector3.Zero)
        prevPosition.set(Vector3.Zero)
        interpolatedPosition.set(Vector3.Zero)
        size.set(1f,1f)
        rotationDegree = 0f
    }

    override fun compareTo(other: TransformComponent): Int {
        val zDiff :Int = other.position.z.compareTo(position.z)
        return (if (zDiff == 0) other.position.y.compareTo(position.y) else zDiff)
    }

    companion object{
        val mapper = mapperFor<TransformComponent>()
    }

}


