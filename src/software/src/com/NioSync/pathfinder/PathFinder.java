package com.NioSync.pathfinder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PathFinder extends Activity {

	private EditText start_loc,dest_loc;
	private Button view_map;
	private ImageView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_finder);
        
        Map testmap = Map.loadMapFromFile(null);
        
		
        this.view_map = (Button) this.findViewById(R.id.view_map);
        this.view_map.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewMap();
			}
		});
  
    }
    
    
    public void viewMap(){
    	this.map = (ImageView) this.findViewById(R.id.map_container);
    	if (map.getVisibility()==View.VISIBLE)
    		map.setVisibility(View.INVISIBLE);
    	else
    		map.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.path_finder, menu);
        return true;
    }
    
}
