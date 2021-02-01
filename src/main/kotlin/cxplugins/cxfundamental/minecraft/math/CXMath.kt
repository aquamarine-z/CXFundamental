package cxplugins.cxfundamental.minecraft.math

import org.bukkit.util.Vector
import java.util.Collections
import java.util.Stack
import java.util.regex.Pattern
import kotlin.math.cos
import kotlin.math.sin

class CXMath{
    /**
     * ��̬�ķ���
     */
    companion object {
        /**
         * ����ĳһ��������ʽ
         * ��ʾ:����ĳһ���Ƿ����ַ������ʽʱ���ܻ���ּ�����������
         * @param string ������ʽ
         * @return ������ʽ�Ľ��
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
         * ��һ��ֵ��ά�����ֽ�
         *
         * @param value ���ֵ
         * @param pyaw ƫ���ǽǶ�(�Ƕ���)
         * @param ppitch �����ǽǶ�(�Ƕ���)
         * @return �ֽ⵽X,Y,Z���ϵ�������ֵ
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
            //��y����ٶ���x-z����ϵ���ٶ������ֽ�
            if(pitch in 0.0..90.0){
                pValue=(cos(Math.toRadians(pitch.toDouble())))*value
                y=-sin(Math.toRadians(pitch.toDouble())) *value
            }
            else if(pitch in -90.0..0.0){
                pValue=(cos(Math.toRadians(pitch.toDouble())))*value
                y=-sin(Math.toRadians(pitch.toDouble())) *value
            }
            //��x����z����ٶ������ֽ�
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
         *�ж�ĳ���ַ����Ƿ�Ϊ����
         *
         * @param str �ַ���
         * @return �������� ����true ���򷵻�false
         */
        @JvmStatic
        fun isInteger(str: String): Boolean {
            val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
            return pattern.matcher(str).matches()
        }
        /**
         *�ж�ĳ���ַ����Ƿ�Ϊ����
         *
         * @param str �ַ���
         * @return �������� ����true ���򷵻�false
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

}// TODO �Զ����ɵĹ��캯�����


internal object ArithHelper {

    // Ĭ�ϳ������㾫��
    private val DEF_DIV_SCALE = 16

    /**
     * �ṩ��ȷ�ļӷ����㡣
     *
     * @param v1 ������
     * @param v2 ����
     * @return ���������ĺ�
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
     * �ṩ��ȷ�ļ������㡣
     *
     * @param v1 ������
     * @param v2 ����
     * @return ���������Ĳ�
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
     * �ṩ��ȷ�ĳ˷����㡣
     *
     * @param v1
     * ������
     * @param v2
     * ����
     * @return ���������Ļ�
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
     * �ṩ����ԣ���ȷ�ĳ������㡣�����������������ʱ����scale����ָ �����ȣ��Ժ�������������롣
     *
     * @param v1 ������
     * @param v2 ����
     * @param scale ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
     * @return ������������
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
     * �ṩ��ȷ��С��λ�������봦��
     *
     * @param v ��Ҫ�������������
     * @param scale С���������λ
     * @return ���������Ľ��
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
}// ����಻��ʵ����

/**
 * �ṩ����ԣ���ȷ�ĳ������㣬�����������������ʱ����ȷ�� С�����Ժ�10λ���Ժ�������������롣
 *
 * @param v1
 * ������
 * @param v2
 * ����
 * @return ������������
 */
internal class Calculator {
    private val postfixStack = Stack<String>()// ��׺ʽջ
    private val opStack = Stack<Char>()// �����ջ
    private val operatPriority = intArrayOf(0, 3, 2, 1, -1, 1, 0, 2)// ���������ASCII��-40����������������ȼ�

    /**
     * ���ո����ı��ʽ����
     *
     * @param expression
     * Ҫ����ı��ʽ����:5+12*(3+5)/7
     * @return
     */
    fun calculate(expression: String): Double {
        val resultStack = Stack<String>()
        prepare(expression)
        Collections.reverse(postfixStack)// ����׺ʽջ��ת
        var firstValue: String
        var secondValue: String
        var currentValue: String// �������ĵ�һ��ֵ���ڶ���ֵ�����������
        while (!postfixStack.isEmpty()) {
            currentValue = postfixStack.pop()
            if (!isOperator(currentValue[0])) {// ����������������������ջ��
                currentValue = currentValue.replace("~", "-")
                resultStack.push(currentValue)
            } else {// ������������Ӳ�����ջ��ȡ����ֵ�͸���ֵһ���������
                secondValue = resultStack.pop()
                firstValue = resultStack.pop()

                // ��������Ƿ���Ϊ����
                firstValue = firstValue.replace("~", "-")
                secondValue = secondValue.replace("~", "-")

                val tempResult = calculate(firstValue, secondValue, currentValue[0])
                resultStack.push(tempResult)
            }
        }
        return java.lang.Double.valueOf(resultStack.pop())
    }

    /**
     * ����׼���׶ν����ʽת����Ϊ��׺ʽջ
     *
     * @param expression
     */
    private fun prepare(expression: String) {
        opStack.push(',')// ���������ջ��Ԫ�ض��ţ��˷������ȼ����
        val arr = expression.toCharArray()
        var currentIndex = 0// ��ǰ�ַ���λ��
        var count = 0// �ϴ����������������������������ַ��ĳ��ȱ��ڻ���֮�����ֵ
        var currentOp: Char
        var peekOp: Char// ��ǰ��������ջ��������
        for (i in arr.indices) {
            currentOp = arr[i]
            if (isOperator(currentOp)) {// �����ǰ�ַ��������
                if (count > 0) {
                    postfixStack.push(String(arr, currentIndex, count))// ȡ���������֮�������
                }
                peekOp = opStack.peek()
                if (currentOp == ')') {// �����������������ջ�е�Ԫ���Ƴ�����׺ʽջ��ֱ������������
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
        if (count > 1 || count == 1 && !isOperator(arr[currentIndex])) {// ���һ���ַ��������Ż��������������������׺ʽջ��
            postfixStack.push(String(arr, currentIndex, count))
        }

        while (opStack.peek() != ',') {
            postfixStack.push(opStack.pop().toString())// ��������ջ�е�ʣ���Ԫ����ӵ���׺ʽջ��
        }
    }

    /**
     * �ж��Ƿ�Ϊ��������
     *
     * @param c
     * @return
     */
    private fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
    }

    /**
     * ����ASCII��-40���±�ȥ�����������ȼ�
     *
     * @param cur
     * @param peek
     * @return
     */
    fun compare(cur: Char, peek: Char): Boolean {// �����peek���ȼ�����cur������true��Ĭ�϶���peek���ȼ�Ҫ��
        var result = false
        if (operatPriority[peek.toInt() - 40] >= operatPriority[cur.toInt() - 40]) {
            result = true
        }
        return result
    }

    /**
     * ���ո��������������������
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
                // ������󷵻�NaN
                return 0.0 / 0.0
            }

            // return new String().valueOf(result);
            return result
        }

        /**
         * �����ʽ�и����ķ��Ÿ���
         *
         * @param expression
         * ����-2+-1*(-3E-2)-(-1) ��תΪ ~2+~1*(~3E~2)-(~1)
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