package com.tab28.majalisplayerservice;

/**
 * @author xadimouSALIH
 * http://www.tab28.com
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public abstract class DashBoardActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    public void setHeader(String title, boolean btnHomeVisible, boolean btnFeedbackVisible)
    {
    		ViewStub stub = (ViewStub) findViewById(R.id.vsHeader);
    		View inflated = stub.inflate();
          
    		TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
    		txtTitle.setText(title);
    		
    		Button btnHome = (Button) inflated.findViewById(R.id.btnHome);
    		if(!btnHomeVisible)
    			btnHome.setVisibility(View.INVISIBLE);
    		
    		Button btnFeedback = (Button) inflated.findViewById(R.id.btnFeedback);
    		if(!btnFeedbackVisible)
    			btnFeedback.setVisibility(View.INVISIBLE);
    		
    }
    
    /**
     * Home button click handler
     * @param v
     */
    public void btnHomeClick(View v)
    {
    	Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
    	intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity(intent);
    	
    }
    
    /**
     * Feedback button click handler
     * @param v
     */
    public void btnFeedbackClick(View v)
    {
    	Intent intent = new Intent(getApplicationContext(), AboutDialog.class);
    	startActivity(intent);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, R.string.app_about);
		menu.add(0, 1, 1, R.string.str_exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case 0:
			openOptionsDialog();
			break;
		case 1:
			exitOptionsDialog();
			break;
		}
		return true;
	}

	private void exitOptionsDialog() {
//		new AlertDialog.Builder(this)
//				.setTitle(R.string.app_exit)
//				.setMessage(R.string.app_exit_message)
//				.setNegativeButton(R.string.str_no,
//						new DialogInterface.OnClickListener() {
//							public void onClick(
//									DialogInterface dialoginterface, int i) {
//							}
//						})
//				.setPositiveButton(R.string.str_ok,
//						new DialogInterface.OnClickListener() {
//							public void onClick(
//									DialogInterface dialoginterface, int i) {
//								finish();
//							}
//						}).show();
	}

	private void openOptionsDialog() {
		Toast.makeText(getApplicationContext(),
				"TAB28: Oeuvrer pour Cheikh Ahmadou Bamba KHADIMOU RASSOUL",
				Toast.LENGTH_LONG).show();
	}
}