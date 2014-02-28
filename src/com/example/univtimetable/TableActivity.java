package com.example.univtimetable;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_table);
		TableLayout l1 = (TableLayout)findViewById(R.id.contentsTab);
		String str=getIntent().getStringExtra("data");
		String [] subjects = null;
		String [] dates;
		if(str.equals("COMP-TE"))
		{			
			subjects=getResources().getStringArray(R.array.compTE);
		}
		dates=getResources().getStringArray(R.array.dates);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Dense-Regular.otf");
		TableRow row=new TableRow(this);
		TextView rowText=new TextView(this);
		rowText.setText("DAY & DATE");
		rowText.setTypeface(tf);
		rowText.setTextColor(Color.parseColor("#FFFFFF"));
		row.addView(rowText,0);
		rowText=new TextView(this);
		rowText.setText("SUBJECT");
		rowText.setTypeface(tf);
		rowText.setTextColor(Color.parseColor("#FFFFFF"));
		row.addView(rowText,1);
		int clr;
		clr=Color.parseColor("#17b794");
		row.setBackgroundColor(clr);
		l1.addView(row);
		for(int i=0;i<5;i++)
		{
			row= new TableRow(this);
			rowText = new TextView(this);
			rowText.setText(dates[i]);
			rowText.setWidth(150);
			rowText.setTypeface(tf);
			row.addView(rowText,0);
			rowText = new TextView(this);
			rowText.setText(subjects[i]);
			rowText.setLines(2);
			rowText.setWidth(280);
			rowText.setTypeface(tf);
			row.addView(rowText,1);
			l1.addView(row);
		}
		
        super.onCreate(savedInstanceState);       
	}
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	  return;
	}
	@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // your code here
    	View root=findViewById(R.id.contentsTab);
    	View container=findViewById(R.id.containerTab);
    	scaleContents(root,container);
    }
	private void scaleContents(View rootView, View container) {
		// Compute the scaling ratio
		float xScale = (float) container.getWidth() / rootView.getWidth();
		float yScale = (float) container.getHeight() / rootView.getHeight();
		float scale = Math.min(xScale, yScale);

		// Scale our contents
		scaleViewAndChildren(rootView, scale);
	}

	// Scale the given view, its contents, and all of its children by the given
	// factor.
	public static void scaleViewAndChildren(View root, float scale) {
		// Retrieve the view's layout information
		ViewGroup.LayoutParams layoutParams = root.getLayoutParams();

		// Scale the view itself
		if (layoutParams.width != ViewGroup.LayoutParams.FILL_PARENT
				&& layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
			layoutParams.width *= scale;
		}
		if (layoutParams.height != ViewGroup.LayoutParams.FILL_PARENT
				&& layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
			layoutParams.height *= scale;
		}

		// If this view has margins, scale those too
		if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
			marginParams.leftMargin *= scale;
			marginParams.rightMargin *= scale;
			marginParams.topMargin *= scale;
			marginParams.bottomMargin *= scale;
		}

		// Set the layout information back into the view
		root.setLayoutParams(layoutParams);
		// Scale the view's padding
		root.setPadding((int) (root.getPaddingLeft() * scale),
				(int) (root.getPaddingTop() * scale),
				(int) (root.getPaddingRight() * scale),
				(int) (root.getPaddingBottom() * scale));

		// If the root view is a TextView, scale the size of its text
		if (root instanceof TextView) {
			TextView textView = (TextView) root;
			textView.setTextSize(textView.getTextSize() * scale);
		}

		// If the root view is a ViewGroup, scale all of its children
		// recursively
		if (root instanceof ViewGroup) {
			ViewGroup groupView = (ViewGroup) root;
			for (int cnt = 0; cnt < groupView.getChildCount(); ++cnt)
				scaleViewAndChildren(groupView.getChildAt(cnt), scale);
		}
	}
}

