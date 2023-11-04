package cxplugins.cxfundamental.minecraft.server.nms.itemnbt

import java.util.*

/**
 * ���������Ʒ��ĳ������
 *
 *
 * @param name ������Ե�����
 * @param amount ������Ե���ֵ
 * @param attribute ������Ե�����
 * @param operation ������Եķ���
 * @param slot ָ������Ʒװ��ĳ����λ����Ч
 * @param uuidLeast UUID����Сֵ
 * @param uuidMost UUID�����ֵ
 */
class AttributeModifier(
    name: String,
    amount: Double,
    attribute: ItemAttribute,
    operation: AttributeOperation = AttributeOperation.ADD_NUMBER,
    slot: ItemSlot = ItemSlot.INVENTORY,
    uuidLeast: Long = Random().nextLong(),
    uuidMost: Long = uuidLeast + Math.abs(Random().nextLong())
) {
    /**
     * ������Ե�����
     */
    var name: String = "name"

    /**
     * UUID����Сֵ
     */
    var UUIDLeast = uuidLeast

    /**
     * UUID�����ֵ
     */
    var UUIDMost = uuidMost

    /**
     * ������Ե�����
     */
    var attribute: ItemAttribute = ItemAttribute.GENERIC_AMOUR

    /**
     * ������Եķ���
     */
    var operation: AttributeOperation = AttributeOperation.ADD_NUMBER

    /**
     * ָ������Ʒװ��ĳ����λ����Ч
     */
    var slot = ItemSlot.INVENTORY

    /**
     * ������Ե���ֵ
     */
    var amount = 0.0

    init {
        this.name = name
        this.attribute = attribute
        this.operation = operation
        this.slot = slot
        this.amount = amount
        UUIDLeast = uuidLeast
        UUIDMost = uuidMost
    }

}

/**
 * ���ԵĲ�����
 * ����ժ��Minecraft Wiki:
 * ���η�
���η�������һ�����Ե�Base����ֵ����Ҳ�������ӻ����������Ҫע����ǣ����κ��ֵ����Խ�����������ֵ/��Сֵ�����ơ���������һ�������η���Name�����ƣ���Ȼ�����������η���Ч���޹أ����η���Ч����Operation������ģʽ����Amount������ֵ�����������η���������UUID������Ψһ�ر�ʶÿһ�����η���
�������ѧ�ǶȽ������η������ã�Ϊ�˷�����������BaseΪ���ԵĻ�ֵ��Op0Ϊ����Operation 0��ֵ���ܺͣ�Op1Ϊ����Operation 1��ֵ���ܺͣ�Op2Ϊ����Operation 2��ֵ��+1��ĳ˻�[3]���������±�ʾ��

��������ֵ = (Base + Op0) �� (1 + Op1) �� Op2
 */
enum class AttributeOperation(typeId: Short) {
    /**
     * ��ģʽֱ���ڻ�ֵ�Ͻ��мӼ�������(ժ��Minecraft Wiki)
     * ��{Amount:2,Operation:0}��{Amount:4,Operation:0}����ĳ���ԣ���ֵΪ3��
     * �����ι��̿��Ա�ʾΪ��
     * 3 + (2 + 4) = 3 + 6 = 9
     */
    ADD_NUMBER(0),

    /**
     *   ��������(ժ��Minecraft Wiki)
    ��Operation 1����ģʽ����Operation 0֮��ִ�У���ֱ��������ֵ�����ñ����Ͻ��мӼ���������Ҳ���ǽ���ǰ������ֵ���б����ϵ��޸ģ�����������Ϊ��1�����������Ķ�����
    ������{Amount:3,Operation:1}��{Amount:6,Operation:1}����ĳ���ԣ�����Operation 0�Ĳ����������ֵΪ9��
    �����ι��̿��Ա�ʾΪ��
    9 �� (1 + 3 + 6) = 9 �� 10 = 90
     *
     */
    ADD_SCALAR(1),

    /**
     * ���ձ���(ժ��Minecraft Wiki)
    ��Operation 2����ģʽ�����������������Σ�����������Operation 2���Σ���ɺ������ձ�����ִ�мӼ�����������ִ�����յı��ˡ���������ͬ��Ϊ��1����

    �����������յĲ�������˶��Operation 2֮���໥�������ֱ���и��Ե����ձ��ʵļӼ�������������������໥���ˣ�������ӡ�

    ������{Amount:2,Operation:2}��{Amount:4,Operation:2}����ĳ���ԣ������������в����������ֵΪ90��

    �����ι��̿��Ա�ʾΪ��

    1. ����ִ�е�һ��Amount:2�Ĳ�����

    90 �� (1 + 2) = 90 �� 3 = 270
    2. ��ִ�еڶ���Amount:4�Ĳ�����

    270 �� (1 + 4) = 270 �� 5 = 1350
     */
    MULTIPLY_SCALAR_1(2);

