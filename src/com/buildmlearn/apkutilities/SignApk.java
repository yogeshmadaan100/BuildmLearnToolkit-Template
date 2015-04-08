package com.buildmlearn.apkutilities;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import kellinwood.security.zipsigner.ProgressEvent;
import kellinwood.security.zipsigner.ProgressListener;
import kellinwood.security.zipsigner.ZipSigner;
import android.util.Log;

public class SignApk {
	
	public void sign(String inputFile,String outputFile) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, GeneralSecurityException
	{
		ZipSigner zipSigner = new ZipSigner();
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
