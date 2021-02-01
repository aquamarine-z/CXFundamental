package cxplugins.cxfundamental.minecraft.command

import org.bukkit.World
import org.bukkit.command.Command
import cxplugins.cxfundamental.minecraft.math.CXMath
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
typealias ActionLambda= Action.()->Boolean
typealias CXCommandExecutor=CPMLCommandExecutor
/*@JvmName("StringParameter")
fun string(name:String):String{
    return "[type=string,name=$name]"
}

@JvmName("IntegerParameter")
fun integer(name:String):String{
    return "[type=int,name=$name]"
}
@JvmName("DoubleParameter")
fun double(name:String):String{
    return "[type=double,name=$name]"
}
@JvmName("FloatParameter")
fun float(name:String):String{
    return "[type=float,name=$name]"
}
@JvmName("BooleanParameter")
fun boolean(name:String):String{
    return "[type=boolean,name=$name]"
}
@JvmName("WorldParameter")
fun world(name:String):String{
    return "[type=world,name=$name]"
}
@JvmName("PlayerParameter")
fun player(name:String):String{
    return "[type=player,name=$name]"
}
@JvmName("OnlineplayerParameter")
fun onlinePlayer(name:String):String{
    return "[type=onlineplayer,name=$name]"
}*/
/**
 * ��������������
 */
class CPMLCommandExecutor{
    /**
     * Ϊ�Ż������������Ĵ����ṩ�ľ�̬����
     *
     *
     *
     */
    /**
     * ��ʾһ������Ĳ��� (���������ɶ���)
     */
    private class CommandParameter constructor(type:String, name:String, multiParameter: Boolean, multiParameterStart: String, multiParameterEnd: String, calculate: Boolean) {
        var type=""
        var name=""
        var multiParameter=false
        var multiParameterStart="("
        var multiParameterEnd=")"
        var calculate=false;
        /*constructor(){

            this.type=type
            this.name=name
        }*/
        init{
            this.type=type
            this.name=name
            this.multiParameterStart=multiParameterStart
            this.multiParameterEnd=multiParameterEnd
            this.multiParameter=multiParameter
            this.calculate=calculate
        }
        constructor(type:String,name:String): this(type,name,false,"","",false)
        constructor(type:String,name:String,calculate: Boolean): this(type,name,false,"","",calculate)
        constructor(type:String,name:String,multiParameter: Boolean,multiParameterStart: String,multiParameterEnd: String): this(type,name,multiParameter,multiParameterStart,multiParameterEnd,false)



    }

    /**
     * ��ʾһ���Ĳ��������б� (���������ɶ���)
     */
    private class CommandParameterCompound{
        val parameters= ArrayList<CommandParameter>()
    }

