package cxplugins.cxfundamental.minecraft.server.nms


import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.*
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Method

/**
 * 操作物品NBT数据提供的类
 */
object NBTEditor {
    /**
     * 设置一个物品的NBT数据
     *
     * @param itemStack 需要设置的物品
     * @param value 这个nbt的数值
     * @param compoundPath nbt的路径 可以递归设置
     * @return 设置好的物品
     */
    @JvmStatic
    fun setItemStackNBT(itemStack: ItemStack, value: Any?, vararg compoundPath: String): ItemStack {
        var nmsItem = CraftItemStack.asNMSCopy.invoke(null, itemStack)
        var compound = if (NMSItemStack.hasTag(nmsItem)) NMSItemStack.getTag(nmsItem)
        else NBTTagCompound.constructor.newInstance()
        setValueInCompound(compound, value, *compoundPath)
        NMSItemStack.setTag(nmsItem, compound)
        return CraftItemStack.asBukkitCopy(null, nmsItem) as ItemStack
    }

    /**
     * 获取一个物品的NBT数据
     *
     * @param itemStack 需要获取的物品
     * @param compoundPath nbt的路径 可以递归获取
     * @return 获取的数值
     */
    @JvmStatic
    fun getItemStackNBT(itemStack: ItemStack, vararg compoundPath: String): Any? {
        var nmsItem = CraftItemStack.asNMSCopy.invoke(null, itemStack)
        var compound = if (NMSItemStack.hasTag(nmsItem)) {
            NMSItemStack.getTag(nmsItem)

        } else {
            NBTTagCompound.constructor.newInstance()
            return null
        }
        return getValueInCompound(compound, *compoundPath)
    }

    /**
     * 设置一个NBT组合中的某个值
     *
     * @param compound 需要设置的组合
     * @param compoundPath nbt的路径 可以递归设置
     */
    @JvmStatic
    fun setValueInCompound(compound: Any, value: Any?, vararg compoundPath: String) {
        if (compoundPath.size <= 1) {
            when (value) {
                is Int -> {
                    var baseValue = NBTTagInt.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)
                }

                is Double -> {
                    var baseValue = NBTTagDouble.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)
                }

                is Long -> {
                    var baseValue = NBTTagLong.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)
                }

