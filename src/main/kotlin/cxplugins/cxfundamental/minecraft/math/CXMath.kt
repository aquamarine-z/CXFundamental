package cxplugins.cxfundamental.minecraft.math

import org.bukkit.util.Vector
import java.util.Collections
import java.util.Stack
import java.util.regex.Pattern
import kotlin.math.cos
import kotlin.math.sin

class CXMath{
    /**
     * 静态的方法
     */
    companion object {
        /**
         * 计算某一个计算表达式
         * 提示:输入某一个非法的字符串表达式时可能会出现计算错误的问题
         * @param string 计算表达式
         * @return 计算表达式的结果
         */
        @JvmStatic
        fun calculate(string: String): Double {
            var result = 0.0
            try {
                result = java.lang.Double.parseDouble(string)
            } catch (exception: NumberFormatException) {
                result = Calculator.conversion(string)
            }

            return result
        }

        /**
         * 将一个值三维正交分解
         *
         * @param value 这个值
         * @param pyaw 偏航角角度(角度制)
         * @param ppitch 旋进角角度(角度制)
         * @return 分解到X,Y,Z轴上的三个数值
         */
        @JvmStatic
        fun orthogonalDecomposition(value:Double,pyaw:Double,ppitch:Double):Vector{
            var yaw=pyaw
            var pitch=ppitch
            var velocity=Vector(0,0,0)
            if(yaw>=-360&&yaw<-180){
                yaw+=180
                yaw=180+yaw
            }
            var pValue=0.0
            var y=0.0
            //将y轴的速度与x-z坐标系的速度正交分解
            if(pitch in 0.0..90.0){
                pValue=(cos(Math.toRadians(pitch.toDouble())))*value
                y=-sin(Math.toRadians(pitch.toDouble())) *value
            }
            else if(pitch in -90.0..0.0){
                pValue=(cos(Math.toRadians(pitch.toDouble())))*value
                y=-sin(Math.toRadians(pitch.toDouble())) *value
            }
            //将x轴与z轴的速度正交分解
            when(yaw){
                in 0.0..90.0-> {
                    //if(pitch)
                    velocity.setX(-sin(Math.toRadians(yaw.toDouble())) * pValue)
                    velocity.setZ(cos(Math.toRadians(yaw.toDouble())) * pValue)
                }
                in 90.0..180.0-> {
                    velocity.setX(-cos(Math.toRadians((yaw-90).toDouble())) * pValue)
                    velocity.setZ(-sin(Math.toRadians((yaw-90).toDouble())) *pValue)
                }
                in -90.0..0.0-> {
                    velocity.setZ(cos(Math.toRadians(yaw.toDouble())) * pValue)
                    velocity.setX(-sin(Math.toRadians(yaw.toDouble())) * pValue)
                }
                in -180.0..-90.0-> {
                    velocity.setZ(-sin(Math.toRadians((yaw-90).toDouble())) * pValue)
                    velocity.setX(-cos(Math.toRadians((yaw-90).toDouble())) *pValue)
                }
            }
            velocity.setY(y)
            return velocity
        }
        /**
         *判断某个字符串是否为整数
         *
         * @param str 字符串
         * @return 若是整数 返回true 否则返回false
         */
        @JvmStatic
        fun isInteger(str: String): Boolean {
            val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
            return pattern.matcher(str).matches()
        }
        /**
         *判断某个字符串是否为数字
         *
         * @param str 字符串
         * @return 若是整数 返回true 否则返回false
         */
        @JvmStatic
        fun isNumeric(str: String): Boolean {
            for (i in 0 until str.length) {
                println(str[i])
                if (!Character.isDigit(str[i])) {
                    return false
                }
            }
            return true
        }
    }

}// TODO 自动生成的构造函数存根


internal object ArithHelper {

    // 默认除法运算精度
    private val DEF_DIV_SCALE = 16

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    fun add(v1: Double, v2: Double): Double {
        val b1 = java.math.BigDecimal(java.lang.Double.toString(v1))
        val b2 = java.math.BigDecimal(java.lang.Double.toString(v2))
        return b1.add(b2).toDouble()
    }

