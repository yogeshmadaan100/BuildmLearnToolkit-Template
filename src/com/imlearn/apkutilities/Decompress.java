package com.imlearn.apkutilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.util.Log;

public class Decompress { 
	  private String _zipFile; 
	  private String _location; 
	 
	  public Decompress(String zipFile, String location) { 
	    _zipFile = zipFile; 
	    _location = location; 
	    Log.e("uncompressed destination", _location);
	    _dirChecker(""); 
	  } 
	 
	 /* public void unzip() { 
	    try  { 
	      FileInputStream fin = new FileInputStream(_zipFile); 
	      ZipInputStream zin = new ZipInputStream(fin); 
	      ZipEntry ze = null; 
	      while ((ze = zin.getNextEntry()) != null) { 
	        Log.v("Decompress", "Unzipping " + ze.getName()); 
	 
	        if(ze.isDirectory()) { 
	          _dirChecker(ze.getName()); 
	        } else { 
	          FileOutputStream fout = new FileOutputStream(_location + ze.getName()); 
	          for (int c = zin.read(); c != -1; c = zin.read()) { 
	            fout.write(c); 
	          } 
	 
	          zin.closeEntry(); 
	          fout.close(); 
	        } 
	         
	      } 
	      zin.close(); 
	    } catch(Exception e) { 
	      Log.e("Decompress", "unzip", e); 
	    } 
	 
	  } */
	  
	  public static void unzip(File zipFile, File targetDirectory) throws IOException {
		    ZipInputStream zis = new ZipInputStream(
		            new BufferedInputStream(new FileInputStream(zipFile)));
		    try {
		        ZipEntry ze;
		        int count;
		        byte[] buffer = new byte[8192];
		        while ((ze = zis.getNextEntry()) != null) {
		            File file = new File(targetDirectory, ze.getName());
		            File dir = ze.isDirectory() ? file : file.getParentFile();
		            if (!dir.isDirectory() && !dir.mkdirs())
		                throw new FileNotFoundException("Failed to ensure directory: " +
		                        dir.getAbsolutePath());
		            if (ze.isDirectory())
		                continue;
		            FileOutputStream fout = new FileOutputStream(file);
		            try {
		                while ((count = zis.read(buffer)) != -1)
		                    fout.write(buffer, 0, count);
		            } finally {
		                fout.close();
		            }
		            /* if time should be restored as well
		            long time = ze.getTime();
		            if (time > 0)
		                file.setLastModified(time);
		            */
		        }
		    } finally {
		        zis.close();
		    }
		}
	 
	  private void _dirChecker(String dir) { 
	    File f = new File(_location + dir); 
	 
	    if(!f.isDirectory()) { 
	      f.mkdirs(); 
	    } 
	  } 
}