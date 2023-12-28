package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.swing.SwingComponent

fun SwingComponent.location(x: Int, y: Int) {
    this.position.x = x
    this.position.y = y
}

fun SwingComponent.location(point: Vector2I) {
    this.position.x = point.x
    this.position.y = point.y
}
fun SwingComponent.id(id: String?) {
    this.id = id
}