package cxplugins.cxfundamental.minecraft.server.nms

object OBCHelper {
    /**
     * ͨ�����������ȡĳһ������org.bukkit.craftbukkit�е���
     *
     * @param className ����
     * @return ����
     */
    @JvmStatic
    fun getOBCClass(className: String): Class<*> {
        val version = org.bukkit.Bukkit.getServer().javaClass.getPackage().name.replace(".", ",").split(",".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()[3]
        return Class.forName("org.bukkit.craftbukkit.$version.$className")

    }

}
