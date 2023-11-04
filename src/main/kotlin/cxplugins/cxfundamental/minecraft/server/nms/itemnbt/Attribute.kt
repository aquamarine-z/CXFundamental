package cxplugins.cxfundamental.minecraft.server.nms.itemnbt

import java.util.*

/**
 * 此类代表物品的某个属性
 *
 *
 * @param name 这个属性的名字
 * @param amount 这个属性的数值
 * @param attribute 这个属性的类型
 * @param operation 这个属性的符号
 * @param slot 指定此物品装在某个部位才生效
 * @param uuidLeast UUID的最小值
 * @param uuidMost UUID的最大值
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
     * 这个属性的名字
     */
    var name: String = "name"

    /**
     * UUID的最小值
     */
    var UUIDLeast = uuidLeast

    /**
     * UUID的最大值
     */
    var UUIDMost = uuidMost

    /**
     * 这个属性的类型
     */
    var attribute: ItemAttribute = ItemAttribute.GENERIC_AMOUR

    /**
     * 这个属性的符号
     */
    var operation: AttributeOperation = AttributeOperation.ADD_NUMBER

    /**
     * 指定此物品装在某个部位才生效
     */
    var slot = ItemSlot.INVENTORY

    /**
     * 这个属性的数值
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
 * 属性的操作符
 * 以下摘自Minecraft Wiki:
 * 修饰符
修饰符会修饰一个属性的Base（基值），也就是增加或减少它。需要注意的是，修饰后的值不能越过该属性最大值/最小值的限制。就像属性一样，修饰符有Name（名称），然而名称与修饰符的效果无关，修饰符的效果由Operation（运算模式）和Amount（修饰值）决定。修饰符还有它的UUID，用来唯一地标识每一个修饰符。
下面从数学角度解释修饰符的作用，为了方便起见，简记Base为属性的基值；Op0为所有Operation 0的值的总和；Op1为所有Operation 1的值的总和；Op2为所有Operation 2的值再+1后的乘积[3]，则有如下表示：

最终属性值 = (Base + Op0) × (1 + Op1) × Op2
 */
enum class AttributeOperation(typeId: Short) {
    /**
     * 该模式直接在基值上进行加减法操作(摘自Minecraft Wiki)
     * 用{Amount:2,Operation:0}和{Amount:4,Operation:0}修饰某属性，基值为3。
     * 则修饰过程可以表示为：
     * 3 + (2 + 4) = 3 + 6 = 9
     */
    ADD_NUMBER(0),

    /**
     *   倍率增量(摘自Minecraft Wiki)
    即Operation 1。该模式将在Operation 0之后执行，并直接在属性值的作用倍率上进行加减法操作，也就是将当前的属性值进行倍率上的修改，而基础倍率为×1倍（即不做改动）。
    例：用{Amount:3,Operation:1}和{Amount:6,Operation:1}修饰某属性，经过Operation 0的操作后的属性值为9。
    则修饰过程可以表示为：
    9 × (1 + 3 + 6) = 9 × 10 = 90
     *
     */
    ADD_SCALAR(1),

    /**
     * 最终倍乘(摘自Minecraft Wiki)
    即Operation 2。该模式将会在其他所有修饰（包括其他的Operation 2修饰）完成后，在最终倍率上执行加减法操作，并执行最终的倍乘。基础倍率同样为×1倍。

    由于这是最终的操作，因此多个Operation 2之间相互独立，分别进行各自的最终倍率的加减法操作，最后结果则是相互倍乘，而非相加。

    例：用{Amount:2,Operation:2}和{Amount:4,Operation:2}修饰某属性，经过其他所有操作后的属性值为90。

    则修饰过程可以表示为：

    1. 首先执行第一个Amount:2的操作：

    90 × (1 + 2) = 90 × 3 = 270
    2. 再执行第二个Amount:4的操作：

    270 × (1 + 4) = 270 × 5 = 1350
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
 * 所有NBT属性的枚举
 * 以下摘自Minecraft Wiki
 *每个独立的属性都控制着一些性状，这由这个属性的Name（名字）决定。一个属性通常都有一个Base（基值），有的时候会有一些Modifiers（修饰符）。属性通常也有一个默认值（例如用于生成一个没有定义过特别属性的生物），以及一个自带的规定值域。修饰符会修饰属性的基值，但结果也是在规定值域内的。

除非另有说明，否则最小值和最大值均为包含值，或者等于1.7x10308。

请注意，具有相同UUID并影响相同属性的属性修饰符将不会叠加；只有最近影响的玩家或生物的一个才会产生影响，且覆盖之前的修饰符
 */
enum class Attribute {
    /**以下摘自Minecraft Wiki
     *盔甲的防御点数
     */
    GENERIC_AMOUR,

