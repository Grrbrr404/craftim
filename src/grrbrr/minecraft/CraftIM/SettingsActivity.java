package grrbrr.minecraft.CraftIM;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView tv = new TextView(this);
		tv.setText("Settings tab");
		setContentView(tv);
	}
}
