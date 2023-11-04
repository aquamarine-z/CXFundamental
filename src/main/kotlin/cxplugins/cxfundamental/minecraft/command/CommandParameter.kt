package cxplugins.cxfundamental.minecraft.command

import org.bukkit.plugin.Plugin


class DELCommandInformationWithRegistration : DSLCommandInformation() {
    lateinit var command: String
    var description = ""
    var usage = ""
    var alias = ArrayList<String>()

    lateinit var permission: String
    lateinit var plugin: Plugin

    fun alia(alia: String) {
        alias.add(alia)
    }
}

open class DSLCommandInformation {
    var commandParameter: String = ""
    var targetList: List<CommandSenderType> = ArrayList()
    lateinit var commandAction: ActionLambda
    fun parameter(lambda: DSLCommandParameter.() -> Unit) {
        var parameter = DSLCommandParameter()
        parameter.apply(lambda)
        commandParameter = parameter.parameter
    }

    fun target(vararg type: CommandSenderType) {
        targetList = type.toMutableList()
    }

    fun action(lambda: ActionLambda) {
        this.commandAction = lambda
    }
}

open class DSLCommandParameter {

    var parameter: String = ""
    lateinit var name: String
    fun int(lambda: ParameterInt.() -> Unit) {
        val parameter = ParameterInt()
        parameter.apply(lambda)
        if (parameter.calculate) {
            this.parameter += " [name=${parameter.name},type=int,calculate=${parameter.calculate}] "
        } else {
            this.parameter += " [name=${parameter.name},type=int] "
        }
    }

    fun string(lambda: ParameterString.() -> Unit) {
        var parameter = ParameterString()
        parameter.apply(lambda)
        if (parameter.multiparameter.enable) {
            this.parameter += " [name=${parameter.name},type=string,multiparameter=true,multiparameterstart=${parameter.multiparameter.start},multiparameterend=${parameter.multiparameter.end}] "
        } else {
            this.parameter += " [name=${parameter.name},type=string] "
        }
    }

    fun boolean(lambda: ParameterBoolean.() -> Unit) {
        var parameter = ParameterBoolean()
        parameter.apply(lambda)
        this.parameter += " [name=${parameter.name},type=boolean] "
    }

    fun double(lambda: ParameterDouble.() -> Unit) {
        var parameter = ParameterDouble()
        parameter.apply(lambda)
        if (parameter.calculate) {
            this.parameter += " [name=${parameter.name},type=double,calculate=${parameter.calculate}] "
        } else {
            this.parameter += " [name=${parameter.name},type=double] "
        }
    }

    fun float(lambda: ParameterFloat.() -> Unit) {
        var parameter = ParameterFloat()
        parameter.apply(lambda)
        if (parameter.calculate) {
            this.parameter += " [name=${parameter.name},type=float,calculate=${parameter.calculate}] "
        } else {
            this.parameter += " [name=${parameter.name},type=float] "
        }
    }

    fun short(lambda: ParameterShort.() -> Unit) {
        var parameter = ParameterShort()
        parameter.apply(lambda)
        if (parameter.calculate) {
            this.parameter += " [name=${parameter.name},type=short,calculate=${parameter.calculate}] "
        } else {
            this.parameter += " [name=${parameter.name},type=short] "
        }
    }

    fun byte(lambda: ParameterByte.() -> Unit) {
        var parameter = ParameterByte()
        parameter.apply(lambda)
        if (parameter.calculate) {
            this.parameter += " [name=${parameter.name},type=byte,calculate=${parameter.calculate}] "
        } else {
            this.parameter += " [name=${parameter.name},type=byte] "
        }
    }

    fun long(lambda: ParameterLong.() -> Unit) {
        var parameter = ParameterLong()
        parameter.apply(lambda)
        if (parameter.calculate) {
            this.parameter += " [name=${parameter.name},type=long,calculate=${parameter.calculate}] "
        } else {
            this.parameter += " [name=${parameter.name},type=long] "
        }
    }

    fun onlinePlayer(lambda: ParameterOnlinePlayer.() -> Unit) {
        var parameter = ParameterOnlinePlayer()
        parameter.apply(lambda)
        this.parameter += " [name=${parameter.name},type=onlineplayer] "
    }

    fun player(lambda: ParameterPlayer.() -> Unit) {
        var parameter = ParameterPlayer()
        parameter.apply(lambda)
        this.parameter += " [name=${parameter.name},type=player] "
    }

    fun world(lambda: ParameterWorld.() -> Unit) {
        var parameter = ParameterWorld()
        parameter.apply(lambda)
        this.parameter += " [name=${parameter.name},type=world] "
    }
}

interface Calculatable {
    var calculate: Boolean
}

class Multiparameter {
    var enable = false
    lateinit var start: String
    lateinit var end: String
}

class ParameterInt : Calculatable, DSLCommandParameter() {
    override var calculate: Boolean = false
}

class ParameterString : DSLCommandParameter() {
    var multiparameter = Multiparameter()
    fun multiparameter(lambda: Multiparameter.() -> Unit) {
        multiparameter.apply(lambda)
    }
}

class ParameterBoolean : DSLCommandParameter()

class ParameterDouble : DSLCommandParameter(), Calculatable {
    override var calculate: Boolean = false
}

class ParameterFloat : DSLCommandParameter(), Calculatable {
    override var calculate: Boolean = false
}

class ParameterByte : DSLCommandParameter(), Calculatable {
    override var calculate: Boolean = false
}

class ParameterLong : DSLCommandParameter(), Calculatable {
    override var calculate: Boolean = false
}

class ParameterShort : DSLCommandParameter(), Calculatable {
    override var calculate: Boolean = false
}

class ParameterOnlinePlayer : DSLCommandParameter()

class ParameterPlayer : DSLCommandParameter()

class ParameterWorld : DSLCommandParameter()