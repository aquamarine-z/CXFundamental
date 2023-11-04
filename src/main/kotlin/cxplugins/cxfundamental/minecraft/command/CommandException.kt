package cxplugins.cxfundamental.minecraft.command

/**
 * �������������ʱ �������쳣
 * @param place ���쳣�Ĳ�����λ��
 * @param reason �׳����쳣��ԭ��
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
     * @param errorcode �������
     * @param description ����
     * @param type ��������
     */
    enum class Reason(errorcode: Long, description: String, type: String) {
        /**
         * O n l i n e p l a y e r
         *
         * @constructor Create empty O n l i n e p l a y e r
         */
        ONLINEPLAYER(10000001, "����Ĳ�������[OnlinePlayer]", "ArgsException"),

        /**
         * W o r l d
         *
         * @constructor Create empty W o r l d
         */
        WORLD(10000002, "����Ĳ�������[World]", "ArgsException"),

        /**
         * I n t e g e r
         *
         * @constructor Create empty I n t e g e r
         */
        INTEGER(10000003, "����Ĳ�������[Integer]", "ArgsException"),

        /**
         * N u m b e r
         *
         * @constructor Create empty N u m b e r
         */
        NUMBER(10000004, "����Ĳ�������[Number]", "ArgsException"),

        /**
         * B o o l e a n
         *
         * @constructor Create empty B o o l e a n
         */
        BOOLEAN(10000005, "����Ĳ�������[Boolean]", "ArgsException"),

        /**
         * P l a y e r
         *
         * @constructor Create empty P l a y e r
         */
        PLAYER(10000006, "����Ĳ�������[Player]", "ArgsException"),

        /**
         * W r o n g a m o u n t
         *
         * @constructor Create empty W r o n g a m o u n t
         */
        WRONGAMOUNT(10000006, "����Ĳ�����������", "ArgsException"),

        /**
         * M u l t i p a m a m e t e r n o t f o u n d e n d
         *
         * @constructor Create empty M u l t i p a m a m e t e r n o t f o u n d e n d
         */
        MULTIPAMAMETERNOTFOUNDEND(10000008, "���������ϲ���û�н�β", "ArgsException"),

        /**
         * M u l t i p a m a m e t e r n o t f o u n d s t a r t
         *
         * @constructor Create empty M u l t i p a m a m e t e r n o t f o u n d s t a r t
         */
        MULTIPAMAMETERNOTFOUNDSTART(10000008, "���������ϲ���û�п�ͷ", "ArgsException"),

        /**
         * F l o a t
         *
         * @constructor Create empty F l o a t
         */
        FLOAT(10000009, "����Ĳ�������[Float]", "ArgsException"),

        /**
         * D o u b l e
         *
         * @constructor Create empty D o u b l e
         */
        DOUBLE(10000010, "����Ĳ�������[Double]", "ArgsException"),
        BYTE(10000011, "����Ĳ�������[Byte]", "ArgsException"),
        LONG(10000012, "����Ĳ�������[Long]", "ArgsException"),
        SHORT(10000013, "����Ĳ�������[Short]", "ArgsException"),
        WRONGSENDER(10000014, "�������ִ���߲���ȷ", "SenderException");

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
