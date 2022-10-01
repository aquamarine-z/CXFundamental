package cxplugins.cxfundamental.minecraft.dependiences;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ExtClasspathLoader {



    public static void loadJarFile(String pathToJar){
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(pathToJar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration e = jarFile.entries();

        URL[] urls = new URL[0];
        try {
            urls = new URL[]{ new URL("jar:file:" + pathToJar+"!/") };
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }

// -6 because of .class

            String className = je.getName().substring(0,je.getName().length()-6);

            className = className.replace('/', '.');
            System.out.println("loading:"+className);
            try {
                Class c = cl.loadClass(className);
            } catch (Exception ex) {
                System.out.println(className+"Failed to load class");
            }
            catch (NoClassDefFoundError ex) {
                System.out.println(className+"Failed to load class");
            }

        }

    }
}