package cxplugins.cxfundamental.minecraft.swing

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I

interface SwingContainer : SwingComponent {
    fun setComponent(component: SwingComponent)
    fun getTopComponent(position: Vector2I): SwingComponent?
    fun getComponents(position: Vector2I): Array<SwingComponent>
    fun resize(width: Int, height: Int)
    fun setBounds(x: Int, y: Int, w: Int, h: Int)

}