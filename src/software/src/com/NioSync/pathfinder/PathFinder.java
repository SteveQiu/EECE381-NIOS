package com.NioSync.pathfinder;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PathFinder extends Activity {

	private String start_loc=null;
	private String dest_loc=null;
	private String start_id;
	private String dest_id;
	private ImageButton pulldown;
	private LinearLayout pulldown_container;
	private RelativeLayout pathfinder_rel;
	private Map map_object;
	private MapView map_view;
	private Canvas canvas;
	private Spinner start_loc_spin,dest_loc_spin;
	private ArrayAdapter<String> start_adapt,dest_adapt;
	
    @Override	
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_finder);
        map_view = (MapView) findViewById(R.id.map_container);
        Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.raw.mcld1));
        canvas = new Canvas(bitmap);
        
        //map_view.draw(map_view.canvas);
        map_object=Map.loadMapFromFile(null);

        pulldown = (ImageButton) findViewById(R.id.pulldown);
        start_loc_spin = (Spinner) findViewById(R.id.startLoc);
        dest_loc_spin = (Spinner) findViewById(R.id.destLoc);
        start_adapt = spindownPopulate(start_loc_spin);
        dest_adapt = spindownPopulate(dest_loc_spin);
        
        start_loc_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> parent, View itemClicked, int position, long id){
        			TextView text = (TextView) itemClicked;
        			String name = text.getText().toString();
        			start_loc = name;
        	        Log.d("START LOCATION SET", start_loc);
        			start_id = map_object.getNodeIDFromName(start_loc);
        	        
        	        map_view.draw(canvas, map_object, start_id, dest_id);
        	        //map_view.invalidate();
          	}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				start_loc = null;
				
			}
        });
        dest_loc_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> parent, View itemClicked, int position, long id){
        			TextView text = (TextView) itemClicked;
        			String name = text.getText().toString();
        			dest_loc = name;
        			Log.d("DEST LOCATION SET", dest_loc);
        			dest_id = map_object.getNodeIDFromName(dest_loc);
        			map_view.draw(canvas, map_object, start_id, dest_id);
        	        //map_view.invalidate();
        			//mapview.refreshDrawableState();
        	}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				dest_loc = null;
				
			}
        });

       
        
        
       pulldown.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			showHideTools();
			
			
		}
	});
    
    }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.path_finder, menu);
        return true;
    }
    
    public void showHideTools(){
        this.pulldown_container = (LinearLayout) findViewById(R.id.linearLayout_pulldowncontainer);
    	this.pathfinder_rel = (RelativeLayout) findViewById(R.id.relativelayout_pathfinder);
        if(this.pulldown_container.getVisibility()==View.VISIBLE){
    		
        	this.pulldown_container.setVisibility(View.GONE);
    		for ( int i = 0; i < pulldown_container.getChildCount();  i++ ){
    		    View view = pulldown_container.getChildAt(i);
    		    view.setVisibility(View.GONE); // Or whatever you want to do with the view.
    		}
    		pathfinder_rel.invalidate();
    		this.pulldown.setImageResource(R.drawable.pulldown_bar);
    	}
    	else{
    		
    		this.pulldown_container.setVisibility(View.VISIBLE);
    		for ( int i = 0; i < pulldown_container.getChildCount();  i++ ){
    		    View view = pulldown_container.getChildAt(i);
    		    view.setVisibility(View.VISIBLE); // Or whatever you want to do with the view.
    		}
    		pathfinder_rel.invalidate();
    		this.pulldown.setImageResource(R.drawable.pulldown_bar_extended);
    		
    	}
    }
    public ArrayAdapter<String> spindownPopulate(Spinner spin){

        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.map_object.getNodeNames());
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(spinner_adapter);
		return spinner_adapter;
    }
    
}
