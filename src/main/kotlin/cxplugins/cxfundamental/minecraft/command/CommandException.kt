package cxplugins.cxfundamental.minecraft.command

/**
 * 在玩家输入命令时 发生的异常
 * @param place 有异常的参数的位置
 * @param reason 抛出此异常的原因
 */
class CommandException(place: Int, reason: Reason) : RuntimeException() {
    var place: Int = 0
        internal set
    var reason: Reason
        internal set
    var extraInformation: Any? = null

    /**
     * Reason
     *
     * @constructor
     *
     * @param errorcode 错误代码
     * @param description 描述
     * @param type 错误类型
     */
    enum class Reason(errorcode: Long, description: String, type: String) {
        /**
         * O n l i n e p l a y e r
         *
         * @constructor Create empty O n l i n e p l a y e r
         */
        ONLINEPLAYER(10000001, "输入的参数不是[OnlinePlayer]", "ArgsException"),

        /**
         * W o r l d
         *
         * @constructor Create empty W o r l d
         */
        WORLD(10000002, "输入的参数不是[World]", "ArgsException"),

        /**
         * I n t e g e r
         *
         * @constructor Create empty I n t e g e r
         */
        INTEGER(10000003, "输入的参数不是[Integer]", "ArgsException"),

        /**
         * N u m b e r
         *
         * @constructor Create empty N u m b e r
         */
        NUMBER(10000004, "输入的参数不是[Number]", "ArgsException"),

        /**
         * B o o l e a n
         *
         * @constructor Create empty B o o l e a n
         */
        BOOLEAN(10000005, "输入的参数不是[Boolean]", "ArgsException"),

        /**
         * P l a y e r
         *
         * @constructor Create empty P l a y e r
         */
        PLAYER(10000006, "输入的参数不是[Player]", "ArgsException"),

        /**
         * W r o n g a m o u n t
         *
         * @constructor Create empty W r o n g a m o u n t
         */
        WRONGAMOUNT(10000006, "输入的参数数量错误", "ArgsException"),

        /**
         * M u l t i p a m a m e t e r n o t f o u n d e n d
         *
         * @constructor Create empty M u l t i p a m a m e t e r n o t f o u n d e n d
         */
        MULTIPAMAMETERNOTFOUNDEND(10000008, "该命令的组合参数没有结尾", "ArgsException"),

        /**
         * M u l t i p a m a m e t e r n o t f o u n d s t a r t
         *
         * @constructor Create empty M u l t i p a m a m e t e r n o t f o u n d s t a r t
         */
        MULTIPAMAMETERNOTFOUNDSTART(10000008, "该命令的组合参数没有开头", "ArgsException"),

        /**
         * F l o a t
         *
         * @constructor Create empty F l o a t
         */
        FLOAT(10000009, "输入的参数不是[Float]", "ArgsException"),

        /**
         * D o u b l e
         *
         * @constructor Create empty D o u b l e
         */
        DOUBLE(10000010, "输入的参数不是[Double]", "ArgsException"),
        BYTE(10000011, "输入的参数不是[Byte]", "ArgsException"),
        LONG(10000012, "输入的参数不是[Long]", "ArgsException"),
        SHORT(10000013, "输入的参数不是[Short]", "ArgsException"),
        WRONGSENDER(10000014, "该命令的执行者不正确", "SenderException");

        internal var errorCode: Long = 0
        internal var description = ""
        internal var type = ""

        init {
            this.errorCode = errorcode
            this.description = description
            this.type = type
        }

    }

    init {
        this.place = place
        this.reason = reason
    }
}
