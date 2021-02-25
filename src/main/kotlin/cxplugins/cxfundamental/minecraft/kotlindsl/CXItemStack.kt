package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.server.CXItemStack
import cxplugins.cxfundamental.minecraft.server.nms.CraftItemStack
import cxplugins.cxfundamental.minecraft.server.nms.NBTEditor
import cxplugins.cxfundamental.minecraft.server.nms.NMSItemStack
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.AttributeModifier
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.AttributeOperation
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.ItemAttribute
import cxplugins.cxfundamental.minecraft.server.nms.itemnbt.NBTTagCompound
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.ItemStack as ItemStack

/**
 * ������Ʒ����������ָ����ֵ
 *
 * @param Amount ���ٵ�����
 */
fun ItemStack.cost(Amount: Int){
    this.amount=this.amount-Amount
}

/**
 * ����һ������Ʒ�Ŀ��� ��������Ϊ����amount
 *
 * @param amount ������Ʒ������
 * @return ��Ʒ�Ŀ���
 */
fun ItemStack.cloneWithAmount(amount:Int):ItemStack{
    var item=this.clone()
    item.amount=amount
    return item
}
/*
class ItemStack:ItemStacks(){
    companion object{
        @JvmStatic
        fun create(id:Int,amount:Int,display:String,lore:String,durability:Short=0):ItemStacks{
            var item=ItemStacks(id,amount,durability)
            var meta=item.itemMeta
            meta.displayName=display.toColor()
            meta.lore=lore.toColor().split("|").toMutableList()
            item.itemMeta=meta
            return item
        }
    }
}*/
/**
 * �ں�������������� �ж������Ƿ������Item���
 *
 * @param Item �жϵ���Ʒ
 * @return ����� �򷵻�true ���򷵻�false
 */

fun ItemStack.equalsIgnoreAmount(Item: ItemStack):Boolean{
    var a = this
    var b = Item

    a = a.clone()
    b = b.clone()
    a.amount = 1
    b.amount = 1
    return if (a == b)
        true
    else
        false
}

/**
 * ����Ʒ������׷������
 *
 * @param lore ׷�ӵ�����
 * @param index ׷�ӵ�λ�� Ĭ��Ϊĩβ
 */
fun ItemStack.appendLore(lore:String,index:Int=if(this.itemMeta.lore!=null) this.itemMeta.lore.size else 0){
    var meta=this.itemMeta
    var lores=meta.lore
    if(lores==null) lores=ArrayList<String>()
    try {
        lores.add(index, lore.toColor())
    }
    catch(exception:Exception){
        return
    }
    meta.lore=lores
    this.itemMeta=meta
}
/**
 * ����Ʒ������ɾ������
 *
 * @param line ɾ�������� Ĭ��Ϊĩβ
 */
fun ItemStack.removeLore(line:Int=this.itemMeta.lore.size-1)
{
    if(this.itemMeta.lore==null) return
    else if(this.itemMeta.lore.size<=line) return
    try{
        var meta=this.itemMeta
        var lores=meta.lore
        lores.removeAt(line)
        meta.lore=lores
        this.itemMeta=meta
     }
    catch (exception:Exception){
        return
    }
}
/**
 * ������Ʒչʾ��
 *
 * @param name ��Ʒ��չʾ��
 */
fun ItemStack.setDisplayName(name:String){
    var meta=this.itemMeta
    meta.displayName=name
    this.itemMeta=meta
}

/**
 * ������Ʒ����һ�ݲ�����NBT����
 *
 * @param compound NBTCompound����
 * @return ����Ʒ����NBT����֮��Ŀ���
 */
fun ItemStack.setNBTTagToCopy(compound:Any?):ItemStack{
    var nmsItemStack=CraftItemStack.asNMSCopy(null,this)
    NMSItemStack.setTag(nmsItemStack,compound)
    return CraftItemStack.asBukkitCopy(null,nmsItemStack) as ItemStack
}

/**
 * ������Ʒ����һ�ݲ�����NBT����
 *
 * @param value ���õ�����ֵ
 * @param path ���ݵ�·��
 * @return ����Ʒ����NBT����֮��Ŀ���
 */
fun ItemStack.setNBTValueToCopy(value:Any?,vararg path:String):ItemStack{
    return NBTEditor.setItemStackNBT(this,value,*path)
}

/**
 * ��ȡ����Ʒ��ĳ��NBT����
 *
 * @param path �����ݵ�·��
 * @return ��ȡ��NBT����
 */
fun ItemStack.getNBTValue(vararg path:String):Any?{
     return NBTEditor.getItemStackNBT(this,*path)
}
/**
 * ��ȡ����Ʒ��ĳ��NBT
 *
 * @return ��ȡ��NBT
 */
fun ItemStack.getNBTTag():Any{
    var nmsItemStack=CraftItemStack.asNMSCopy(null,this)
    return NMSItemStack.getTag(nmsItemStack)

}

