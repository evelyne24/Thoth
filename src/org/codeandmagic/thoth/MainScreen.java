package org.codeandmagic.thoth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Button next = (Button) findViewById(R.id.btn);
		next.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainScreen.this, ThothScreen.class));
			}
		});
    }
}