    /**
     * 以下摘自Minecraft Wiki
     *
    这个生物的最大生命值；亦或这个生物通过生命恢复最多可以恢复至的极限。你需要运用[Health:#]nbt改变生物的当前生命值。
     */
    GENERIC_MAX_HEALTH,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 这个生物追踪玩家或者其他生物的最大范围，以方块数为单位。目标离开这个区域意味着它们将停止追踪。目前大多数生物这个值为16，而僵尸则有40。
     */
    GENERIC_FOLLOW_RANGE,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 这个生物的抗击退效果（包括攻击的击退、爆炸和弹射物冲击）的程度，1.0代表完全抵抗。
     */
    GENERIC_KNOCKBACK_RESISTANCE,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 在某种不明度量标准下这个生物的速度。以格/秒为单位的生物最大速度略高于此值的43倍，但可能会受到各种条件的影响，例如：骑马（如果是马），疾跑，逃跑（如果是被动生物），攻击（如果是一个末影人或僵尸猪人），被拴绳牵引，受速度或迟缓药水影响，为幼年僵尸或者是一个女巫和喝药水。可以使用以下等式计算每秒格数的速度，其中x是movementSpeed属性y = 43.178x-0.02141
     */
    GENERIC_MOVEMENT_SPEED,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 在某种不明度量标准下鹦鹉的飞行速度。
     */
    GENERIC_FLYING_SPEED,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 普通攻击造成的伤害，一点表示半个心形标志。此属性在被动生物中未找到。
     */
    GENERIC_ATTACK_DAMAGE,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 这个生物的攻击击退力度，列表之外的生物中不具备该属性。
     */
    GENERIC_ATTACK_KNOCKBACK,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 决定攻击力度的填充速度，值代表每秒可以进行全力攻击的次数。
     */
    GENERIC_ATTACK_SPEED,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 盔甲韧性
     */
    GENERIC_ARMOR_TOUGHNESS,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 影响战利品表使用的quality和bonus_rolls（例如当打开箱子、运输矿车，钓鱼和杀怪）。
     */
    GENERIC_LUCK,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 在某种不明度量标准下马的弹跳力。
     */
    HORSE_JUMP_STRENGTH,

    /**
     * 以下摘自Minecraft Wiki
     *
     * 僵尸在一次攻击中在周围生成另一个僵尸的可能性。即使是僵尸猪人也会生成僵尸。
     */
    ZOMBIE_SPAWN_REINFORCEMENTS
}

/**以下摘自Minecraft Wiki
 * 决定物品处在什么栏位时，属性修饰符才会生效。如果没有定义此项，属性修饰符将对所有栏位生效。
 */
enum class ItemSlot(slotName: String) {
    /**
     * 主手
     */
    MAINHAND("mainhand"),

    /**
     * 副手
     */
    offHAND("offhand"),

    /**
     * 脚
     */
    FEET("feet"),

    /**
     * 双腿
     */
    LEGS("legs"),

    /**
     * 身体 胸甲的部位
     */
    CHEST("chest"),

    /**
     * 头
     */
    HEAD("head"),

    /**
     * 物品栏
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

/**可用于物品的属性枚举
 * 以下摘自Minecraft Wiki
 *每个独立的属性都控制着一些性状，这由这个属性的Name（名字）决定。一个属性通常都有一个Base（基值），有的时候会有一些Modifiers（修饰符）。属性通常也有一个默认值（例如用于生成一个没有定义过特别属性的生物），以及一个自带的规定值域。修饰符会修饰属性的基值，但结果也是在规定值域内的。

除非另有说明，否则最小值和最大值均为包含值，或者等于1.7x10308。

请注意，具有相同UUID并影响相同属性的属性修饰符将不会叠加；只有最近影响的玩家或生物的一个才会产生影响，且覆盖之前的修饰符
 */
enum class ItemAttribute(attributeName: String) {
    /**以下摘自Minecraft Wiki
     *盔甲的防御点数
     */
    GENERIC_AMOUR("generic.amounr"),

    /**
     * 以下摘自Minecraft Wiki
     *
     * 在某种不明度量标准下这个生物的速度。以格/秒为单位的生物最大速度略高于此值的43倍，但可能会受到各种条件的影响，例如：骑马（如果是马），疾跑，逃跑（如果是被动生物），攻击（如果是一个末影人或僵尸猪人），被拴绳牵引，受速度或迟缓药水影响，为幼年僵尸或者是一个女巫和喝药水。可以使用以下等式计算每秒格数的速度，其中x是movementSpeed属性y = 43.178x-0.02141
     */
    GENERIC_MOVEMENT_SPEED("generic.movementSpeed"),

    /**
     * 以下摘自Minecraft Wiki
     *
     * 普通攻击造成的伤害，一点表示半个心形标志。此属性在被动生物中未找到。
     */
    GENERIC_ATTACK_DAMAGE("generic.attackDamage"),

    /**
     * 以下摘自Minecraft Wiki
     *
     * 决定攻击力度的填充速度，值代表每秒可以进行全力攻击的次数。
     */
    GENERIC_ATTACK_SPEED("generic.attackSpeed"),

    /**
     * 以下摘自Minecraft Wiki
     *
     * 影响战利品表使用的quality和bonus_rolls（例如当打开箱子、运输矿车，钓鱼和杀怪）。
     */
    GENERIC_LUCK("generic.luck"),

    /**
     * 以下摘自Minecraft Wiki
     *
    这个生物的最大生命值；亦或这个生物通过生命恢复最多可以恢复至的极限。你需要运用[Health:#]nbt改变生物的当前生命值。
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