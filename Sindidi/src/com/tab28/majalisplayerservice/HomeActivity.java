package com.tab28.majalisplayerservice;

/**
 * @author xadimouSALIH
 * http://www.tab28.com
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends DashBoardActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Creating alert Dialog with one Button
		setContentView(R.layout.main);
		setHeader(getString(R.string.app_name), false, true);
		Button btnHis = (Button) findViewById(R.id.main_btn_histoire);
		btnHis.setText(Html.fromHtml("Xacida"));
		Button btnMsbi = (Button) findViewById(R.id.main_btn_mbindumsbi);
		btnMsbi.setText(Html.fromHtml("Xamxam"));
		Button btnTny = (Button) findViewById(R.id.main_btn_tny);
		btnTny.setText(Html.fromHtml("Wolofal"));
		Button btnSar = (Button) findViewById(R.id.main_btn_sar);
		btnSar.setText(Html.fromHtml("Autre"));
		AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();
		alertDialog1.setTitle(this.getString(R.string.bienvenu));
		alertDialog1.setMessage(Html.fromHtml("<center>"
				+ this.getString(R.string.obj1) + "<br/>"
				+ this.getString(R.string.obj2) + "</center>"));
		alertDialog1.setIcon(R.drawable.serignsaliou);
		alertDialog1.setButton(this.getString(R.string.str_yes),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						Toast.makeText(
								getApplicationContext(),
								HomeActivity.this.
										getString(R.string.dieredieuf),
								Toast.LENGTH_SHORT).show();
					}
				});
		alertDialog1.show();

	}

	/**
	 * Button click handler on Main activity
	 * 
	 * @param v
	 */
	public void onButtonClicker(View v) {
		Intent intent;

		switch (v.getId()) {
		case R.id.main_btn_histoire:
			intent = new Intent(this, SindidiActivity.class);
			startActivity(intent);
			break;

		case R.id.main_btn_mbindumsbi:
			intent = new Intent(this, SindidiActivity.class);
			startActivity(intent);
			break;

		case R.id.main_btn_tny:
			intent = new Intent(this, SindidiActivity.class);
			startActivity(intent);
			break;

		case R.id.main_btn_sar:
			intent = new Intent(this, SindidiActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
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
