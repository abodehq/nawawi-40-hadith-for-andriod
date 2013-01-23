package com.mos7af.nawawyeh;

import android.app.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;



import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;





public class ListsActivity extends Activity {
		
	ListView list;
    ListItemAdapter listItemAdapter;
    private ListsActivity _scope;
    private GetTask getTask;
    private ArrayList<HashMap<String, String>> containerList;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(R.layout.ly_lists);
        _scope = this;
        getTask = new GetTask();
        getTask.execute(); 
    }
    
    private class GetTask extends AsyncTask<Void, Void, ReturnModel> {
        @Override
        protected ReturnModel doInBackground(Void... params) {
          return GetData();
        }

        @Override
        protected void onPostExecute(ReturnModel result) {

          
        	ProgressBar loading=(ProgressBar)findViewById(R.id.loading);;
        	loading.setVisibility(View.GONE);
        	containerList = result.getheadlines();
			list= (ListView)findViewById(R.id.list);
		
			listItemAdapter = new ListItemAdapter(_scope, containerList );    
	        
	         list.setAdapter(listItemAdapter);
	         
	         list.setOnItemClickListener(new OnItemClickListener() {
	        	  @Override
	        	  public void onItemClick(AdapterView<?> parent, View view,
	        	    int position, long id) 
	        	  {
	  				Intent in = new Intent(getApplicationContext(),
	  						Nawawy.class);
	  				in.putExtra("index", position);
	  				setResult(100, in);
	  				finish();
					
				 
	        	  }
	        	});

          
        }
      }
    
    
    
    private ReturnModel GetData()
    {
    	
    	ProgressBar loading=(ProgressBar)findViewById(R.id.loading);;
    	loading.setVisibility(View.VISIBLE);
    	containerList = new ArrayList<HashMap<String, String>>();
	
        String[]  jsonArray = Nawawy.Hadiths ;
    
        for (int i = 1; i < jsonArray.length; i++) {
        
  			HashMap<String, String> item = new HashMap<String, String>();
	  	
  		item.put("nameAr", jsonArray[i]);
  		item.put("thumb",""+(i));
	  		
	  		containerList.add(item);
        }
  		ReturnModel returnModel = new ReturnModel();
  		returnModel.setheadlines(containerList);
         return returnModel;
    }
    private class ReturnModel {
        private ArrayList<HashMap<String, String>> containerList ;
     

        public ArrayList<HashMap<String, String>> getheadlines() {
          return containerList;
        }

        public void setheadlines(ArrayList<HashMap<String, String>> _songsList) {
          this.containerList = _songsList;
        }

        
      }
    
    
    
    
}
