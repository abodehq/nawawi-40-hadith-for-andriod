package com.mos7af.nawawyeh;


import android.app.Activity;
import android.content.Intent;

import android.graphics.Typeface;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;


import android.webkit.WebView;



import android.widget.TextView;

public class HadithDetails extends Activity 
{
	private TextView txtTitle ;
	private TextView hadith_details_btn;
	private WebView webview;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ly_details);
		webview = (WebView) findViewById(R.id.webkit);
		
		hadith_details_btn= (TextView)findViewById(R.id.hadith_details_btn);
		 String fontPath = "fonts/arabic.ttf";
	     Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
	     hadith_details_btn.setTypeface(tf);
	     hadith_details_btn.setText("رجوع");
		setupUI();
		
		hadith_details_btn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent in = new Intent(getApplicationContext(),
  						Nawawy.class);
  				
  				setResult(200, in);
  				finish();
	        }
	    });
		
		setSelectedImage(Nawawy.selectedImagePosition);
	}
	

	private void setupUI() {
		txtTitle= (TextView)findViewById(R.id.textTitle);
		 String fontPath = "fonts/arabic.ttf";
	     Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
	     txtTitle.setTypeface(tf);
		
	}
	private int selectedIndex = 0;
	private void setSelectedImage(int selectedImagePosition) {

		
		selectedIndex = selectedImagePosition;
		txtTitle.setText("تحميل ...");
		myHandler.removeCallbacks(mMyRunnable);
		myHandler.postDelayed(mMyRunnable, 1500);
		
	}
	
	 private Handler myHandler = new Handler();
	 private Runnable mMyRunnable = new Runnable()
	 {
	     @Override
	     public void run()
	     {
	 			try {
	 				
	 				webview.loadUrl("file:///android_asset/gdd"+selectedIndex+".html");
	 				webview.requestFocus();
	 				webview.setInitialScale(Nawawy.zoomFactor);
	 				txtTitle.setText(" * " + Nawawy.Hadiths[selectedIndex+1]+ " * ");
	 			} catch(Exception e)
	 			{
	 				
	 			}
	     }
	  };
	  
}