                is Short -> {
                    var baseValue = NBTTagShort.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)
                }

                is Boolean -> {
                    NBTTagCompound.setBoolean(compound, compoundPath[0], value)
                }

                is Float -> {
                    var baseValue = NBTTagFloat.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)
                }

                is String -> {
                    var baseValue = NBTTagString.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)
                    //var a:net.minecraft.server.v1_12_R1.NBTTagInt
                }

                is Byte -> {
                    var baseValue = NBTTagByte.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)

                }

                is ByteArray -> {
                    var baseValue = NBTTagByteArray.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)

                }

                is IntArray -> {
                    var baseValue = NBTTagIntArray.constructor.newInstance(value)
                    NBTTagCompound.set(compound, compoundPath[0], baseValue)

                }

                null -> {

                    NBTTagCompound.remove(compound, compoundPath[0])
                }

                else -> {
                    return

                }
            }
        } else {
            if (hasKeyInCompound(compound, compoundPath[0])) {
                var newCompound = NBTTagCompound.getCompound(compound, compoundPath[0])
                setValueInCompound(
                    newCompound,
                    value,
                    *compoundPath.toList().subList(1, compoundPath.size).toTypedArray()
                )
                NBTTagCompound.set(compound, compoundPath[0], newCompound)
            } else {
                var newCompound = NBTTagCompound.constructor.newInstance()
                setValueInCompound(
                    newCompound,
                    value,
                    *compoundPath.toList().subList(1, compoundPath.size).toTypedArray()
                )
                NBTTagCompound.set(compound, compoundPath[0], newCompound)
            }
        }
    }

    /**
     * 获取一个NBT组合中的某个值
     *
     * @param compound 需要获取的组合
     * @param compoundPath nbt的路径 可以递归获取
     */
    @JvmStatic
    fun getValueInCompound(compound: Any, vararg compoundPath: String): Any? {
        if (compoundPath.size <= 1) {
            var result = NBTTagCompound.get(compound, compoundPath[0])
            if (result == null) return null
            //println(NBTTagFloat.clazz::kotlin::returnType)
            when {
                result::class.java == NBTTagFloat.clazz -> {
                    return NBTTagCompound.getFloat(compound, compoundPath[0])
                }

                result::class.java == NBTTagDouble.clazz -> {
                    return NBTTagCompound.getDouble(compound, compoundPath[0])
                }

                result::class.java == NBTTagInt.clazz -> {
                    return NBTTagCompound.getInt(compound, compoundPath[0])
                }

                result::class.java == NBTTagIntArray.clazz -> {
                    return NBTTagCompound.getIntArray(compound, compoundPath[0])
                }

                result::class.java == NBTTagByte.clazz -> {
                    return NBTTagCompound.getByte(compound, compoundPath[0])
                }

                result::class.java == NBTTagByteArray.clazz -> {
                    return NBTTagCompound.getByteArray(compound, compoundPath[0])
                }

                result::class.java == NBTTagString.clazz -> {
                    return NBTTagCompound.getString(compound, compoundPath[0])
                }

                result::class.java == NBTTagLong.clazz -> {
                    return NBTTagCompound.getLong(compound, compoundPath[0])
                }

                result::class.java == NBTTagShort.clazz -> {
                    return NBTTagCompound.getShort(compound, compoundPath[0])
                }

                result::class.java == NBTTagCompound.clazz -> {
                    return NBTTagCompound.getCompound(compound, compoundPath[0])
                }

                else -> {
                    //print(1)
                    return result
                }
            }

        } else {
            if (NBTTagCompound.hasKey(compound, compoundPath[0])) {
                var comp = NBTTagCompound.getCompound(compound, compoundPath[0])
                return getValueInCompound(comp, *compoundPath.toList().subList(1, compoundPath.size).toTypedArray())
            } else return null
        }
    }

    /**
     * 判断某个组合里面是否包含某个值
     *
     * @param compound 需要判断的组合
     * @param key 值的名字
     * @return 若存在 则返回true 否则返回false
     */
    @JvmStatic
    fun hasKeyInCompound(compound: Any, vararg key: String): Boolean {
        if (key.size <= 1) {
            return NBTTagCompound.hasKey(compound, key[0])
        } else {
            var k = key[0]
            if (NBTTagCompound.hasKey(compound, k)) {
                return hasKeyInCompound(compound, *key.toList().subList(1, key.size).toTypedArray())
            } else return false
        }
    }

    @JvmStatic
    private fun hasItemStackNBTCompound(itemStack: ItemStack, vararg compoundPath: String): Boolean {
        var nmsItem = CraftItemStack.asNMSCopy.invoke(null, itemStack)
        var compound = NBTTagCompound.get.invoke(nmsItem)
        return hasKeyInCompound(compound, *compoundPath)
    }

    /**
     * 获取某个物品的属性列表
     *
     * @param itemStack 需要获取属性的物品
     * @return 属性列表
     */
    @JvmStatic
    fun getItemAttributeModifiers(itemStack: ItemStack): List<AttributeModifier> {
        var nmsItem = CraftItemStack.asNMSCopy.invoke(null, itemStack)
        var mainCompound = if (NMSItemStack.hasTag(nmsItem)) {
            NMSItemStack.getTag(nmsItem)
        } else {
            return emptyList()
        }
        var modifiers = if (NBTTagCompound.hasKey(mainCompound, "AttributeModifiers")) NBTTagCompound.get(
            mainCompound,
            "AttributeModifiers"
        )
        else {
            return emptyList()
        }
        var result = ArrayList<AttributeModifier>()
        var modifierAmount = NBTTagList.size(modifiers) as Int

        for (i in 0..modifierAmount) {
            var attributeModifier = AttributeModifier("", 1.0, ItemAttribute.GENERIC_AMOUR)
            var compound = NBTTagList.get(modifiers, i)
            if (NBTTagCompound.hasKey(compound, "AttributeName")) {
                attributeModifier.attribute =
                    ItemAttribute.getValueFromString(NBTTagCompound.getString(compound, "AttributeName") as String)!!
            }
            if (NBTTagCompound.hasKey(compound, "Name")) {
                attributeModifier.name = NBTTagCompound.getString(compound, "Name") as String
            }
            if (NBTTagCompound.hasKey(compound, "Operation")) {
                attributeModifier.operation =
                    AttributeOperation.getValueFromTypeId((NBTTagCompound.getInt(compound, "Name") as Int).toShort())!!
            }
            if (NBTTagCompound.hasKey(compound, "Amount")) {
                attributeModifier.amount = NBTTagCompound.getDouble(compound, "Amount") as Double
            }
            if (NBTTagCompound.hasKey(compound, "UUIDLeast")) {
                attributeModifier.UUIDLeast = NBTTagCompound.getLong(compound, "UUIDLeast") as Long
            }
            if (NBTTagCompound.hasKey(compound, "UUIDMost")) {
                attributeModifier.UUIDMost = NBTTagCompound.getLong(compound, "UUIDMost") as Long
            }
            if (NBTTagCompound.hasKey(compound, "Slot")) {
                attributeModifier.slot =
                    ItemSlot.getValueFromString(NBTTagCompound.getString(compound, "Slot") as String)!!
            }
            result.add(attributeModifier)
        }
        return result
    }

    /**
     * 添加某个物品的属性
     *
     * @param itemStack 需要添加属性的物品
     * @param modifier 添加的属性
     * @return 添加后的物品
     */
    @JvmStatic
    fun setItemAttribute(itemStack: ItemStack, modifier: AttributeModifier): ItemStack {
        var nmsItem = CraftItemStack.asNMSCopy.invoke(null, itemStack)
        var mainCompound = if (NMSItemStack.hasTag(nmsItem)) {
            NMSItemStack.getTag(nmsItem)
        } else {
            NBTTagCompound.constructor.newInstance()
        }
        var modifers = if (NBTTagCompound.hasKey(mainCompound, "AttributeModifiers")) NBTTagCompound.get(
            mainCompound,
            "AttributeModifiers"
        )
        else NBTTagList.constructor.newInstance()
        var modiferCompound = NBTTagCompound.constructor.newInstance()
        NBTTagCompound.set(
            modiferCompound,
            "AttributeName",
            NBTTagString.constructor.newInstance(modifier.attribute.attributeName)
        )
        NBTTagCompound.setString(modiferCompound, "Name", modifier.name)
        NBTTagCompound.setDouble(modiferCompound, "Amount", modifier.amount)
        NBTTagCompound.setInt(modiferCompound, "Operation", modifier.operation.typeId.toInt())
        //var least=Random().nextLong()
        NBTTagCompound.setLong(modiferCompound, "UUIDLeast", modifier.UUIDLeast)
        NBTTagCompound.setLong(modiferCompound, "UUIDMost", modifier.UUIDMost)
        if (modifier.slot != ItemSlot.INVENTORY) {
            NBTTagCompound.set(modiferCompound, "Slot", NBTTagString.constructor.newInstance(modifier.slot.slotName))
        }
        NBTTagList.add(modifers, modiferCompound)
        NBTTagCompound.set(mainCompound, "AttributeModifiers", modifers)
        NMSItemStack.setTag(nmsItem, mainCompound)
        return CraftItemStack.asBukkitCopy(null, nmsItem) as ItemStack
    }


    @JvmStatic
    fun getMethods() {
        CraftItemStack.asNMSCopy = CraftItemStack.clazz.getMethod("asNMSCopy", ItemStack::class.java)
        CraftItemStack.asBukkitCopy = CraftItemStack.clazz.getMethod("asBukkitCopy", NMSItemStack.clazz)

        NMSItemStack.hasTag = NMSItemStack.clazz!!.getMethod("hasTag")
        NMSItemStack.getTag = NMSItemStack.clazz!!.getMethod("getTag")
        NMSItemStack.setTag = NMSItemStack.clazz!!.getMethod("setTag", NBTTagCompound.clazz)

        NBTTagCompound.remove = NBTTagCompound.clazz.getMethod("remove", String::class.java)
        NBTTagCompound.hasKey = NBTTagCompound.clazz.getMethod("hasKey", String::class.java)
        NBTTagCompound.get = NBTTagCompound.clazz.getMethod("get", String::class.java)
        NBTTagCompound.getCompound = NBTTagCompound.clazz.getMethod("getCompound", String::class.java)
        NBTTagCompound.getInt = NBTTagCompound.clazz.getMethod("getInt", String::class.java)
        NBTTagCompound.getIntArray = NBTTagCompound.clazz.getMethod("getIntArray", String::class.java)
        NBTTagCompound.getDouble = NBTTagCompound.clazz.getMethod("getDouble", String::class.java)
        NBTTagCompound.getFloat = NBTTagCompound.clazz.getMethod("getFloat", String::class.java)
        NBTTagCompound.getByte = NBTTagCompound.clazz.getMethod("getByte", String::class.java)
        NBTTagCompound.getByteArray = NBTTagCompound.clazz.getMethod("getByteArray", String::class.java)
        NBTTagCompound.getLong = NBTTagCompound.clazz.getMethod("getLong", String::class.java)
        NBTTagCompound.getShort = NBTTagCompound.clazz.getMethod("getShort", String::class.java)
        NBTTagCompound.getString = NBTTagCompound.clazz.getMethod("getString", String::class.java)
        NBTTagCompound.getBoolean = NBTTagCompound.clazz.getMethod("getBoolean", String::class.java)
        NBTTagCompound.set = NBTTagCompound.clazz.getMethod("set", String::class.java, NBTBase.clazz)
        NBTTagCompound.setBoolean =
            NBTTagCompound.clazz.getMethod("setBoolean", String::class.java, Boolean::class.javaPrimitiveType)
        NBTTagCompound.setByte =
            NBTTagCompound.clazz.getMethod("setByte", String::class.java, Byte::class.javaPrimitiveType)
        NBTTagCompound.setByteArray =
            NBTTagCompound.clazz.getMethod("setByteArray", String::class.java, ByteArray::class.java)
        NBTTagCompound.setDouble =
            NBTTagCompound.clazz.getMethod("setDouble", String::class.java, Double::class.javaPrimitiveType)
        NBTTagCompound.setFloat =
            NBTTagCompound.clazz.getMethod("setFloat", String::class.java, Float::class.javaPrimitiveType)
        NBTTagCompound.setInt =
            NBTTagCompound.clazz.getMethod("setInt", String::class.java, Int::class.javaPrimitiveType)
        NBTTagCompound.setIntArray =
            NBTTagCompound.clazz.getMethod("setIntArray", String::class.java, IntArray::class.java)
        NBTTagCompound.setLong =
            NBTTagCompound.clazz.getMethod("setLong", String::class.java, Long::class.javaPrimitiveType)
        NBTTagCompound.setShort =
            NBTTagCompound.clazz.getMethod("setShort", String::class.java, Short::class.javaPrimitiveType)
        NBTTagCompound.setString = NBTTagCompound.clazz.getMethod("setString", String::class.java, String::class.java)

        NBTTagList.add = NBTTagList.clazz.getMethod("add", NBTBase.clazz)
        NBTTagList.size = NBTTagList.clazz.getMethod("size")
        NBTTagList.get = NBTTagList.clazz.getMethod("get", Int::class.java)
    }

    init {
        getMethods()
    }
}