    fun add(v1: String, v2: String): Double {
        val b1 = java.math.BigDecimal(v1)
        val b2 = java.math.BigDecimal(v2)
        return b1.add(b2).toDouble()
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    fun sub(v1: Double, v2: Double): Double {
        val b1 = java.math.BigDecimal(java.lang.Double.toString(v1))
        val b2 = java.math.BigDecimal(java.lang.Double.toString(v2))
        return b1.subtract(b2).toDouble()
    }

    fun sub(v1: String, v2: String): Double {
        val b1 = java.math.BigDecimal(v1)
        val b2 = java.math.BigDecimal(v2)
        return b1.subtract(b2).toDouble()
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     * 被乘数
     * @param v2
     * 乘数
     * @return 两个参数的积
     */

    fun mul(v1: Double, v2: Double): Double {
        val b1 = java.math.BigDecimal(java.lang.Double.toString(v1))
        val b2 = java.math.BigDecimal(java.lang.Double.toString(v2))
        return b1.multiply(b2).toDouble()
    }

    fun mul(v1: String, v2: String): Double {
        val b1 = java.math.BigDecimal(v1)
        val b2 = java.math.BigDecimal(v2)
        return b1.multiply(b2).toDouble()
    }

    fun div(v1: String, v2: String): Double {
        val b1 = java.math.BigDecimal(v1)
        val b2 = java.math.BigDecimal(v2)
        return b1.divide(b2, DEF_DIV_SCALE, java.math.BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    @JvmOverloads
    fun div(v1: Double, v2: Double, scale: Int = DEF_DIV_SCALE): Double {
        if (scale < 0) {
            throw IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero")
        }
        val b1 = java.math.BigDecimal(java.lang.Double.toString(v1))
        val b2 = java.math.BigDecimal(java.lang.Double.toString(v2))
        return b1.divide(b2, scale, java.math.BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    fun round(v: Double, scale: Int): Double {
        if (scale < 0) {
            throw IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero")
        }
        val b = java.math.BigDecimal(java.lang.Double.toString(v))
        val one = java.math.BigDecimal("1")
        return b.divide(one, scale, java.math.BigDecimal.ROUND_HALF_UP).toDouble()
    }

    fun round(v: String, scale: Int): Double {
        if (scale < 0) {
            throw IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero")
        }
        val b = java.math.BigDecimal(v)
        val one = java.math.BigDecimal("1")
        return b.divide(one, scale, java.math.BigDecimal.ROUND_HALF_UP).toDouble()
    }
}// 这个类不能实例化

/**
 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
 *
 * @param v1
 * 被除数
 * @param v2
 * 除数
 * @return 两个参数的商
 */
internal class Calculator {
    private val postfixStack = Stack<String>()// 后缀式栈
    private val opStack = Stack<Char>()// 运算符栈
    private val operatPriority = intArrayOf(0, 3, 2, 1, -1, 1, 0, 2)// 运用运算符ASCII码-40做索引的运算符优先级

    /**
     * 按照给定的表达式计算
     *
     * @param expression
     * 要计算的表达式例如:5+12*(3+5)/7
     * @return
     */
    fun calculate(expression: String): Double {
        val resultStack = Stack<String>()
        prepare(expression)
        Collections.reverse(postfixStack)// 将后缀式栈反转
        var firstValue: String
        var secondValue: String
        var currentValue: String// 参与计算的第一个值，第二个值和算术运算符
        while (!postfixStack.isEmpty()) {
            currentValue = postfixStack.pop()
            if (!isOperator(currentValue[0])) {// 如果不是运算符则存入操作数栈中
                currentValue = currentValue.replace("~", "-")
                resultStack.push(currentValue)
            } else {// 如果是运算符则从操作数栈中取两个值和该数值一起参与运算
                secondValue = resultStack.pop()
                firstValue = resultStack.pop()

                // 将负数标记符改为负号
                firstValue = firstValue.replace("~", "-")
                secondValue = secondValue.replace("~", "-")

                val tempResult = calculate(firstValue, secondValue, currentValue[0])
                resultStack.push(tempResult)
            }
        }
        return java.lang.Double.valueOf(resultStack.pop())
    }

    /**
     * 数据准备阶段将表达式转换成为后缀式栈
     *
     * @param expression
     */
    private fun prepare(expression: String) {
        opStack.push(',')// 运算符放入栈底元素逗号，此符号优先级最低
        val arr = expression.toCharArray()
        var currentIndex = 0// 当前字符的位置
        var count = 0// 上次算术运算符到本次算术运算符的字符的长度便于或者之间的数值
        var currentOp: Char
        var peekOp: Char// 当前操作符和栈顶操作符
        for (i in arr.indices) {
            currentOp = arr[i]
            if (isOperator(currentOp)) {// 如果当前字符是运算符
                if (count > 0) {
                    postfixStack.push(String(arr, currentIndex, count))// 取两个运算符之间的数字
                }
                peekOp = opStack.peek()
                if (currentOp == ')') {// 遇到反括号则将运算符栈中的元素移除到后缀式栈中直到遇到左括号
                    while (opStack.peek() != '(') {
                        postfixStack.push(opStack.pop().toString())
                    }
                    opStack.pop()
                } else {
                    while (currentOp != '(' && peekOp != ',' && compare(currentOp, peekOp)) {
                        postfixStack.push(opStack.pop().toString())
                        peekOp = opStack.peek()
                    }
                    opStack.push(currentOp)
                }
                count = 0
                currentIndex = i + 1
            } else {
                count++
            }
        }
        if (count > 1 || count == 1 && !isOperator(arr[currentIndex])) {// 最后一个字符不是括号或者其他运算符的则加入后缀式栈中
            postfixStack.push(String(arr, currentIndex, count))
        }

        while (opStack.peek() != ',') {
            postfixStack.push(opStack.pop().toString())// 将操作符栈中的剩余的元素添加到后缀式栈中
        }
    }

    /**
     * 判断是否为算术符号
     *
     * @param c
     * @return
     */
    private fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
    }

    /**
     * 利用ASCII码-40做下标去算术符号优先级
     *
     * @param cur
     * @param peek
     * @return
     */
    fun compare(cur: Char, peek: Char): Boolean {// 如果是peek优先级高于cur，返回true，默认都是peek优先级要低
        var result = false
        if (operatPriority[peek.toInt() - 40] >= operatPriority[cur.toInt() - 40]) {
            result = true
        }
        return result
    }

    /**
     * 按照给定的算术运算符做计算
     *
     * @param firstValue
     * @param secondValue
     * @param currentOp
     * @return
     */
    private fun calculate(firstValue: String, secondValue: String, currentOp: Char): String {
        var result = ""
        when (currentOp) {
            '+' -> result = ArithHelper.add(firstValue, secondValue).toString()
            '-' -> result = ArithHelper.sub(firstValue, secondValue).toString()
            '*' -> result = ArithHelper.mul(firstValue, secondValue).toString()
            '/' -> result = ArithHelper.div(firstValue, secondValue).toString()
        }
        return result
    }

    companion object {

        fun conversion(expression: String): Double {
            var expression = expression
            var result = 0.0
            val cal = Calculator()
            try {
                expression = transform(expression)
                result = cal.calculate(expression)
            } catch (e: Exception) {
                // e.printStackTrace();
                // 运算错误返回NaN
                return 0.0 / 0.0
            }

            // return new String().valueOf(result);
            return result
        }

        /**
         * 将表达式中负数的符号更改
         *
         * @param expression
         * 例如-2+-1*(-3E-2)-(-1) 被转为 ~2+~1*(~3E~2)-(~1)
         * @return
         */
        private fun transform(expression: String): String {
            val arr = expression.toCharArray()
            for (i in arr.indices) {
                if (arr[i] == '-') {
                    if (i == 0) {
                        arr[i] = '~'
                    } else {
                        val c = arr[i - 1]
                        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e') {
                            arr[i] = '~'
                        }
                    }
                }
            }
            if (arr[0] == '~' || arr[1] == '(') {
                arr[0] = '-'
                return "0" + String(arr)
            } else {
                return String(arr)
            }
        }
    }
}