package cxplugins.cxfundamental.minecraft.server.nms.itemnbt

import cxplugins.cxfundamental.minecraft.server.nms.NMSHelper
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.*
import java.lang.reflect.Constructor
import java.lang.reflect.Method
class NBTTagBoolean{
    var value:Boolean=true
    lateinit var name:String
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط��� �� �Լ�������
 *
 */
class NBTTagCompound {
    var value:Any=NBTTagCompound.constructor.newInstance()
    lateinit var name:String
    fun int(lambda:NBTTagInt.()->Unit){
        var int=NBTTagInt()
        int.apply(lambda)
        NBTTagCompound.setInt(this.value,int.name,int.value)
    }
    fun boolean(lambda:NBTTagBoolean.()->Unit){
        var value=NBTTagBoolean()
        value.apply(lambda)
        NBTTagCompound.setBoolean(this.value,value.name,value.value)
    }
    fun compound(lambda: NBTTagCompound.() -> Unit){
        var compound=NBTTagCompound()
        compound.apply(lambda)
        NBTTagCompound.set(this.value,compound.name,compound.value)
    }
    fun double(lambda:NBTTagDouble.()->Unit){
        var value=NBTTagDouble()
        value.apply(lambda)
        NBTTagCompound.setDouble(this.value,value.name,value.value)
    }
    fun float(lambda:NBTTagFloat.()->Unit){
        var value=NBTTagFloat()
        value.apply(lambda)
        NBTTagCompound.setFloat(this.value,value.name,value.value)
    }
    fun short(lambda:NBTTagShort.()->Unit){
        var value=NBTTagShort()
        value.apply(lambda)
        NBTTagCompound.setShort(this.value,value.name,value.value)
    }
    fun string(lambda:NBTTagString.()->Unit){
        var value=NBTTagString()
        value.apply(lambda)
        NBTTagCompound.setString(this.value,value.name,value.value)
    }
    fun byte(lambda:NBTTagByte.()->Unit){
        var value=NBTTagByte()
        value.apply(lambda)
        NBTTagCompound.setByte(this.value,value.name,value.value)
    }
    fun long(lambda:NBTTagLong.()->Unit){
        var value=NBTTagLong()
        value.apply(lambda)
        NBTTagCompound.setLong(this.value,value.name,value.value)
    }
    fun intArray(lambda:NBTTagIntArray.()->Unit){
        var value=NBTTagIntArray()
        value.apply(lambda)
        NBTTagCompound.setIntArray(this.value,value.name,value.value)
    }
    fun byteArray(lambda:NBTTagByteArray.()->Unit){
        var value=NBTTagByteArray()
        value.apply(lambda)
        NBTTagCompound.setByteArray(this.value,value.name,value.value)
    }
    fun tagList(lambda:NBTTagList.()->Unit){
        var value=NBTTagList()
        value.apply(lambda)
        NBTTagCompound.set(this.value,value.name,value.value)
    }
    companion object{
        /**
         * �ж�ĳ��Compound�����Ƿ���ĳ��ֵ
         *
         * @param compound ���compound
         * @param key ֵ������
         * @return ������ ����true ���򷵻�false
         */
        @JvmStatic
        fun hasKey(compound: Any, key: String): Boolean {
            return this.hasKey!!.invoke(compound, key) as Boolean
        }

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagCompound")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound�Ĺ�����
         *
         */
        @JvmStatic
        var constructor: Constructor<*> = clazz.getConstructor()

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var hasKey: Method
        @JvmStatic
        lateinit var remove: Method
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var get: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getCompound: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getBoolean: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getByte: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getByteArray: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getDouble: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getFloat: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getInt: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getIntArray: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getString: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getLong: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var getShort: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var set: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setBoolean: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setByte: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setByteArray: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setDouble: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setFloat: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setInt: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setIntArray: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setLong: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setShort: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
        @JvmStatic
        lateinit var setString: Method
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagCompound����ط���
         *
         */
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagList����ط��� �� �Լ�������
 *
 */
class NBTTagList {
    var value=NBTTagList.constructor.newInstance()
    lateinit var name:String
    fun compound(lambda:NBTTagCompound.()->Unit){
        var compound=NBTTagCompound()
        compound.apply(lambda)
        NBTTagList.add(value,compound.value)
    }
    companion object{
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagList����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagList")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagList�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor()

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagList�ķ���
         *
         */
        @JvmStatic
        lateinit var add: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagList�ķ���
         *
         */
        @JvmStatic
        lateinit var size: Method

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagList�ķ���
         *
         */
        @JvmStatic
        lateinit var get: Method
    }

}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTBase���� ���� ������
 *
 */
class NBTBase {
    companion object{
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTBase����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTBase")
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagInt���� ���� ������
 *
 */
class NBTTagInt {
    var value=0
    lateinit var name:String
    companion object{
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagInt����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagInt")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagInt�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Int::class.java)
    }

}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagShort���� ���� ������
 *
 */
class NBTTagShort{
    var value:Short=0
    lateinit var name:String
    companion object{
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagShort����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagShort")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagShort�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Short::class.java)
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagString���� ���� ������
 *
 */
class NBTTagString{
    lateinit var value:String
    lateinit var name:String
    companion object {
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagString����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagString")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagString�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(String::class.java)
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagFloat���� ���� ������
 *
 */
class NBTTagFloat{
    var value:Float=0.0f
    lateinit var name:String
    companion object {
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagFloat����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagFloat")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagFloat�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Float::class.java)
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagLong���� ���� ������
 *
 */
class NBTTagLong{
    var value:Long=0
    lateinit var name:String
    companion object {
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagLong����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagLong")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagLong�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Long::class.java)
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagByte���� ���� ������
 *
 */
class NBTTagByte{
    var value:Byte=0
    lateinit var name:String
    companion object {
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagByte����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagByte")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagByte�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Byte::class.java)
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagDouble���� ���� ������
 *
 */
class NBTTagDouble {
    var value:Double=0.0
    lateinit var name:String
    companion object {
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagDouble����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagDouble")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagDouble�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(Double::class.java)
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagIntArray���� ���� ������
 *
 */
class NBTTagIntArray {
    lateinit var value:IntArray
    lateinit var name:String

    companion object {
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagIntArray����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagIntArray")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagIntArray�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(IntArray::class.java)
    }
}
/**
 * �����net.minecraft.server.vX_X_XRX.NBTTagByteArray���� ���� ������
 *
 */
class NBTTagByteArray {
    lateinit var value:ByteArray
    lateinit var name:String
    companion object {
        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagByteArray����
         *
         */
        @JvmStatic
        var clazz: Class<*> = NMSHelper.getNMSClass("NBTTagByteArray")

        /**
         * �����net.minecraft.server.vX_X_XRX.NBTTagByteArray�Ĺ�����
         *
         */
        @JvmStatic
        var constructor = clazz.getConstructor(ByteArray::class.java)
    }
}