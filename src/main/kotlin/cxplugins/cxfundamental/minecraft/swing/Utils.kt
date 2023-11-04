package cxplugins.cxfundamental.minecraft.swing

inline fun <reified T> createMatrixOfNulls(x: Int, y: Int): Array<Array<T?>> {
    val matrix = Array<Array<T?>>(x) {
        arrayOfNulls(y)
    }
    return matrix
}