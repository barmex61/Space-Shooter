package com.fatih.youtube.ecs.types

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode

enum class AnimationType(val atlasKey : String, val playMode: PlayMode = PlayMode.LOOP, val speedRate : Float = 1f) {
    DARK_MATTER("dark_matter", speedRate = 3f),
    NONE("")
}
