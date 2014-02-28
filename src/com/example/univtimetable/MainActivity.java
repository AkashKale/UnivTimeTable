package com.example.univtimetable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity{
	
	ImageButton imgBtn1;
	ImageButton imgBtn2;
	ImageButton imgBtn3;
	ImageButton imgBtn4;
	ImageButton imgBtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }


    public void addListenerOnButton() {
    	imgBtn1 = (ImageButton) findViewById(R.id.imageButton1Br);
		imgBtn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), YearsActivity.class).putExtra("Branch", "COMP");
				startActivity(i); 
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});
		imgBtn2 = (ImageButton) findViewById(R.id.imageButton2Br);
   		imgBtn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), YearsActivity.class).putExtra("Branch", "IT");
				startActivity(i); 
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});
		imgBtn3 = (ImageButton) findViewById(R.id.imageButton3Br);
   		imgBtn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), YearsActivity.class).putExtra("Branch", "MECH");
				startActivity(i); 
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});

		imgBtn4 = (ImageButton) findViewById(R.id.imageButton4Br);
   		imgBtn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), YearsActivity.class).putExtra("Branch", "E & TC");
				startActivity(i); 
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});

		imgBtn5 = (ImageButton) findViewById(R.id.imageButton5Br);
   		imgBtn5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), YearsActivity.class).putExtra("Branch", "FE");
				startActivity(i); 
				overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
			}
		});

	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // your code here
    	View root=findViewById(R.id.contents);
    	View container=findViewById(R.id.container);
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
