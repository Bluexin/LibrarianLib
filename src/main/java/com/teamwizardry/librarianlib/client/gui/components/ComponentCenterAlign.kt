package com.teamwizardry.librarianlib.client.gui.components

import com.teamwizardry.librarianlib.client.gui.GuiComponent
import com.teamwizardry.librarianlib.common.util.div
import com.teamwizardry.librarianlib.common.util.math.Vec2d
import com.teamwizardry.librarianlib.common.util.plus
import com.teamwizardry.librarianlib.common.util.times

class ComponentCenterAlign(posX: Int, posY: Int, var centerHorizontal: Boolean, var centerVertical: Boolean) : GuiComponent<ComponentCenterAlign>(posX, posY) {

    override fun drawComponent(mousePos: Vec2d, partialTicks: Float) {
        //NO-OP
    }

    override fun draw(mousePos: Vec2d, partialTicks: Float) {
        if (centerHorizontal || centerVertical) {
            for (component in components) {
                val compPos = component.pos
                val bb = component.getLogicalSize()
                bb ?: continue
                val centerPos = (bb.max - bb.min) / 2 + (bb.min - compPos)
                var adjustedPos = centerPos * -1.0
                if (!centerHorizontal)
                    adjustedPos = adjustedPos.setX(compPos.x)
                if (!centerVertical)
                    adjustedPos = adjustedPos.setY(compPos.y)
                component.pos = adjustedPos
            }
        }

        super.draw(mousePos, partialTicks)
    }

}
