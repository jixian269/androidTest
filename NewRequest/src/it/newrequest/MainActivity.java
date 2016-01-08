package it.newrequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import it.request.GetCarSpeedRequest;
import it.base.BaseRequest.RequestListener;

public class MainActivity extends Activity {
	String returnvalue = "initvalue" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void click(View v){
		
		new GetCarSpeedRequest().connection(new RequestListener() {
			
			@Override
			public void resultReturn(Object result) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "return: "+result, 0).show();
				System.out.println("return: "+result);
				returnvalue = (String) result;
				System.out.println("returnvalue: "+returnvalue);
			}
		});
//		Toast.makeText(this, returnvalue, 0).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