/**
 * 反射的net.minecraft.server.版本.ItemStack类及其相关方法
 */
object NMSItemStack {
    /**
     * 反射的net.minecraft.server.版本.ItemStack类
     */
    @JvmStatic
    var clazz: Class<*>? = NMSHelper.getNMSClass("ItemStack")

    /**
     * 反射的net.minecraft.server.版本.ItemStack.hasTag方法
     */
    @JvmStatic
    lateinit var hasTag: Method

    /**
     * 判断某个物品是否有附加值
     *
     * @param item 这个物品 类型为net.minecraft.server.版本.ItemStack
     * @return 若有附加值 返回true 否则返回false
     */
    @JvmStatic
    fun hasTag(item: Any): Boolean {
        return hasTag.invoke(item) as Boolean
    }

    /**
     * 反射的net.minecraft.server.版本.ItemStack.getTag方法
     */
    @JvmStatic
    lateinit var getTag: Method

    /**
     * 获取某个物品的附加值
     *
     * @param item 这个物品 类型为net.minecraft.server.版本.ItemStack
     * @return 获取到的附加值
     */
    @JvmStatic
    fun getTag(item: Any): Any {
        return getTag.invoke(item)
    }

    /**
     * 反射的net.minecraft.server.版本.ItemStack.setTag方法
     */
    @JvmStatic
    lateinit var setTag: Method

    /**
     * 设置某个物品的附加值
     *
     * @param item 这个物品 类型为net.minecraft.server.版本.ItemStack
     */
    @JvmStatic
    fun setTag(item: Any, tag: Any) {
        setTag.invoke(item, tag)
    }

}

/**
 * 反射的org.bukkit.craftbukkit.版本.inventory.CraftItemStack类及其相关方法
 */
object CraftItemStack {
    /**
     * 反射的org.bukkit.craftbukkit.版本.inventory.CraftItemStack类
     */
    @JvmStatic
    var clazz: Class<*> = OBCHelper.getOBCClass("inventory.CraftItemStack")

    /**
     * 反射的org.bukkit.craftbukkit.版本.inventory.CraftItemStack类的asNMSCopy方法 将一个Bukkit的itemstack转为NMS的itemstack
     */
    @JvmStatic
    lateinit var asNMSCopy: Method

    /**
     * 反射的org.bukkit.craftbukkit.版本.inventory.CraftItemStack类的asBukkitCopy方法 将一个NMS的itemstack转为Bukkit的itemstack
     */
    @JvmStatic
    lateinit var asBukkitCopy: Method
}



