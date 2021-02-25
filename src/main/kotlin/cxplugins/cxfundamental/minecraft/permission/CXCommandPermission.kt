
package cxplugins.cxfundamental.minecraft.permission

import org.bukkit.Bukkit
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

class CXCommandPermission{
    companion object
    {
        @JvmStatic
        fun register(lambda: CXCommandPermission.()->Unit){
            val permissionDsl= CXCommandPermission()
            permissionDsl.root
            permissionDsl.apply(lambda)
            for(entry in permissionDsl.permissionMap.entries){
                //println(entry.value.default)
                Bukkit.getServer().pluginManager.addPermission(entry.value)
            }
        }
    }
    var description=""
    lateinit var name:String
    var default=PermissionDefault.OP
    val permissionMap=HashMap<String,Permission>()
    private var root=""
    fun permission(lambda:CXCommandPermission.()->Unit){
        val commandPermission=CXCommandPermission()
        root=name
        commandPermission.root=this.root+".$name"
        commandPermission.apply(lambda)

        permissionMap.put("$root.${commandPermission.name}", Permission(root+"."+commandPermission.name,commandPermission.description,commandPermission.default))
        for(entry in commandPermission.permissionMap.entries){
            this.permissionMap.set(entry.key,entry.value)
        }
    }
}