    /**
     * �ṩ��ע�� ִ������ľ�̬����
     */
    companion object{
        private val parameterMap=HashMap<String, CommandParameterCompound>()
        val actionMap=HashMap<String, Action>()
        val commands=ArrayList<String>()
        @JvmStatic
        private fun sort(){
            for(i in 0..Companion.commands.size-2){
                for (j in i+1..Companion.commands.size-1){
                    if(Companion.commands[i].split(" ").size> Companion.commands[j].split(" ").size){
                        var temp= Companion.commands[i]
                        Companion.commands[i]= Companion.commands[j]
                        Companion.commands[j]=temp
                    }
                }
            }
            return
        }
        private fun asCommandString(cmd:Command, args: Array<String>) : String{
            var result=""
            result+=cmd.name
            for(arg in args){
                result+=" "+arg
            }
            return result
        }
        private fun getCommandName(commandString:String) : String?{
            var commandSize=commandString.split(" ").size
            var splitedCommand=commandString.split(" ")
            for(command in Companion.commands){
                var words=command.split(" ")
                if(words.size>commandSize) continue //��������������Ѿ����ڴ����� �������һ������
                var result=true
                for(i in 0..words.size-1){
                    if(words[i]!=splitedCommand[i]) result=false //�����0~���������-1��ƥ�� �򷵻�command �����ٴ�����
                }
                if(result){
                    return command
                }
                else continue
            }
            return null
        }

        /**
         * ����������������ע���������бȶ� ���������������Ѿ�ע�� ��ִ�и�����
         *
         * @param sender ������ķ�����
         * @param cmd ������
         * @param label ������ı�ʶ
         * @param args ������Ĳ���
         * @return �������Ƿ�ִ�гɹ�
         */
        @JvmStatic
        fun execute(sender: CommandSender, cmd: Command, label: String, args: Array<String>):Boolean{
            var commandString= Companion.asCommandString(cmd, args)
            var commandName= Companion.getCommandName(commandString)
            //(commandName)
            //(commandString)
            if(commandName!=null){
                //(commandName)
                var action= Companion.actionMap[commandName]
                var commandSize=commandName.split(" ").size
                var inputParameters=commandString.split(" ").subList(commandSize,commandString.split(" ").size)
                //(inputParameters)
                //(inputParameters)
                var definedParameters= Companion.parameterMap[commandName]

                var inputPointer=0

                for(i in 0 until definedParameters!!.parameters.size){
                    var calculate=definedParameters.parameters[i].calculate
                    var name=definedParameters.parameters[i].name
                    var type=definedParameters.parameters[i].type
                    var multiParameter=definedParameters.parameters[i].multiParameter
                    var multiParameterStart=definedParameters.parameters[i].multiParameterStart

                    var multiParameterEnd=definedParameters.parameters[i].multiParameterEnd
                    //print("name=$name type=$type multiParameter=$multiParameter start=$multiParameterStart end=$multiParameterEnd")
                    if(inputPointer>=inputParameters.size) {
                        throw CommandException(inputPointer, CommandException.Reason.WRONGAMOUNT)
                    }
                    when(type) {
                        "boolean"->{
                            var str=inputParameters[inputPointer]
                            if(str.toLowerCase()!="true"&&str.toLowerCase()!="false") {
                                throw CommandException(inputPointer, CommandException.Reason.BOOLEAN)
                            }
                            action!!.booleans.put(name, str.toLowerCase()=="true")
                        }
                        "integer","int"->{
                            if(calculate){
                                var int=CXMath.calculate(inputParameters[inputPointer])
                                action!!.integers.put(name,int.toInt())
                            }
                            else{
                                try {
                                    var int = inputParameters[inputPointer].toInt()
                                    action!!.integers.put(name, int)
                                }
                                catch (exc:Exception){
                                    throw CommandException(inputPointer, CommandException.Reason.INTEGER)
                                }
                            }
                        }
                        "double"->{
                            if(calculate){
                                var double=CXMath.calculate(inputParameters[inputPointer])
                                action!!.doubles.put(name,double)
                            }
                            else{
                                try {
                                    var double = inputParameters[inputPointer].toDouble()
                                    action!!.doubles.put(name, double)
                                }
                                catch (exc:Exception){
                                    throw CommandException(inputPointer, CommandException.Reason.DOUBLE)
                                }
                            }
                        }
                        "float"->{
                            if(calculate){
                                var float=CXMath.calculate(inputParameters[inputPointer])
                                action!!.floats.put(name,float.toFloat())
                            }
                            else{
                                try {
                                    var float = inputParameters[inputPointer].toFloat()
                                    action!!.floats.put(name, float)
                                }
                                catch (exc:Exception){
                                    throw CommandException(inputPointer, CommandException.Reason.FLOAT)
                                }
                            }

                        }
                        "string" -> {
                            if(multiParameter){
                                var string=""
                                var exit=false
                                //(multiParameterStart)
                                //(inputParameters[inputPointer])
                                if(!inputParameters[inputPointer].matches(Regex("$multiParameterStart.*"))){
                                   // (inputPointer)
                                    //(multiParameterStart[0])
                                    //(inputParameters[inputPointer][0])
                                    throw CommandException(inputPointer, CommandException.Reason.MULTIPAMAMETERNOTFOUNDSTART)
                                }

                                for(j in inputPointer until inputParameters.size){
                                    try {
                                        if (inputParameters[j].matches(Regex("^.*$multiParameterEnd"))) {
                                            string += inputParameters[j]
                                            inputPointer = j
                                            exit = true
                                            break
                                        }
                                        string += inputParameters[j]+" "
                                    }
                                    catch (exc :java.lang.Exception){
                                        //(multiParameterEnd)
                                    }
                                }
                                if(!exit){
                                    throw CommandException(inputPointer, CommandException.Reason.MULTIPAMAMETERNOTFOUNDEND)
                                }
                                string=string.substring(multiParameterStart.length,string.length-multiParameterEnd.length)
                                action!!.strings.put(name,string)
                            }
                            else{
                                //(inputParameters)
                                //(inputParameters[inputPointer])
                                action!!.strings.put(name,inputParameters[inputPointer])
                            }
                        }
                        "byte"->{
                            if(calculate){
                                var byte= CXMath.calculate(inputParameters[inputPointer]).toInt().toByte()
                                action!!.bytes.put(name,byte)
                            }
                            else{
                                try {
                                    var byte = inputParameters[inputPointer].toByte()
                                    action!!.bytes.put(name, byte)
                                }
                                catch (exc:Exception){
                                    throw CommandException(inputPointer, CommandException.Reason.BYTE)
                                }
                            }
                        }
                        "short"->{
                            if(calculate){
                                var short= CXMath.calculate(inputParameters[inputPointer]).toInt().toShort()
                                action!!.shorts.put(name,short)
                            }
                            else{
                                try {
                                    var short = inputParameters[inputPointer].toShort()
                                    action!!.shorts.put(name, short)
                                }
                                catch (exc:Exception){
                                    throw CommandException(inputPointer, CommandException.Reason.SHORT)
                                }
                            }
                        }
                        "long"->{
                            if(calculate){
                                var long=CXMath.calculate(inputParameters[inputPointer]).toLong()
                                action!!.longs.put(name,long)
                            }
                            else{
                                try {
                                    var long = inputParameters[inputPointer].toLong()
                                    action!!.longs.put(name, long)
                                }
                                catch (exc:Exception){
                                    throw CommandException(inputPointer, CommandException.Reason.LONG)
                                }
                            }
                        }
                        "char"->{
                            //Char����ʹ��Ƶ���� �ʲ�ʵ��Char���͵Ĳ���
                        }
                        "world"->{
                            var world=Bukkit.getWorld(inputParameters[inputPointer])
                            if(world==null){
                                throw CommandException(inputPointer, CommandException.Reason.WORLD)
                            }
                            else{
                                action!!.worlds.put(name, world)
                            }
                        }
                        "player"->{
                            if(Bukkit.getPlayer(inputParameters[inputPointer])==null){
                                throw CommandException(inputPointer, CommandException.Reason.PLAYER)
                            }
                            else{
                                action!!.players.put(name,Bukkit.getPlayer(inputParameters[inputPointer]))
                            }
                        }
                        "onlineplayer"->{
                            var player=Bukkit.getPlayer(inputParameters[inputPointer])
                            if(player==null){
                                throw CommandException(inputPointer, CommandException.Reason.ONLINEPLAYER)
                            }
                            else if(!player.isOnline){
                                throw CommandException(inputPointer, CommandException.Reason.ONLINEPLAYER)
                            }
                            else{
                                action!!.onlinePlayers.put(name,player)
                            }
                        }

                    }
                    inputPointer++
                }
                action!!.args=args
                action.cmd=cmd
                action.sender=sender
                action.label=label
                return action.action()
            }


            return true
        }
        fun register(command:String,lambda: cxplugins.cxfundamental.minecraft.command.DSLCommandInformation.()->Unit){
            var information= cxplugins.cxfundamental.minecraft.command.DSLCommandInformation()
            information.apply(lambda)
            var parameter=information.commandParameter
            var action=information.commandAction
            Companion.register(command, parameter, action)
        }
        fun register(command:String,parameters:String,action: Action.()->Boolean){
            var act=object : Action(){
                var action=action
                override fun action(): Boolean {
                    var r=this.let(this.action)
                    return r
                }
            }
            Companion.register(command, parameters, act)
        }
        /**
         * ע������� ���п�ѡ��type��:boolean,string,integer,double,player,onlineplayer,world
         * ��ѡ�Ĺ�����:calculate:�Ƿ�������������ʽ��ֵΪ��
         *            multiparameter:
         *               multiparameterstart:
         *               multiparameterend:
         *            �Ƿ������ϲ����Ķ�ȡ ��_This is a test_ �м��пո� �����������ϲ�����ȡ��Ϊ�ĸ����� ����֮��Ϊһ������"This is a test"
         * @param command ������
         * @param parameters ������Ĳ���(ʹ��CPML�﷨)
         * @param action ������ִ�к�Ĳ���
         * `register("test","[type=boolean,name="boolean"] [type=string,name=string,multiparameter=true,multiparameterstart=_,multiparameterend=_] [type=player,name=player] [type=integer,calculate=true,name=integer],null")`
         * @return �������Ƿ�ע��ɹ�
         *
         */
        @JvmStatic
        fun register(command:String,parameters:String,action: Action) : Boolean{
            if(Companion.commands.contains(command)) return false
            var parameter= parameters.trim().trimEnd().replace(Regex("""[ ]+""")," ").toString().split(" ").toMutableList()
            var compound= CommandParameterCompound()
            var registeredNames=ArrayList<String>()
            if(parameter.size==0){
                Companion.commands.add(command)
                Companion.parameterMap.put(command,compound)
                Companion.sort()
                Companion.actionMap.put(command,action)
                return true
            }
            for(i in 0..parameter.size-1){
                try {
                    parameter[i] = parameter[i].substring(1, parameter[i].length - 1)/*[name=integer,type=integer]*/

                }
                catch (exception:Exception){
                    Companion.commands.add(command)
                    Companion.parameterMap.put(command,compound)
                    Companion.sort()
                    Companion.actionMap.put(command,action)
                    return true
                }
                //(parameter[i])
                var modifier=parameter[i].split(",")
                var modifiersMap=HashMap<String,String>()
                for(j in 0..modifier.size-1){
                    var key=modifier[j].split("=")[0]
                    var value=modifier[j].split("=")[1]
                    modifiersMap.put(key,value)
                }
                if(registeredNames.contains(modifiersMap["name"]!!)) return false
                lateinit var commandParameter: CommandParameter
                if(modifiersMap.containsKey("calculate")){
                    try{
                        if (modifiersMap["type"]!="integer"&&modifiersMap["type"]!="double"&&modifiersMap["type"]!="float") return false
                    }
                    catch (exc:Exception){
                        return false
                    }
                }
                if(modifiersMap.containsKey("multiparameter")){
                    try{
                        if (modifiersMap["type"]!="string") return false
                        commandParameter= CommandParameter(modifiersMap["type"]!!, modifiersMap["name"]!!, modifiersMap["multiparameter"]!!.toBoolean(), modifiersMap["multiparameterstart"]!!, modifiersMap["multiparameterend"]!!)

                    }
                    catch (exc:Exception){
                        return false
                    }
                }
                else{
                    try{
                        commandParameter= CommandParameter(modifiersMap["type"]!!, modifiersMap["name"]!!)
                    }
                    catch (exc:Exception){
                        return false
                    }
                }
                compound.parameters.add(commandParameter)
                registeredNames.add(modifiersMap["name"]!!)
            }
            Companion.commands.add(command)
            Companion.parameterMap.put(command,compound)
            Companion.sort()
            Companion.actionMap.put(command,action)
            return true
        }
    }

}
/**
 * ���������ĳ�������ʱ�� Ӧ��ִ�еĲ���
 * ͨ��ʵ�ַ���action()��ָ������Ĳ���
 */
