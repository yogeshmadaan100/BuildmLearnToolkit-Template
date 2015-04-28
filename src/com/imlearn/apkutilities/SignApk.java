package com.imlearn.apkutilities;

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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.imlearntoolkit.ContentActivity;
import com.imlearn.application.MyApplication;

public class SignApk {
	public Context mContext;
	ProgressDialog pDialog;
	String inputFile,outputFile;
	String message;
	public SignApk(Context context) {
		// TODO Auto-generated constructor stub
		pDialog=new ProgressDialog(context);
		mContext=context;
	}
	private class Sign extends AsyncTask<String, Void, String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Log.e("pre execute ", "called");
			pDialog=new ProgressDialog(mContext);
			pDialog.setMessage("Generating Apk...");
	        pDialog.setIndeterminate(true);
	        
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.setCancelable(true);
	        pDialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try{
				  final Handler messageHandler = new Handler(Looper.getMainLooper()) {
				        public void handleMessage(Message msg) {
				        	super.handleMessage(msg);
				        	pDialog.setMessage(message);
				        }
				    };
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
					 URL publicKeyUrl = certi.toURL();
			         URL privateKeyUrl = key.toURL();
		
			         X509Certificate certificate = zipSigner.readPublicKey(publicKeyUrl);
			         PrivateKey privateKey = zipSigner.readPrivateKey(privateKeyUrl,null);
			         zipSigner.setKeys("toolkit",certificate,privateKey,null);
					zipSigner.setKeymode("testkey");
				
					zipSigner.addProgressListener( new ProgressListener() {
					   public void onProgress( ProgressEvent event)
					   {
					      String message = event.getMessage();
					      int percentDone = event.getPercentDone();
					      //Log.e("message ", ""+message);
					      SignApk.this.message=message;
					     messageHandler.sendEmptyMessage(0);
					      // log output or update the display here       
					   }
					   
					});
					
					zipSigner.signZip(inputFile, outputFile);
			}catch(Exception e)
			{
				Log.e("sign exception", ""+e);
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.hide();
			ContentActivity.apkSigningComplete(mContext);
		}
		
	}
	public void sign(String inputFile,String outputFile) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, GeneralSecurityException
	{
		this.inputFile=inputFile;
		this.outputFile=outputFile;
		new Sign().execute();
	}
/*public void updateMessage(String message)
{

	 pDialog.setMessage(message);
}*/
}
