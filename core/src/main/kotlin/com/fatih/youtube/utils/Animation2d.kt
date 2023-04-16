package com.fatih.youtube.utils

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.fatih.youtube.ecs.types.AnimationType
import com.fatih.youtube.utils.Utils.DEFAULT_FRAME_DURATION

class Animation2d(val type : AnimationType,keyFrames : com.badlogic.gdx.utils.Array<TextureAtlas.AtlasRegion>,playMode: PlayMode,speedRate : Float = 1f) : Animation<TextureRegion>(DEFAULT_FRAME_DURATION / speedRate, keyFrames , playMode)



