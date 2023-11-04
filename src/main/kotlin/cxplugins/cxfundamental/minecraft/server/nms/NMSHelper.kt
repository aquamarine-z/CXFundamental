package cxplugins.cxfundamental.minecraft.server.nms

object NMSHelper {
    /**
     * ͨ�����������ȡĳһ������net.minecraft.server�е���
     *
     * @param className ����
     * @return ����
     */
    @JvmStatic
    fun getNMSClass(className: String): Class<*> {
        //System.out.println(Bukkit.getServer().getClass().getPackage().getName());
        val version = org.bukkit.Bukkit.getServer().javaClass.getPackage().name.replace(".", ",").split(",".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()[3]
        return Class.forName("net.minecraft.server.$version.$className")

    }

}
