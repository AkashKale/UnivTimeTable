package com.example.univtimetable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ActivityComp extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_comp);
		TableLayout l1 = (TableLayout)findViewById(R.id.tableLayout1);
		String [] subjects=getResources().getStringArray(R.array.compTE);
		String [] dates=getResources().getStringArray(R.array.dates);
		for(int i=0;i<4;i++)
		{
			TableRow row= new TableRow(this);
			TextView rowText = new TextView(this);
			rowText.setText(dates[i]);
			rowText.setWidth(150);
			row.addView(rowText,0);
			rowText = new TextView(this);
			rowText.setText(subjects[i]);
			rowText.setLines(2);
			rowText.setWidth(280);
			row.addView(rowText,1);
			l1.addView(row);
		}
        super.onCreate(savedInstanceState);    
        
	}
}

