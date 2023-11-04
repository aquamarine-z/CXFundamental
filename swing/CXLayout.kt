package cxplugins.cxfundamental.minecraft.swing

typealias SwingLayout = CXLayout

abstract class CXLayout {
    abstract fun calculateLocation(x: Int, y: Int): Pair<Int, Int>

    companion object {
        val NullLayout = object : CXLayout() {
            override fun calculateLocation(x: Int, y: Int): Pair<Int, Int> {
                return Pair(x, y)
            }

        }
    }
}