    var typeId: Short = 0

    init {
        this.typeId = typeId
    }

    companion object {
        @JvmStatic
        fun getValueFromTypeId(typeId: Short): AttributeOperation? {
            var values = AttributeOperation.values()
            for (value in values) {
                if (typeId == value.typeId) return value
            }
            return null
        }
    }

}

/**
 * ����NBT���Ե�ö��
 * ����ժ��Minecraft Wiki
 *ÿ�����������Զ�������һЩ��״������������Ե�Name�����֣�������һ������ͨ������һ��Base����ֵ�����е�ʱ�����һЩModifiers�����η���������ͨ��Ҳ��һ��Ĭ��ֵ��������������һ��û�ж�����ر����Ե�������Լ�һ���Դ��Ĺ涨ֵ�����η����������ԵĻ�ֵ�������Ҳ���ڹ涨ֵ���ڵġ�

��������˵����������Сֵ�����ֵ��Ϊ����ֵ�����ߵ���1.7x10308��

��ע�⣬������ͬUUID��Ӱ����ͬ���Ե��������η���������ӣ�ֻ�����Ӱ�����һ������һ���Ż����Ӱ�죬�Ҹ���֮ǰ�����η�
 */
enum class Attribute {
    /**����ժ��Minecraft Wiki
     *���׵ķ�������
     */
    GENERIC_AMOUR,

    /**
     * ����ժ��Minecraft Wiki
     *
    ���������������ֵ������������ͨ�������ָ������Իָ����ļ��ޡ�����Ҫ����[Health:#]nbt�ı�����ĵ�ǰ����ֵ��
     */
    GENERIC_MAX_HEALTH,

    /**
     * ����ժ��Minecraft Wiki
     *
     * �������׷����һ���������������Χ���Է�����Ϊ��λ��Ŀ���뿪���������ζ�����ǽ�ֹͣ׷�١�Ŀǰ������������ֵΪ16������ʬ����40��
     */
    GENERIC_FOLLOW_RANGE,

    /**
     * ����ժ��Minecraft Wiki
     *
     * �������Ŀ�����Ч�������������Ļ��ˡ���ը�͵����������ĳ̶ȣ�1.0������ȫ�ֿ���
     */
    GENERIC_KNOCKBACK_RESISTANCE,

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��ĳ�ֲ���������׼�����������ٶȡ��Ը�/��Ϊ��λ����������ٶ��Ը��ڴ�ֵ��43���������ܻ��ܵ�����������Ӱ�죬���磺����������������ܣ����ܣ�����Ǳ�������������������һ��ĩӰ�˻�ʬ���ˣ�����˩��ǣ�������ٶȻ�ٻ�ҩˮӰ�죬Ϊ���꽩ʬ������һ��Ů�׺ͺ�ҩˮ������ʹ�����µ�ʽ����ÿ��������ٶȣ�����x��movementSpeed����y = 43.178x-0.02141
     */
    GENERIC_MOVEMENT_SPEED,

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��ĳ�ֲ���������׼�����ĵķ����ٶȡ�
     */
    GENERIC_FLYING_SPEED,

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��ͨ������ɵ��˺���һ���ʾ������α�־���������ڱ���������δ�ҵ���
     */
    GENERIC_ATTACK_DAMAGE,

    /**
     * ����ժ��Minecraft Wiki
     *
     * �������Ĺ����������ȣ��б�֮��������в��߱������ԡ�
     */
    GENERIC_ATTACK_KNOCKBACK,

    /**
     * ����ժ��Minecraft Wiki
     *
     * �����������ȵ�����ٶȣ�ֵ����ÿ����Խ���ȫ�������Ĵ�����
     */
    GENERIC_ATTACK_SPEED,

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��������
     */
    GENERIC_ARMOR_TOUGHNESS,

    /**
     * ����ժ��Minecraft Wiki
     *
     * Ӱ��ս��Ʒ��ʹ�õ�quality��bonus_rolls�����統�����ӡ�����󳵣������ɱ�֣���
     */
    GENERIC_LUCK,

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��ĳ�ֲ���������׼����ĵ�������
     */
    HORSE_JUMP_STRENGTH,

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��ʬ��һ�ι���������Χ������һ����ʬ�Ŀ����ԡ���ʹ�ǽ�ʬ����Ҳ�����ɽ�ʬ��
     */
    ZOMBIE_SPAWN_REINFORCEMENTS
}

