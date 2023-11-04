package cxplugins.cxfundamental.minecraft.server.nms

object OBCHelper {
    /**
     * 通过类名反射获取某一个处于org.bukkit.craftbukkit中的类
     *
     * @param className 类名
     * @return 此类
     */
    @JvmStatic
    fun getOBCClass(className: String): Class<*> {
        val version = org.bukkit.Bukkit.getServer().javaClass.getPackage().name.replace(".", ",").split(",".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()[3]
        return Class.forName("org.bukkit.craftbukkit.$version.$className")

    }

}