abstract class Action{
    /**
     * ����ķ�����
     */
    lateinit var sender:CommandSender
    /**
     * ����Ĳ���
     */
    lateinit var args:Array<String>
    /**
     * ����ı�ʶ
     */
    lateinit var label:String
    /**
     * ������
     */
    lateinit var cmd: Command
    /**
     * ������CPML�����б��б����Ϊ"type=string"�Ĳ������ᱣ����strings�ֵ���
     */
    var strings=HashMap<String,String>()
    /**
     * ������CPML�����б��б����Ϊ"type=integers"�Ĳ������ᱣ����integers�ֵ���
     */
    var integers=HashMap<String,Int>()
    /**
     * ������CPML�����б��б����Ϊ"type=boolean"�Ĳ������ᱣ����booleans�ֵ���
     */
    var booleans=HashMap<String,Boolean>()
    /**
     * ������CPML�����б��б����Ϊ"type=double"�Ĳ������ᱣ����doubles�ֵ���
     */
    var doubles=HashMap<String,Double>()
    /**
     * ������CPML�����б��б����Ϊ"type=float"�Ĳ������ᱣ����float�ֵ���
     */
    var floats=HashMap<String,Float>()
    /**
     * ������CPML�����б��б����Ϊ"type=onlineplayer"�Ĳ������ᱣ����onlinePlayers�ֵ���
     */
    var onlinePlayers=HashMap<String,Player>()
    /**
     * ������CPML�����б��б����Ϊ"type=player"�Ĳ������ᱣ����players�ֵ���
     */
    var players=HashMap<String,Player>()
    /**
     * ������CPML�����б��б����Ϊ"type=world"�Ĳ������ᱣ����worlds�ֵ���
     */
    var worlds=HashMap<String,World>()
    /**
     * ������CPML�����б��б����Ϊ"type=byte"�Ĳ������ᱣ����bytes�ֵ���
     */
    var bytes=HashMap<String,Byte>()
    /**
     * ������CPML�����б��б����Ϊ"type=short"�Ĳ������ᱣ����shorts�ֵ���
     */
    var shorts=HashMap<String,Short>()
    /**
     * ������CPML�����б��б����Ϊ"type=long"�Ĳ������ᱣ����longs�ֵ���
     */
    var longs=HashMap<String,Long>()
    /**
     * ��Ҫ����ʵ�ֵ�������Ӧ����
     *
     * @return �������Ƿ�ɹ�ִ��
     */
    abstract fun action() : Boolean
}