/**����ժ��Minecraft Wiki
 * ������Ʒ����ʲô��λʱ���������η��Ż���Ч�����û�ж������������η�����������λ��Ч��
 */
enum class ItemSlot(slotName: String) {
    /**
     * ����
     */
    MAINHAND("mainhand"),

    /**
     * ����
     */
    offHAND("offhand"),

    /**
     * ��
     */
    FEET("feet"),

    /**
     * ˫��
     */
    LEGS("legs"),

    /**
     * ���� �ؼ׵Ĳ�λ
     */
    CHEST("chest"),

    /**
     * ͷ
     */
    HEAD("head"),

    /**
     * ��Ʒ��
     */
    INVENTORY("inventory");

    var slotName: String = ""

    init {
        this.slotName = slotName
    }

    companion object {
        @JvmStatic
        fun getValueFromString(name: String): ItemSlot? {
            var values = ItemSlot.values()
            for (value in values) {
                if (name == value.slotName) return value
            }
            return null
        }
    }

}

/**��������Ʒ������ö��
 * ����ժ��Minecraft Wiki
 *ÿ�����������Զ�������һЩ��״������������Ե�Name�����֣�������һ������ͨ������һ��Base����ֵ�����е�ʱ�����һЩModifiers�����η���������ͨ��Ҳ��һ��Ĭ��ֵ��������������һ��û�ж�����ر����Ե�������Լ�һ���Դ��Ĺ涨ֵ�����η����������ԵĻ�ֵ�������Ҳ���ڹ涨ֵ���ڵġ�

��������˵����������Сֵ�����ֵ��Ϊ����ֵ�����ߵ���1.7x10308��

��ע�⣬������ͬUUID��Ӱ����ͬ���Ե��������η���������ӣ�ֻ�����Ӱ�����һ������һ���Ż����Ӱ�죬�Ҹ���֮ǰ�����η�
 */
enum class ItemAttribute(attributeName: String) {
    /**����ժ��Minecraft Wiki
     *���׵ķ�������
     */
    GENERIC_AMOUR("generic.amounr"),

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��ĳ�ֲ���������׼�����������ٶȡ��Ը�/��Ϊ��λ����������ٶ��Ը��ڴ�ֵ��43���������ܻ��ܵ�����������Ӱ�죬���磺����������������ܣ����ܣ�����Ǳ�������������������һ��ĩӰ�˻�ʬ���ˣ�����˩��ǣ�������ٶȻ�ٻ�ҩˮӰ�죬Ϊ���꽩ʬ������һ��Ů�׺ͺ�ҩˮ������ʹ�����µ�ʽ����ÿ��������ٶȣ�����x��movementSpeed����y = 43.178x-0.02141
     */
    GENERIC_MOVEMENT_SPEED("generic.movementSpeed"),

    /**
     * ����ժ��Minecraft Wiki
     *
     * ��ͨ������ɵ��˺���һ���ʾ������α�־���������ڱ���������δ�ҵ���
     */
    GENERIC_ATTACK_DAMAGE("generic.attackDamage"),

    /**
     * ����ժ��Minecraft Wiki
     *
     * �����������ȵ�����ٶȣ�ֵ����ÿ����Խ���ȫ�������Ĵ�����
     */
    GENERIC_ATTACK_SPEED("generic.attackSpeed"),

    /**
     * ����ժ��Minecraft Wiki
     *
     * Ӱ��ս��Ʒ��ʹ�õ�quality��bonus_rolls�����統�����ӡ�����󳵣������ɱ�֣���
     */
    GENERIC_LUCK("generic.luck"),

    /**
     * ����ժ��Minecraft Wiki
     *
    ���������������ֵ������������ͨ�������ָ������Իָ����ļ��ޡ�����Ҫ����[Health:#]nbt�ı�����ĵ�ǰ����ֵ��
     */
    GENERIC_MAX_HEALTH("generic.maxHealth");

    var attributeName = ""

    init {
        this.attributeName = attributeName
    }

    companion object {
        @JvmStatic
        fun getValueFromString(name: String): ItemAttribute? {
            var values = ItemAttribute.values()
            for (value in values) {
                if (name == value.attributeName) return value
            }
            return null
        }
    }
}