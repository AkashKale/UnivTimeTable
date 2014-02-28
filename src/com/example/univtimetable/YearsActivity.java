package com.example.univtimetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class YearsActivity extends Activity {
	ImageButton imgBtn1;
	ImageButton imgBtn2;
	ImageButton imgBtn3;
	String str;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_years);
        str=getIntent().getStringExtra("Branch");
        super.onCreate(savedInstanceState);
        addListenerOnButton();
    }
	
	public void addListenerOnButton() {
    	imgBtn1 = (ImageButton) findViewById(R.id.imageButton1Yr);
		imgBtn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(str.contains("-"))
				{
					str=str.substring(0,str.length()-3);
				}
				str=str+"-SE";
				Intent i = new Intent(YearsActivity.this, TableActivity.class).putExtra("data", str);
				startActivity(i);
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});
		imgBtn2 = (ImageButton) findViewById(R.id.imageButton2Yr);
   		imgBtn2.setOnClickListener(new OnClickListener() {   			
			@Override
			public void onClick(View arg0) {
				if(str.contains("-"))
				{
					str=str.substring(0,str.length()-3);
				}
				str=str+"-TE";
				Intent i = new Intent(YearsActivity.this, TableActivity.class).putExtra("data", str);
				startActivity(i);
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});
		imgBtn3 = (ImageButton) findViewById(R.id.imageButton3Yr);
   		imgBtn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(str.contains("-"))
				{
					str=str.substring(0,str.length()-3);
				}
				str=str+"-BE";
				Intent i = new Intent(YearsActivity.this, TableActivity.class).putExtra("data", str);
				startActivity(i);
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});
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
    	View root=findViewById(R.id.contentsYr);
    	View container=findViewById(R.id.containerYr);
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
