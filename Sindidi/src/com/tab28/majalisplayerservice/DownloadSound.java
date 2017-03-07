package com.tab28.majalisplayerservice;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class DownloadSound extends Activity {

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	// private Button startBtn;
	private ProgressDialog mProgressDialog;
	public String url = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		// startBtn = (Button)findViewById(R.id.startBtn);
		// startBtn.setOnClickListener(new OnClickListener(){
		// public void onClick(View v) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			url = extras.getString("urlSong");
		}
		startDownload(url);
		// }
		// });

	}

	public String fileToRead(String fileURL) {
		String path = null;
		try {
			// String folderName = getFolderName(fileURL);
			String fileName = getFileName(fileURL);
			File root = Environment.getExternalStorageDirectory();
			// path = root.getAbsolutePath() + "/xacida/" + folderName + "/"
			// + fileName;
			path = root.getAbsolutePath() + "/xacida/" + fileName;

		} catch (Exception e) {
			Log.d("fileToRead", e.getMessage());
		}
		return path;
	}

	public String getFileName(String url) {
		int taille = url.length();
		int positionDernierSlash = url.lastIndexOf("/");
		String fileName = url.substring(positionDernierSlash + 1, taille);
		return fileName;
	}

	// public String getFolderName(String url) {
	// String folderName = null;
	// int positionDernierSlash = url.lastIndexOf("/");
	// String titreRep = url.substring(0, positionDernierSlash);
	// System.out.println(titreRep);
	// int i = titreRep.lastIndexOf("/");
	// folderName = url.substring(i + 1, titreRep.length());
	// return folderName;
	// }

	public boolean existFile(String fileURL) {
		boolean isExist = false;
		try {
			// String folderName = getFolderName(fileURL);
			File root = Environment.getExternalStorageDirectory();
			File dir = new File(root.getAbsolutePath() + "/xacida/");
			// + folderName);
			if (dir.exists())
				isExist = true;
			else {
				dir.mkdirs();
			}
		} catch (Exception e) {
			Log.d("existFile", e.getMessage());
		}
		return isExist;
	}

	private void startDownload(String url) {
		String path = fileToRead(url);
		Log.d("############ PATH", path);
		new DownloadFileAsync().execute(url, path);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage(this.getResources().getString(
					R.string.downloadfile));
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
			return mProgressDialog;
		default:
			return null;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			openOptionsDialog();
			return true;
		case R.id.app_exit:
			exitOptionsDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void exitOptionsDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.str_exit)
				.setMessage(R.string.app_exit_message)
				.setNegativeButton(R.string.str_no,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})
				.setPositiveButton(R.string.str_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								finish();
								System.exit(0);
							}
						}).show();
	}

	private void openOptionsDialog() {
		AboutDialog about = new AboutDialog(this);
		about.setTitle(Html.fromHtml(this.getString(R.string.app_about)));
		about.show();
	}
	class DownloadFileAsync extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(DIALOG_DOWNLOAD_PROGRESS);
		}

		@Override
		protected String doInBackground(String... aurl) {
			int count;

			try {

				URL url = new URL(aurl[0]);
				URLConnection conexion = url.openConnection();
				conexion.connect();

				int lenghtOfFile = conexion.getContentLength();
				Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

				InputStream input = new BufferedInputStream(url.openStream());

				String path = aurl[1];
				Log.i("############ PATH", path);
				existFile(path);
				OutputStream output = new FileOutputStream(path);

				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}

				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}
			return null;

		}

		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC", progress[0]);
			mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String unused) {
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
		}

	}
}