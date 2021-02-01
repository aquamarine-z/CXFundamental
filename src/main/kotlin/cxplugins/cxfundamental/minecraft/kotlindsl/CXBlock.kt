package cxplugins.cxfundamental.minecraft.kotlindsl

import cxplugins.cxfundamental.minecraft.file.CXYamlConfiguration
import cxplugins.cxfundamental.minecraft.server.CXFundamentalMain
import org.bukkit.Bukkit
import org.bukkit.block.Block
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.metadata.FixedMetadataValue

fun Block.setData(path:String,data:Any?){
    var configuration=CXYamlConfiguration("CXPlugins\\","blockData.yml")
    var worldName=this.world.name
    var x=this.location.blockX
    var y=this.location.blockY
    var z=this.location.blockZ
    configuration.set("${worldName}_${x}_${y}_${z}.$path",data)
    configuration.save()
    this.setMetadata(path,FixedMetadataValue(CXFundamentalMain.pluginMain,data))
}
fun Block.getData(path:String,index:Int=0) :Any?{
    var data=this.getMetadata("$path")
    if(data.size==0) return null
    else return data[index].value()
}
fun updataBlockData(){
    var configuration=CXYamlConfiguration("CXPlugins\\","blockData.yml")
    for(blockInformation in configuration.getKeys(false)){
        var worldName=blockInformation.split("_")[0]
        var x=blockInformation.split("_")[1].toInt()
        var y=blockInformation.split("_")[2].toInt()
        var z=blockInformation.split("_")[3].toInt()
        var section=configuration.get(blockInformation) as ConfigurationSection
        var block=Bukkit.getWorld(worldName).getBlockAt(x,y,z)
        for(dataPath in section.getKeys(true)){
            block.setMetadata(dataPath,FixedMetadataValue(CXFundamentalMain.pluginMain,section.get(dataPath)))
        }
    }
}