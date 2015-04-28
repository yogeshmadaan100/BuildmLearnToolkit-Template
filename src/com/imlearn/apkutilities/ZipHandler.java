package com.imlearn.apkutilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

public class ZipHandler {
	
	public boolean zipFileAtPath(String sourcePath, String toLocation) {
	    // ArrayList<String> contentList = new ArrayList<String>();
	    final int BUFFER = 2048;


	    File sourceFile = new File(sourcePath);
	    Log.e("source file", ""+sourcePath);
	    try {
	        BufferedInputStream origin = null;
	        FileOutputStream dest = new FileOutputStream(toLocation);
	        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
	                dest));
	        if (sourceFile.isDirectory()) {
	            zipSubFolder(out, sourceFile, sourceFile.getParent().length());
	        } else {
	            byte data[] = new byte[BUFFER];
	            FileInputStream fi = new FileInputStream(sourcePath);
	            origin = new BufferedInputStream(fi, BUFFER);
	            ZipEntry entry = new ZipEntry(getLastPathComponent(sourcePath));
	            out.putNextEntry(entry);
	            int count;
	            while ((count = origin.read(data, 0, BUFFER)) != -1) {
	                out.write(data, 0, count);
	            }
	        }
	        out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}

	/*
	 * 
	 * Zips a subfolder
	 * 
	 */

	private void zipSubFolder(ZipOutputStream out, File folder,
	        int basePathLength) throws IOException {

	    final int BUFFER = 2048;

	    File[] fileList = folder.listFiles();
	    BufferedInputStream origin = null;
	    for (File file : fileList) {
	        if (file.isDirectory()) {
	            zipSubFolder(out, file, basePathLength);
	        } else {
	            byte data[] = new byte[BUFFER];
	            String unmodifiedFilePath = file.getPath();
	            String relativePath = unmodifiedFilePath
	                    .substring(basePathLength);
	            Log.i("ZIP SUBFOLDER", "Relative Path : " + relativePath.substring(6));
	            FileInputStream fi = new FileInputStream(unmodifiedFilePath);
	            origin = new BufferedInputStream(fi, BUFFER);
	            ZipEntry entry = new ZipEntry(relativePath.substring(6));
	            out.putNextEntry(entry);
	            int count;
	            while ((count = origin.read(data, 0, BUFFER)) != -1) {
	                out.write(data, 0, count);
	            }
	            origin.close();
	        }
	    }
	}

	/*
	 * gets the last path component
	 * 
	 * Example: getLastPathComponent("downloads/example/fileToZip");
	 * Result: "fileToZip"
	 */
	public String getLastPathComponent(String filePath) {
	    String[] segments = filePath.split("/");
	    String lastPathComponent = segments[segments.length - 1];
	    return lastPathComponent;
	}
	
	
	public void zipDir(String zipFileName, String dir) throws Exception {
	    File dirObj = new File(dir);
	    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
	    System.out.println("Creating : " + zipFileName);
	    addDir(dirObj, out);
	    out.close();
	  }

	  static void addDir(File dirObj, ZipOutputStream out) throws IOException {
	    File[] files = dirObj.listFiles();
	    byte[] tmpBuf = new byte[1024];

	    for (int i = 0; i < files.length; i++) {
	      if (files[i].isDirectory()) {
	        addDir(files[i], out);
	        continue;
	      }
	      FileInputStream in = new FileInputStream(files[i].getPath());
	      System.out.println(" Adding: " + files[i].getPath());
	      out.putNextEntry(new ZipEntry(files[i].getPath()));
	      int len;
	      while ((len = in.read(tmpBuf)) > 0) {
	        out.write(tmpBuf, 0, len);
	      }
	      out.closeEntry();
	      in.close();
	    }
	  }
	
	
	public void copyFile(Context context,String filename) {
	    AssetManager assetManager = context.getAssets();
	    String root = Environment.getExternalStorageDirectory().toString();
	    File myDir = new File(root + "/buildmlearnFiles/temp/");    
	    myDir.mkdirs();
	    File file = new File (myDir, filename);
	    InputStream in = null;
	    OutputStream out = null;
	    String newFileName = null;
	    try {
	        Log.i("tag", "copyFile() "+filename);
	        in = assetManager.open(filename);
	        if (filename.endsWith(".jpg")) // extension was added to avoid compression on APK file
	            newFileName = myDir + filename.substring(0, filename.length()-4);
	        else
	            newFileName = myDir + filename;
	        out = new FileOutputStream(file);

	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1) {
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        in = null;
	        out.flush();
	        out.close();
	        out = null;
	    } catch (Exception e) {
	        Log.e("tag", "Exception in copyFile() of "+newFileName);
	        Log.e("tag", "Exception in copyFile() "+e.toString());
	    }
	   
	}
	
	
	 
}