/**
 * �ж��Ƿ���ĳ��NBT����
 *
 * @param path �����ݵ�·��
 * @return �Ƿ��д�����
 */
fun ItemStack.hasNBTValue(vararg path:String):Boolean{
    return if(getNBTValue(*path)==null) false else true
}

/**
 * Kotlin DSL����ItemMeta������
 *
 * @param lambda ����ItemMeta��lambda���ʽ
 */
fun CXItemStack.meta(lambda:ItemMeta.()->Unit){
    var meta=this.itemMeta
    meta.also(lambda)
    this.itemMeta=meta
}
/**
 * Kotlin DSL����Attribute������
 *
 * @param lambda ����Attribute��lambda���ʽ
 */
fun CXItemStack.attribute(lambda:ItemAttributeCompound.()->Unit){
    var compound=ItemAttributeCompound()
    compound.apply(lambda)
    var item=this.clone()
    for(modifier in compound.attributeList){
       item= NBTEditor.setItemAttribute(item,modifier)
    }
    this.itemMeta=item.itemMeta.clone()

}
/**
 * Kotlin DSL������Ʒ�˺�������
 *
 * @param lambda �������Ե�lambda���ʽ
 */
fun ItemAttributeCompound.attackDamage(lambda: AttributeModifier.()->Unit){
    var modifier=AttributeModifier("attackDamage",0.0,ItemAttribute.GENERIC_ATTACK_DAMAGE,AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)

}
/**
 * Kotlin DSL������Ʒ����ֵ������
 *
 * @param lambda �������Ե�lambda���ʽ
 */
fun ItemAttributeCompound.amour(lambda: AttributeModifier.()->Unit){
    var modifier=AttributeModifier("amour",0.0,ItemAttribute.GENERIC_AMOUR,AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)

}
/**
 * Kotlin DSL������Ʒ���ٵ�����
 *
 * @param lambda �������Ե�lambda���ʽ
 */
fun ItemAttributeCompound.attackSpeed(lambda: AttributeModifier.()->Unit){
    var modifier=AttributeModifier("attackSpeed",0.0,ItemAttribute.GENERIC_ATTACK_SPEED,AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)

}
/**
 * Kotlin DSL������Ʒ���˵�����
 *
 * @param lambda �������Ե�lambda���ʽ
 */
fun ItemAttributeCompound.luck(lambda: AttributeModifier.()->Unit){
    var modifier=AttributeModifier("luck",0.0,ItemAttribute.GENERIC_LUCK,AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)
}
/**
 * Kotlin DSL������Ʒ���ٵ�����
 *
 * @param lambda �������Ե�lambda���ʽ
 */
fun ItemAttributeCompound.movementSpeed(lambda: AttributeModifier.()->Unit){
    var modifier=AttributeModifier("movementSpeed",0.0,ItemAttribute.GENERIC_MOVEMENT_SPEED,AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)
}
/**
 * Kotlin DSL������Ʒ�������ֵ������
 *
 * @param lambda �������Ե�lambda���ʽ
 */
fun ItemAttributeCompound.maxHealth(lambda: AttributeModifier.()->Unit){
    var modifier=AttributeModifier("maxHealth",0.0,ItemAttribute.GENERIC_MAX_HEALTH,AttributeOperation.ADD_NUMBER)
    modifier.apply(lambda)
    this.attributeList.add(modifier)
}
/**
 * Kotlin DSL������ƷNBT����ֵ������
 *
 * @param lambda ����NBT����ֵ��lambda���ʽ
 */
fun CXItemStack.tag(lambda:NBTTagCompound.()->Unit){
    var compound=NBTTagCompound()
    compound.value=this.getNBTTag()
    compound.apply(lambda)
    var item=this.clone()
    var nmsItem=CraftItemStack.asNMSCopy(null,item)
    NMSItemStack.setTag(nmsItem,compound.value)
    item= CraftItemStack.asBukkitCopy(null,nmsItem) as ItemStack
    this.itemMeta=item.itemMeta.clone()
}
/**
 * Kotlin DSL����һ����Ʒ������������Եķ���
 *
 * @param lambda ������Ʒ���Ե�lambda���ʽ
 */
fun CXItemStack.Companion.copy(srcItemStack:CXItemStack, lambda:ItemStack.()->Unit):CXItemStack{
    srcItemStack.apply(lambda)
    return srcItemStack
}
fun CXItemStack.edit(lambda:ItemStack.()->Unit):Unit{
    var srcItemStack=this.clone()
    srcItemStack.apply(lambda)
    this.itemMeta=srcItemStack.itemMeta
}
/**
 * Kotlin DSL����һ����Ʒ������������Եķ���
 *
 * @param lambda ������Ʒ���Ե�lambda���ʽ
 */
fun CXItemStack.Companion.create(lambda:CXItemStack.()->Unit):CXItemStack{
    var itemStack=CXItemStack(1,1,"","");
    itemStack.apply(lambda)
    return itemStack
}