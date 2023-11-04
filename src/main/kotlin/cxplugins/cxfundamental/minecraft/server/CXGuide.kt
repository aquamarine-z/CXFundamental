package cxplugins.cxfundamental.minecraft.server

/**
 * 提供了一些关于该版本CXFundamental的基础信息的类
 */
object CXGuide {
    @JvmStatic
            /**
             * 此插件的版本号
             */
    val version: String
        get() = "Version: CXFundamental 1.6.4-Unstable"

    @JvmStatic
            /**
             * 此版本新增的方法
             */
    val newMethods: List<String>
        get() {
            val result = ArrayList<String>()
            return result
        }

    @JvmStatic
            /**
             * 此版本有所修改的方法
             */
    val changedMethods: List<String>
        get() {
            val result = ArrayList<String>()

            return result
        }

    @JvmStatic
            /**
             * 此版本的简略介绍
             */
    val newVersionDescription: String
        get() {
            val result = ("Version Update")
            return result
        }

}
