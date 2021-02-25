package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.cxfundamental.minecraft.plugin.CXFundamentalMain
import cxplugins.cxfundamental.minecraft.plugin.debug
import org.bukkit.Bukkit
import org.bukkit.block.Block
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.metadata.FixedMetadataValue
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
    val fileList=File(".\\plugins\\CXPlugins\\blockData").list()
    val worldName=this.world.name
    val x=this.location.blockX
    val y=this.location.blockY
    val z=this.location.blockZ
    val fileName="${worldName}_${x}_${y}_${z}"
    if(!fileList.contains(fileName)){
        return null
    }
    val configuration=CXYamlConfiguration("CXPlugins\\blockData","$fileName.yml")
    return configuration.get(path)
}
fun Block.setData(path:String,data:Any?){
    val configuration=CXYamlConfiguration("CXPlugins\\","blockData.yml")
    val worldName=this.world.name
    val x=this.location.blockX
    val y=this.location.blockY
    val z=this.location.blockZ
    configuration.set("${worldName}_${x}_${y}_${z}.$path",data)
    configuration.save()
    this.setMetadata(path,FixedMetadataValue(CXFundamentalMain.pluginMain,data))
}
fun Block.getData(path:String,index:Int=0) :Any?{
    if(!this.hasMetadata("BlockData.$path")) return null
    var data=this.getMetadata("BlockData.$path")
    return data[index].value()
}
fun updateBlockData(){
    var configuration=CXYamlConfiguration("CXPlugins","blockData.yml")
    for(blockInformation in configuration.getKeys(false)){
        var worldName=blockInformation.split("_")[0]
        var x=blockInformation.split("_")[1].toInt()
        var y=blockInformation.split("_")[2].toInt()
        var z=blockInformation.split("_")[3].toInt()
        var section=configuration.get(blockInformation) as ConfigurationSection
        var block=Bukkit.getWorld(worldName).getBlockAt(x,y,z)

        for(dataPath in section.getKeys(true)){
            if(!section.isConfigurationSection(dataPath)) {
                if(debug) println("$dataPath : ${section.get(dataPath)}")
                block.setMetadata("BlockData.$dataPath".replaceFirst(blockInformation, ""),
                    FixedMetadataValue(CXFundamentalMain.pluginMain, section.get(dataPath)))
            }
        }
    }
}
