package cxplugins.cxfundamental.minecraft.plugin;

import cxplugins.cxfundamental.minecraft.dependiences.ExtClasspathLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

public class RuntimeLoader {

    public static void addClasspath(File file){

        URL url= null;//将File类型转为URL类型，file为jar包路径
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //得到系统类加载器
        URLClassLoader urlClassLoader= (URLClassLoader) ClassLoader.getSystemClassLoader();
    //因为URLClassLoader中的addURL方法的权限为protected所以只能采用反射的方法调用addURL方法
        Method add = null;
        try {
            add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        add.setAccessible(true);
        try {
            add.invoke(urlClassLoader, new Object[] {url });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public static void download(String url, String saveDir, String fileName) {

        BufferedOutputStream bos = null;
        InputStream is = null;
        try {
            byte[] buff = new byte[8192];
            URL ur=new URL(url);
            URLConnection connection=ur.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)"); //防止报403错误。
            connection.connect();
            is =connection.getInputStream();
            File file = new File(saveDir, fileName);
            file.getParentFile().mkdirs();
            bos = new BufferedOutputStream(new FileOutputStream(file));
            int count = 0;
            while ( (count = is.read(buff)) != -1) {
                bos.write(buff, 0, count);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    public static void checkRuntime(){
        /*System.out.println("正在检测前置环境是否下载");
        if(!new File("plugins/CXPlugins/runtime/CXBaseRuntime.jar").exists()){
            System.out.println(new File("plugins/CXPlugins/runtime/CXBaseRuntime.jar").getAbsolutePath());
            System.out.println("前置环境未下载,正在下载中");
            download("https://gitee.com/yuncaiyuye/cxbase-runtime/raw/master/CXBaseRuntime-Kotlin%201.5.10.jar","/plugins/CXPlugins/runtime","CXBaseRuntime.jar");
        }*/
        File file=new File("plugins/CXPlugins/runtime/CXBaseRuntime.jar");
        System.out.println("前置环境加载中");
        try {
            Plugin plugin=Bukkit.getServer().getPluginManager().loadPlugin(file);
            Bukkit.getServer().getPluginManager().enablePlugin(plugin);
        } catch (InvalidPluginException e) {
            System.out.println("前置环境加载失败");
        } catch (InvalidDescriptionException e) {
            System.out.println("前置环境加载失败");
        }
    }

    public static void loadRuntime(){
        System.out.println("前置环境加载中");
        File runtimeDir=new File("plugins/CXPlugins/runtime");
        if(!runtimeDir.exists()) runtimeDir.mkdirs();
        File[] files=runtimeDir.listFiles();
        for(File jar : files){
            System.out.println("loading"+jar.getAbsolutePath());
            if(jar.getName().endsWith(".jar")){
                ExtClasspathLoader.loadJarFile(jar.getAbsolutePath());
            }
        }
        System.out.println("前置环境加载成功");
    }

}
