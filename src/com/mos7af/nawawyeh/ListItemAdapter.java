package com.mos7af.nawawyeh;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListItemAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

    
    public ListItemAdapter(Activity a, ArrayList<HashMap<String, String>> _source) {
        activity = a;
        data=_source;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.ly_lists_item, null);

        TextView reciterNameAr = (TextView)vi.findViewById(R.id.title); 
        
        HashMap<String, String> sura = new HashMap<String, String>();
        sura = data.get(position);
        String fontPath = "fonts/arabic.ttf";
     
	     Typeface tf = Typeface.createFromAsset(activity.getAssets(), fontPath);
	     reciterNameAr.setTypeface(tf);
        reciterNameAr.setText(" * " + sura.get("nameAr") + " * ");
    
        return vi;
    }
}