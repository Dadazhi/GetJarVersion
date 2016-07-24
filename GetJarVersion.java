package com.collectivity.java;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GetJarVersion {

private static HashMap<String,String> getClassVersion() {

HashMap<String,String> map = new HashMap<String,String>();
map.put("45.3", "1.1");
map.put("46.0", "1.2");
map.put("47.0", "1.3");
map.put("48.0", "1.4");
map.put("49.0", "1.5");
map.put("50.0", "1.6");
map.put("51.0", "1.7");
map.put("52.0", "1.8");
map.put("53.0", "1.9");

return map;
}

public static void getVersion(String jarpath) {
	
	ZipInputStream zip = null;
	String substr1 = "Created-By:";
	String substr2 = "Build-Jdk:";
	String Version = null;

	byte[] b = null;
	try {
	zip = new ZipInputStream(new FileInputStream(jarpath));

	for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip
	.getNextEntry()) {

	if (!entry.isDirectory() && entry.getName().endsWith(".MF")) {
	b = new byte[1000];
	zip.read(b);
	zip.closeEntry();
	String str = new String(b);
	String[] array1 = str.split("\r\n");
	str = null;
	b = null;
	for (int i = 0; i < array1.length; i++) {
	String[] array2 = null;
	if (array1[i].contains(substr2) || array1[i].contains("Build-Version")) {
	array2 = array1[i].split(":");
	Version = array2[1];

	} else if (array1[i].contains(substr1))

	{
	array2 = array1[i].split(":");
	Version = array2[1];

	}

	}

	}

	}

	if (Version == null) {

	zip = new ZipInputStream(new FileInputStream(jarpath));
	for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip
	.getNextEntry()) {

	if (!entry.isDirectory()
	&& entry.getName().endsWith(".class")) {
	b = new byte[1000];
	zip.read(b);
	zip.closeEntry();
	}
	}
	if (b != null) {
	InputStream in = new ByteArrayInputStream(b);
	DataInputStream data = new DataInputStream(in);
	if (0xCAFEBABE != data.readInt()) {
	throw new IOException("invalid header");
	}
	int minor = data.readUnsignedShort();
	int major = data.readUnsignedShort();
	Version = ((String) GetJarVersion.getClassVersion().get(major + "." + minor))!=null?(String) GetJarVersion.getClassVersion().get(major + "." + minor):(major + "." + minor);
	b = null;
	}else{
		System.out.println("No class file found in the jar..");
	}

	}

	System.out.println("Java Version:" + Version);
	zip.close();

	} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}

	}

	
/**
* @param args
*/
public static void main(String[] args) {
	
	
// TODO Auto-generated method stub

	if(args.length>0 && args[0].endsWith(".jar")){
	getVersion(args[0]);
	}else
		System.out.println("Please enter valid jar file name with absolute path as command line argument and try again..");

}

}