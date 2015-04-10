package com.buildmlearn.apkutilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import kellinwood.security.zipsigner.ProgressEvent;
import kellinwood.security.zipsigner.ProgressListener;
import kellinwood.security.zipsigner.ZipSigner;
import android.content.res.AssetManager;
import android.os.Environment;
import android.provider.SyncStateContract.Constants;
import android.util.Log;

import com.buildmlearn.application.MyApplication;

public class SignApk {
	
	public void sign(String inputFile,String outputFile) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, GeneralSecurityException
	{
		ZipSigner zipSigner = new ZipSigner();
		AssetManager assetManager = MyApplication.mApplication.getAssets();
		String root = Environment.getExternalStorageDirectory().toString();
		    File myDir = new File(root + "/buildmlearnFiles/temp/");    
		    myDir.mkdirs();
		    File certi = new File (myDir,"certificate.pem");
		    File key = new File (myDir,"key.pk8");
		    InputStream in = null;
		    OutputStream out = null;
		    String newFileName = null;
		    try {
		        
		        in = assetManager.open("certificate.pem");
		       
		        out = new FileOutputStream(certi);

		        byte[] buffer = new byte[1024];
		        int read;
		        while ((read = in.read(buffer)) != -1) {
		            out.write(buffer, 0, read);
		        }
		        in = assetManager.open("key.pk8");
			       
		        out = new FileOutputStream(key);
		        
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
		 URL publicKeyUrl = new URL(certi.toString());
         URL privateKeyUrl = new URL(key.toString());

         X509Certificate certificate = zipSigner.readPublicKey(publicKeyUrl);
         PrivateKey privateKey = zipSigner.readPrivateKey(privateKeyUrl,null);
         zipSigner.setKeys("KKcorps",certificate,privateKey,null);
		zipSigner.setKeymode("testkey");
		zipSigner.addProgressListener( new ProgressListener() {
		   public void onProgress( ProgressEvent event)
		   {
		      String message = event.getMessage();
		      int percentDone = event.getPercentDone();
		      Log.e("message ", ""+message);
		      // log output or update the display here       
		   }
		});
		zipSigner.signZip(inputFile, outputFile);
	}

}
