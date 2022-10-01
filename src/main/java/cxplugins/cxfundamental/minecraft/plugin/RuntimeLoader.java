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

        URL url= null;//��File����תΪURL���ͣ�fileΪjar��·��
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //�õ�ϵͳ�������
        URLClassLoader urlClassLoader= (URLClassLoader) ClassLoader.getSystemClassLoader();
    //��ΪURLClassLoader�е�addURL������Ȩ��Ϊprotected����ֻ�ܲ��÷���ķ�������addURL����
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
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)"); //��ֹ��403����
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
        /*System.out.println("���ڼ��ǰ�û����Ƿ�����");
        if(!new File("plugins/CXPlugins/runtime/CXBaseRuntime.jar").exists()){
            System.out.println(new File("plugins/CXPlugins/runtime/CXBaseRuntime.jar").getAbsolutePath());
            System.out.println("ǰ�û���δ����,����������");
            download("https://gitee.com/yuncaiyuye/cxbase-runtime/raw/master/CXBaseRuntime-Kotlin%201.5.10.jar","/plugins/CXPlugins/runtime","CXBaseRuntime.jar");
        }*/
        File file=new File("plugins/CXPlugins/runtime/CXBaseRuntime.jar");
        System.out.println("ǰ�û���������");
        try {
            Plugin plugin=Bukkit.getServer().getPluginManager().loadPlugin(file);
            Bukkit.getServer().getPluginManager().enablePlugin(plugin);
        } catch (InvalidPluginException e) {
            System.out.println("ǰ�û�������ʧ��");
        } catch (InvalidDescriptionException e) {
            System.out.println("ǰ�û�������ʧ��");
        }
    }

    public static void loadRuntime(){
        System.out.println("ǰ�û���������");
        File runtimeDir=new File("plugins/CXPlugins/runtime");
        if(!runtimeDir.exists()) runtimeDir.mkdirs();
        File[] files=runtimeDir.listFiles();
        for(File jar : files){
            System.out.println("loading"+jar.getAbsolutePath());
            if(jar.getName().endsWith(".jar")){
                ExtClasspathLoader.loadJarFile(jar.getAbsolutePath());
            }
        }
        System.out.println("ǰ�û������سɹ�");
    }

}
