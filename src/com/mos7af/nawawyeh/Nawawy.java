package com.mos7af.nawawyeh;


import java.util.List;
import android.app.Activity;
import android.content.Intent;

import android.graphics.Typeface;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.View.OnClickListener;

import android.webkit.WebView;

import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;

public class Nawawy extends Activity 
{


	public static int selectedImagePosition = 0;
	private List<Drawable> drawables;
	private TextView txtTitle ;
	private TextView hadith_index_txt;
	private TextView hadith_details_btn;
	private WebView webview;
	public static int zoomFactor = -1;
	public static String[] Hadiths = {"","إنما الأعمال بالنيات","حديث جبريل عليه السلام","بني الإسلام على خمس","إن أحدكم يجمع خلقه","من أحدث في أمرنا هذا","الحلال بين والحرام بين","الدين النصيحة","أمرت أن أقاتل الناس","ما نهيتكم عنه فاجتنبوه","إن الله طيب لا يقبل إلا طيبا","دع ما يريبك إلى ما لا يريبك","من حسن إسلام المرء تركه ما لا يعنيه","لا يؤمن أحدكم حتى يحب لأخيه ما يحب","لا يحل دم امرئ مسلم إلا بإحدى ثلاث","من كان يؤمن بالله واليوم الآخر فليكرم","لا تغضب","فإذا قتلتم فأحسنوا القتلة","اتق الله حيثما كنت","احفظ الله يحفظك","إذا لم تستح فاصنع ما شئت","قل آمنت بالله ثم استقم","أرأيت إذا صليت المكتوبات","الطهور شطر الإيمان","يا عبادي إني حرمت الظلم على نفسي","ذهب أهل الدثور بالأجور","كل سلامى من الناس عليه صدقة","البر حسن الخلق","فعليكم بسنتي","ألا أدلك على أبواب الخير","إن الله فرض فرائض فلا تضيعوها","ازهد في الدنيا يحبك الله","لا ضرر ولا ضرار","البينة على المدعي","من رأى منكم منكرا فليغيره","وكونوا عباد الله إخوانا","من نفس عن مؤمن كربة","إن الله كتب الحسنات والسيئات","من عادى لي وليا فقد آذنته بالحرب","رفع عن أمتي الخطأ والنسيان","كن في الدنيا كأنك غريب","حتى يكون هواه تبعا لما جئت به","نك ما دعوتني ورجوتني"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webkit);
		hadith_index_txt= (TextView)findViewById(R.id.hadith_index_txt);
		hadith_details_btn= (TextView)findViewById(R.id.hadith_details_btn);
		 String fontPath = "fonts/arabic.ttf";
	     Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
	     hadith_details_btn.setTypeface(tf);
	     hadith_details_btn.setText("شرح الحديث");
		
		setupUI();
		hadith_details_btn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent i = new Intent(getApplicationContext(), HadithDetails.class);
				startActivityForResult(i, 100);	
	        }
	    });
		
		
		ImageButton zoomOut = (ImageButton) findViewById(R.id.ZoomOut); // your image button
		zoomOut.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	int c = (int) (100 * webview.getScale());
	        	if(c>75)
	        	{
	        		webview.zoomOut();
	        		zoomFactor = (int) (100 * webview.getScale());
	        	}
	        }
	    });
		ImageButton zoomIn = (ImageButton) findViewById(R.id.ZoomIn); // your image button

	    // click event on your button
		zoomIn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	int c = (int) (100 * webview.getScale());
	        	if(c<185)
	        	{
	        		webview.zoomIn();
	        		zoomFactor = (int) (100 * webview.getScale());
	        				
	        	}
	        }
	    });
		
		ImageButton gList = (ImageButton) findViewById(R.id.gList); // your image button

	    // click event on your button
		gList.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	
	        		Intent i = new Intent(getApplicationContext(), ListsActivity.class);
					startActivityForResult(i, 100);			
	        	
	        }
	    });
		setSelectedImage(selectedImagePosition);
	}
	

	private void setupUI() {

		
		
		txtTitle= (TextView)findViewById(R.id.textTitle);
		 String fontPath = "fonts/arabic.ttf";
	     Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
	     txtTitle.setTypeface(tf);
		ImageView leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
		ImageView rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
		
		
		leftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(selectedImagePosition>0)
				{
					--selectedImagePosition;
					setSelectedImage(selectedImagePosition);
				}
				
			}
		});

		rightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(selectedImagePosition<Hadiths.length-2)
				{
					++selectedImagePosition;
					setSelectedImage(selectedImagePosition);
				}
				

			}
		});


		

	}
	private int selectedIndex = 0;
	private void setSelectedImage(int selectedImagePosition) {

		
		selectedIndex = selectedImagePosition;
		txtTitle.setText("تحميل ...");
		hadith_index_txt.setText(""+(selectedImagePosition+1));
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
	 				
	 				webview.loadUrl("file:///android_asset/gd"+selectedIndex+".html");
	 				webview.requestFocus();
	 				if(zoomFactor!=-1)
	 					webview.setInitialScale(zoomFactor);
	 				txtTitle.setText(" * " + Hadiths[selectedIndex+1]+ " * ");
	 			} catch(Exception e)
	 			{
	 				
	 			}
	     }
	  };
	  @Override
	    protected void onActivityResult(int requestCode,
	                                     int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if(resultCode == 100){
	        	selectedImagePosition = data.getExtras().getInt("index");
	        	setSelectedImage(data.getExtras().getInt("index"));
	        }
	 
	    }
}