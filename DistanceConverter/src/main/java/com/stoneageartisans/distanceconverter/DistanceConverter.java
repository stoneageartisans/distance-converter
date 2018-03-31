package com.stoneageartisans.distanceconverter;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

public class DistanceConverter extends Activity {
		
	// Constants
	private static final int millimeters = 0;
	private static final int centimeters = 1;
	private static final int inches = 2;
	private static final int decimeters = 3;
	private static final int feet = 4;
	private static final int yards = 5;
	private static final int meters = 6;
	private static final int dekameters = 7;
	private static final int chains = 8;
	private static final int hectometers = 9;
	private static final int furlongs = 10;
	private static final int kilometers = 11;
	private static final int miles = 12;
	
	// Variables
	private EditText edittext_input;	
	private TextView textview_output;
	private int font_size;
	private int units_from;
	private int units_to;
	private Spinner spinner_units_from;
	private Spinner spinner_units_to;
	private String[] units;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.layout_main);
		initialize();
		
	}
	
	@Override
	public void onResume() {
		
	    super.onResume();
	    
	}
	
	@Override
	public void onPause() {
		
	    super.onPause();
	    
	}
	
	private void initialize() {
		
		units = this.getResources().getStringArray(R.array.units);
		units_from = 0;
		units_to = 0;
		
        // Calculate font size based on screen dimensions
		font_size = 20;
        
        // Set title font size
        ( (TextView) this.findViewById(R.id.title) ).setTextSize(font_size);

        edittext_input = (EditText) this.findViewById(R.id.input);        
        edittext_input.setTextSize(font_size);
        
        ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, units) {
    		public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);                
                ( (TextView) view ).setTextSize(font_size);
                return view;
    		}
    		public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
               ( (TextView) view ).setTextSize(font_size);
               return view;
    		}
    	};
    	array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	spinner_units_from = (Spinner) this.findViewById(R.id.units_from);
    	spinner_units_from.setAdapter(array_adapter); 
    	
    	textview_output = (TextView) this.findViewById(R.id.output);    	
    	textview_output.setTextSize(font_size);
    	
    	spinner_units_to = (Spinner) this.findViewById(R.id.units_to);
    	spinner_units_to.setAdapter(array_adapter);
    	
    	
    	
    	edittext_input.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,	KeyEvent event) {
				convert_units();
				return false;
			}        	
        });
    	
    	spinner_units_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				units_from = position;
				convert_units();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub				
			}    		
		});
    	
    	spinner_units_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				units_to = position;
				convert_units();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub				
			}    		
		});
    	
    	Button convert = ( (Button) this.findViewById(R.id.convert) );
    	convert.setTextSize(font_size);
    	convert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				convert_units();				
			}    		
    	});
        
    }
	
    private void convert_units() {
    	
    	String result = "";    	
    	try {
    		double input = Double.parseDouble( edittext_input.getText().toString() );
	    	switch(units_from) {
	    		case inches:  // from inches
	    			switch(units_to) {
	    				case inches:  // to inches
	    					result = String.valueOf(input);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 12);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 36);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 792);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 7920);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 63360);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input * 25.4);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 2.54);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 0.254);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 0.0254);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 0.00254);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input * 0.000254);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input * 0.0000254);
	    					break;
	    			}
	    			break;
	    		case feet:  // from feet
	    			switch(units_to) {
						case inches:  // to inches
							result = String.valueOf(input * 12);
							break;
						case feet:  // to feet
							result = String.valueOf(input);
							break;
						case yards:  // to yards
							result = String.valueOf(input / 3);
							break;
						case chains:  // to chains
	    					result = String.valueOf(input / 66);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 660);
	    					break;
						case miles:  // miles
							result = String.valueOf(input / 5280);
							break;
						case millimeters:  // to millimeters
	    					result = String.valueOf(input * 304.8);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 30.48);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 3.048);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 0.3048);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 0.03048);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input * 0.003048);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input * 0.0003048);
	    					break;
	    			}
	    			break;
	    		case yards:  // from yards
	    			switch(units_to) {
						case inches:  // to inches
							result = String.valueOf(input * 36);
							break;
						case feet:  // to feet
							result = String.valueOf(input * 3);
							break;
						case yards:  // to yards
							result = String.valueOf(input);
							break;
						case chains:  // to chains
	    					result = String.valueOf(input / 22);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 220);
	    					break;
						case miles:  // miles
							result = String.valueOf(input / 1760);
							break;
						case millimeters:  // to millimeters
	    					result = String.valueOf(input * 914.4);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 91.44);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 9.144);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 0.9144);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 0.09144);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input * 0.009144);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input * 0.0009144);
	    					break;
	    			}
	    			break;
	    		case chains:  // from chains
	    			switch(units_to) {
						case inches:  // to inches
							result = String.valueOf(input * 792);
							break;
						case feet:  // to feet
							result = String.valueOf(input * 66);
							break;
						case yards:  // to yards
							result = String.valueOf(input * 22);
							break;
						case chains:  // to chains
	    					result = String.valueOf(input);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 10);
	    					break;
						case miles:  // miles
							result = String.valueOf(input / 80);
							break;
						case millimeters:  // to millimeters
	    					result = String.valueOf(input * 20116.8);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 2011.68);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 201.168);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 20.1168);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 2.01168);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input * 0.201168);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input * 0.0201168);
	    					break;
	    			}
	    			break;
	    		case furlongs:  // from furlongs
	    			switch(units_to) {
						case inches:  // to inches
							result = String.valueOf(input * 7920);
							break;
						case feet:  // to feet
							result = String.valueOf(input * 660);
							break;
						case yards:  // to yards
							result = String.valueOf(input * 220);
							break;
						case chains:  // to chains
	    					result = String.valueOf(input * 10);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input);
	    					break;
						case miles:  // miles
							result = String.valueOf(input / 8);
							break;
						case millimeters:  // to millimeters
	    					result = String.valueOf(input * 201168);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 20116.8);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 2011.68);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 201.168);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 20.1168);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input * 2.01168);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input * 0.201168);
	    					break;
	    			}
	    			break;
	    		case miles:  // from miles
	    			switch(units_to) {
						case inches:  // to inches
							result = String.valueOf(input * 63360);
							break;
						case feet:  // to feet
							result = String.valueOf(input * 5280);
							break;
						case yards:  // to yards
							result = String.valueOf(input * 1760);
							break;
						case chains:  // to chains
	    					result = String.valueOf(input * 80);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input * 8);
	    					break;
						case miles:  // miles
							result = String.valueOf(input);
							break;
						case millimeters:  // to millimeters
	    					result = String.valueOf(input * 1609344);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 160934.4);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 16093.44);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 1609.344);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 160.9344);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input * 16.09344);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input * 1.609344);
	    					break;
	    			}
	    			break;
	    		case millimeters:  // from millimeters
	    			switch(units_to) {	    				
	    				case inches:  // to inches
	    					result = String.valueOf(input / 25.4);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 304.8);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 914.4);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 20116.8);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 201168);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 1609344);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input / 10);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input / 100);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input / 1000);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input / 10000);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input / 100000);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input / 1000000);
	    					break;
	    			}
	    			break;
	    		case centimeters:  // from centimeters
	    			switch(units_to) {
		    			case inches:  // to inches
	    					result = String.valueOf(input / 2.54);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 30.48);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 91.44);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 2011.68);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 20116.8);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 160934.4);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input * 10);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input / 10);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input / 100);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input / 1000);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input / 10000);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input / 100000);
	    					break;
	    			}
	    			break;
	    		case decimeters:  // from decimeters
	    			switch(units_to) {
		    			case inches:  // to inches
	    					result = String.valueOf(input / 0.254);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 3.048);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 9.144);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 201.168);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 2011.68);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 16093.44);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input * 100);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 10);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input / 10);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input / 100);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input / 1000);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input / 10000);
	    					break;
	    			}
	    			break;
	    		case meters:  // from meters
	    			switch(units_to) {
		    			case inches:  // to inches
	    					result = String.valueOf(input / 0.0254);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 0.3048);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 0.9144);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 20.1168);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 201.168);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 1609.344);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input * 1000);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 100);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 10);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input / 10);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input / 100);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input / 1000);
	    					break;
	    			}
	    			break;
	    		case dekameters:  // from dekameters
	    			switch(units_to) {
		    			case inches:  // to inches
	    					result = String.valueOf(input / 0.00254);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 0.03048);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 0.09144);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 2.01168);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 20.1168);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 160.9344);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input * 10000);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 1000);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 100);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 10);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input / 10);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input / 100);
	    					break;
	    			}
	    			break;    		
	    		case hectometers:  // from hectometers
	    			switch(units_to) {
		    			case inches:  // to inches
	    					result = String.valueOf(input / 0.000254);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 0.003048);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 0.009144);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 0.201168);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 2.01168);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 16.09344);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input * 100000);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 10000);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 1000);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 100);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 10);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input / 10);
	    					break;
	    			}
	    			break;
	    		case kilometers:  // from kilometers
	    			switch(units_to) {
		    			case inches:  // to inches
	    					result = String.valueOf(input / 0.0000254);
	    					break;
	    				case feet:  // to feet
	    					result = String.valueOf(input / 0.0003048);
	    					break;
	    				case yards:  // to yards
	    					result = String.valueOf(input / 0.0009144);
	    					break;
	    				case chains:  // to chains
	    					result = String.valueOf(input / 0.0201168);
	    					break;
	    				case furlongs:  // to furlongs
	    					result = String.valueOf(input / 0.201168);
	    					break;
	    				case miles:  // to miles
	    					result = String.valueOf(input / 1.609344);
	    					break;
	    				case millimeters:  // to millimeters
	    					result = String.valueOf(input * 1000000);
	    					break;
	    				case centimeters:  // to centimeters
	    					result = String.valueOf(input * 100000);
	    					break;
	    				case decimeters:  // to decimeters
	    					result = String.valueOf(input * 10000);
	    					break;
	    				case meters:  // to meters
	    					result = String.valueOf(input * 1000);
	    					break;
	    				case dekameters:  // to dekameters
	    					result = String.valueOf(input * 100);
	    					break;
	    				case hectometers:  // to hectometers
	    					result = String.valueOf(input * 10);
	    					break;
	    				case kilometers:  // kilometers
	    					result = String.valueOf(input);
	    					break;
	    			}
	    			break;
	    	}
    	} catch(NumberFormatException ex) {
    		result = "";
    	}
    	textview_output.setText(result);
    	
    }
	
}
