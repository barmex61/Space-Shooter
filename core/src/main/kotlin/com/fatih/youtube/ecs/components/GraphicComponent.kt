package com.fatih.youtube.ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Pool.Poolable
import com.fatih.youtube.utils.Utils.UNIT_SCALE
import ktx.ashley.mapperFor

class GraphicComponent : Component , Poolable {

    var sprite = Sprite()

    override fun reset() {
        sprite.apply {
            texture = null
            setColor(1f,1f,1f,1f)
        }
    }

    fun setSpriteRegion(region: TextureRegion){
        sprite.apply {
            setRegion(region)
            setSize(texture.width/UNIT_SCALE,texture.height/ UNIT_SCALE)
            setOriginCenter()
        }
    }

    companion object{
        val mapper = mapperFor<GraphicComponent>()
    }
}
