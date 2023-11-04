package cxplugins.cxfundamental.minecraft.swing

typealias SwingContainer = CXContainer

interface CXContainer : CXComponent {

    fun getComponent(x: Int, y: Int): CXComponent?
    fun getComponent(location: Int): CXComponent?
    fun setComponent(location: Int, element: CXComponent?)
    fun setComponent(x: Int, y: Int, element: CXComponent?)
    fun getBaseComponent(x: Int, y: Int): CXComponent?
    fun getBaseComponent(location: Int): CXComponent?
    fun getWidth(): Int
    fun getHeight(): Int
}