package cxplugins.cxfundamental.minecraft.server.nms.itemnbt

import cxplugins.cxfundamental.minecraft.server.nms.NMSHelper
import java.lang.reflect.Constructor
import java.lang.reflect.Method

class NBTTagBoolean {
    var value: Boolean = true
    lateinit var name: String
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法 类 以及构造器
 *
 */
class NBTTagCompound {
    var value: Any = constructor.newInstance()
    lateinit var name: String
    fun int(lambda: NBTTagInt.() -> Unit) {
        var int = NBTTagInt()
        int.apply(lambda)
        setInt(this.value, int.name, int.value)
    }

    fun boolean(lambda: NBTTagBoolean.() -> Unit) {
        var value = NBTTagBoolean()
        value.apply(lambda)
        setBoolean(this.value, value.name, value.value)
    }

    fun compound(lambda: NBTTagCompound.() -> Unit) {
        var compound = NBTTagCompound()
        compound.apply(lambda)
        set(this.value, compound.name, compound.value)
    }

    fun double(lambda: NBTTagDouble.() -> Unit) {
        var value = NBTTagDouble()
        value.apply(lambda)
        setDouble(this.value, value.name, value.value)
    }

    fun float(lambda: NBTTagFloat.() -> Unit) {
        var value = NBTTagFloat()
        value.apply(lambda)
        setFloat(this.value, value.name, value.value)
    }

    fun short(lambda: NBTTagShort.() -> Unit) {
        var value = NBTTagShort()
        value.apply(lambda)
        setShort(this.value, value.name, value.value)
    }

    fun string(lambda: NBTTagString.() -> Unit) {
        var value = NBTTagString()
        value.apply(lambda)
        setString(this.value, value.name, value.value)
    }

    fun byte(lambda: NBTTagByte.() -> Unit) {
        var value = NBTTagByte()
        value.apply(lambda)
        setByte(this.value, value.name, value.value)
    }

    fun long(lambda: NBTTagLong.() -> Unit) {
        var value = NBTTagLong()
        value.apply(lambda)
        setLong(this.value, value.name, value.value)
    }

    fun intArray(lambda: NBTTagIntArray.() -> Unit) {
        var value = NBTTagIntArray()
        value.apply(lambda)
        setIntArray(this.value, value.name, value.value)
    }

    fun byteArray(lambda: NBTTagByteArray.() -> Unit) {
        var value = NBTTagByteArray()
        value.apply(lambda)
        setByteArray(this.value, value.name, value.value)
    }

    fun tagList(lambda: NBTTagList.() -> Unit) {
        var value = NBTTagList()
        value.apply(lambda)
        set(this.value, value.name, value.value)
    }

    companion object {
        /**
         * 判断某个Compound里面是否有某个值
         *
         * @param compound 这个compound
         * @param key 值的名字
         * @return 若存在 返回true 否则返回false
         */
        @JvmStatic
        fun hasKey(compound: Any, key: String): Boolean {
            return this.hasKey.invoke(compound, key) as Boolean
        }

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagCompound")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的构造器
         *
         */
        @JvmStatic
        var constructor: Constructor<*> = clazz.getConstructor()

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var hasKey: Method

        @JvmStatic
        lateinit var remove: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var get: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getCompound: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getBoolean: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getByte: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getByteArray: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getDouble: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getFloat: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getInt: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getIntArray: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getString: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getLong: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var getShort: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var set: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setBoolean: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setByte: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setByteArray: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setDouble: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setFloat: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setInt: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setIntArray: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setLong: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setShort: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
        @JvmStatic
        lateinit var setString: Method
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagCompound的相关方法
         *
         */
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagList的相关方法 类 以及构造器
 *
 */
class NBTTagList {
    var value = constructor.newInstance()
    lateinit var name: String
    fun compound(lambda: NBTTagCompound.() -> Unit) {
        var compound = NBTTagCompound()
        compound.apply(lambda)
        add(value, compound.value)
    }

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagList的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagList")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagList的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor()

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagList的方法
         *
         */
        @JvmStatic
        lateinit var add: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagList的方法
         *
         */
        @JvmStatic
        lateinit var size: Method

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagList的方法
         *
         */
        @JvmStatic
        lateinit var get: Method
    }

}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTBase的类 方法 构造器
 *
 */
class NBTBase {
    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTBase的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTBase")
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagInt的类 方法 构造器
 *
 */
class NBTTagInt {
    var value = 0
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagInt的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagInt")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagInt的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Int::class.java)
    }

}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagShort的类 方法 构造器
 *
 */
class NBTTagShort {
    var value: Short = 0
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagShort的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagShort")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagShort的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Short::class.java)
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagString的类 方法 构造器
 *
 */
class NBTTagString {
    lateinit var value: String
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagString的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagString")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagString的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(String::class.java)
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagFloat的类 方法 构造器
 *
 */
class NBTTagFloat {
    var value: Float = 0.0f
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagFloat的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagFloat")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagFloat的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Float::class.java)
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagLong的类 方法 构造器
 *
 */
class NBTTagLong {
    var value: Long = 0
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagLong的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagLong")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagLong的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Long::class.java)
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagByte的类 方法 构造器
 *
 */
class NBTTagByte {
    var value: Byte = 0
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagByte的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagByte")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagByte的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Byte::class.java)
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagDouble的类 方法 构造器
 *
 */
class NBTTagDouble {
    var value: Double = 0.0
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagDouble的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagDouble")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagDouble的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Double::class.java)
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagIntArray的类 方法 构造器
 *
 */
class NBTTagIntArray {
    lateinit var value: IntArray
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagIntArray的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagIntArray")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagIntArray的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(IntArray::class.java)
    }
}

/**
 * 反射的net.minecraft.server.vX_X_XRX.NBTTagByteArray的类 方法 构造器
 *
 */
class NBTTagByteArray {
    lateinit var value: ByteArray
    lateinit var name: String

    companion object {
        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagByteArray的类
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagByteArray")

        /**
         * 反射的net.minecraft.server.vX_X_XRX.NBTTagByteArray的构造器
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(ByteArray::class.java)
    }
}