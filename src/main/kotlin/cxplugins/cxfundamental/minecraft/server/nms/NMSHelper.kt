package cxplugins.cxfundamental.minecraft.server.nms

object NMSHelper {
    /**
     * 通过类名反射获取某一个处于net.minecraft.server中的类
     *
     * @param className 类名
     * @return 此类
     */
    @JvmStatic
    fun getNMSClass(className: String): Class<*> {
        //System.out.println(Bukkit.getServer().getClass().getPackage().getName());
        val version = org.bukkit.Bukkit.getServer().javaClass.getPackage().name.replace(".", ",").split(",".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()[3]
        return Class.forName("net.minecraft.server.$version.$className")

    }

}
