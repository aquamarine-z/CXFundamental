package cxplugins.cxfundamental.minecraft.dependiences;

import cxplugins.cxfundamental.minecraft.server.CXPluginMain;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DependencyManager {
    /*var r: ByteArray? = null
        var out: ByteArrayOutputStream? = null
        try {
            val url = URL(fileUrl)
            val `in` = url.openStream()
            out = ByteArrayOutputStream()
            val b = ByteArray(8 * 1024)
            var read:Int=0
            while (`in`.read(b).also { read = it } != -1) out.write(b, 0, read)
            out.flush()
            r = out.toByteArray()
            out.close()
            `in`.close()
        } catch (e: java.lang.Exception) {
        }
        return r

     */
    private static byte[] readUrlFile(URL url){
        byte[] result=null;
        ByteArrayOutputStream out=null;
        try {
            InputStream in=url.openStream();
            out= new ByteArrayOutputStream();
            byte[] b= new byte[8 * 1024];
            int read=0;
            while(true){
                read=in.read(b);
                if(read==-1){
                    break;
                }
                out.write(b, 0, read);

            }
            out.flush();
            result=out.toByteArray();
            out.close();
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void download(String name,String urlString){
        File file=new File(".\\plugins\\"+name+".jar");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
               return;
            }
        }
        try {

            URL url=new URL(urlString);
            try {
                url.openConnection().setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //val writer=BufferedOutputStream(FileOutputStream(file))
            /*
            var buf=readUrlFile(address)
            writer.write(buf)
            writer.close()
             */
            try {
                BufferedOutputStream writer=new BufferedOutputStream(new FileOutputStream(file));
                byte[] buf=readUrlFile(url);
                try {
                    writer.write(buf);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (FileNotFoundException e) {
                return;
            }
        } catch (MalformedURLException e) {
            return;
        }
    }

    public static void checkDependencies(){
        List<Plugin> plugins= Arrays.asList(Bukkit.getServer().getPluginManager().getPlugins());
        List<String> pluginNames= new ArrayList<String>();
        for(Plugin plugin :plugins){
            pluginNames.add(plugin.getName());
        }
        ArrayList<CXPluginMain> cxplugins=new ArrayList<CXPluginMain>();
        for(Plugin plugin : plugins){
            if(plugin instanceof CXPluginMain){
                cxplugins.add((CXPluginMain)plugin);
            }
        }
        for(CXPluginMain plugin:cxplugins){
            ArrayList<DependencyInformation> dependiences=plugin.getDependiences();
            for(DependencyInformation dependency : dependiences){
                if(!pluginNames.contains(dependency.getName())){
                    String url= dependency.getUrl();
                    Thread downloadThread = new Thread(){
                        @Override
                        public void run(){
                            download(dependency.getName(),dependency.getUrl());
                        }

                    };

                }
            }
        }

    }
}
