package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import org.bukkit.block.Block
import java.io.File

fun Block.setPersistentData(path:String,data:Any?){
    val worldName=this.world.name
    val x=this.location.blockX
    val y=this.location.blockY
    val z=this.location.blockZ
    val fileName="${worldName}_${x}_${y}_${z}"
    val configuration=CXYamlConfiguration("CXPlugins\\blockData","$fileName.yml")
    configuration.set(path,data)
    configuration.save()
}
fun Block.getPersistentData(path:String) : Any?{
    if(!File(".\\plugins\\CXPlugins\\blockData").exists()){
        File(".\\plugins\\CXPlugins\\blockData").mkdirs()
    }

    val fileList= File(".\\plugins\\CXPlugins\\blockData").list() ?: return null
    val worldName=this.world.name
    val x=this.location.blockX
    val y=this.location.blockY
    val z=this.location.blockZ
    val fileName="${worldName}_${x}_${y}_${z}.yml"
    if(!fileList.contains(fileName)){
        return null
    }
    val configuration=CXYamlConfiguration("CXPlugins\\blockData","$fileName")
    return configuration.get(path)
}
