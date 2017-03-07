package com.tab28.majalisplayerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SindidiActivity extends Activity implements OnClickListener,
		OnItemClickListener, AnimationListener {

	public static ImageView btnPlay, btnForward, btnBackward, btnNext,
			btnPrevious, listSongBtn, downloadfileBtn, lecteurBtn, backBtn;
	public static ImageButton btnShuffle, btnRepeat;
	public static SeekBar songProgressBar;
	private static final int RESULT_SETTINGS = 1;
	public static ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	private ListAdapter adapter;
	private ListView listSongLv;
	private LinearLayout playerScreen, listSongScreen;
	// private RelativeLayout lecteurScreen;
	public static TextView songTitle, songCurrentDurationLabel,
			songTotalDurationLabel;
	public Intent playerService;
	ListView listView;
	List<RowItem> rowItems;
	String url = null;
	String qacidaName = "Sindidi";
	String[] arab = null;
	String[] fran = null;
	String[] tran = null;

	// String url =
	// "http://www.majalis.org/qasidas/audio/Sindidi_HT%28SMstDiop%29.mp3";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
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
								SindidiActivity.this
										.getString(R.string.dieredieuf),
								Toast.LENGTH_SHORT).show();
					}
				});
		alertDialog1.show();

		initViews();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			qacidaName = extras.getString("QacidaName");
		}
		if (qacidaName.equals("Sindidi")) {
			url = "http://www.tab8.com/media/Sindidi.mp3";
			arab = getResources().getStringArray(R.array.Sindidi_Ar);
			fran = getResources().getStringArray(R.array.Sindidi_Fr);
			tran = getResources().getStringArray(R.array.Sindidi_Tr);

		} else if (qacidaName.equals("Jaawartou")) {
			url = "http://www.tab8.com/media/Jaawartou.mp3";
			arab = getResources().getStringArray(R.array.Jaawartou_Ar);
			fran = getResources().getStringArray(R.array.Jaawartou_Fr);
			tran = getResources().getStringArray(R.array.Jaawartou_Tr);

		} else if (qacidaName.equals("Lamyabdou")) {
			url = "http://www.tab8.com/media/Lamyabdou.mp3";
			arab = getResources().getStringArray(R.array.Lamyabdou_Ar);
			fran = getResources().getStringArray(R.array.Lamyabdou_Fr);
			tran = getResources().getStringArray(R.array.Lamyabdou_Tr);

		} else if (qacidaName.equals("Kalaamoun")) {
			url = "http://www.tab8.com/media/Kalaamoun.mp3";
			arab = getResources().getStringArray(R.array.Kalaamoun_Ar);
			fran = getResources().getStringArray(R.array.Kalaamoun_Fr);
			tran = getResources().getStringArray(R.array.Kalaamoun_Tr);

		} else if (qacidaName.equals("Kalaamoun")) {
			url = "http://www.tab8.com/media/Antarabi.mp3";
			arab = getResources().getStringArray(R.array.Antarabi_Ar);
			fran = getResources().getStringArray(R.array.Antarabi_Fr);
			tran = getResources().getStringArray(R.array.Antarabi_Tr);

		} else if (qacidaName.equals("Yassourou")) {
			url = "http://www.tab8.com/media/Yassourou.mp3";
			arab = getResources().getStringArray(R.array.Yassourou_Ar);
			fran = getResources().getStringArray(R.array.Yassourou_Fr);
			tran = getResources().getStringArray(R.array.Yassourou_Tr);

		} else if (qacidaName.equals("Wakaana")) {
			url = "http://www.tab8.com/media/Wakaana.mp3";
			arab = getResources().getStringArray(R.array.Wakaana_Ar);
			fran = getResources().getStringArray(R.array.Wakaana_Fr);
			tran = getResources().getStringArray(R.array.Wakaana_Tr);

		} else if (qacidaName.equals("Faqadjaakum")) {
			url = "http://www.tab8.com/media/Faqadjaakum.mp3";
			arab = getResources().getStringArray(R.array.Faqadjaakum_Ar);
			fran = getResources().getStringArray(R.array.Faqadjaakum_Fr);
			tran = getResources().getStringArray(R.array.Faqadjaakum_Tr);

		} else if (qacidaName.equals("Karramna")) {
			url = "http://www.tab8.com/media/Karramna.mp3";
			arab = getResources().getStringArray(R.array.Karramna_Ar);
			fran = getResources().getStringArray(R.array.Karramna_Fr);
			tran = getResources().getStringArray(R.array.Karramna_Tr);

		} else if (qacidaName.equals("Minalhaqqi")) {
			url = "http://www.tab8.com/media/Minalhaqqi.mp3";
			arab = getResources().getStringArray(R.array.Minalhaqqi_Ar);
			fran = getResources().getStringArray(R.array.Minalhaqqi_Fr);
			tran = getResources().getStringArray(R.array.Minalhaqqi_Tr);

		} else if (qacidaName.equals("Alaaman")) {
			url = "http://www.tab8.com/media/Alaaman.mp3";
			arab = getResources().getStringArray(R.array.Alaaman_Ar);
			fran = getResources().getStringArray(R.array.Alaaman_Fr);
			tran = getResources().getStringArray(R.array.Alaaman_Tr);

		} else if (qacidaName.equals("Chakawtou")) {
			url = "http://www.tab8.com/media/Chakawtou.mp3";
			arab = getResources().getStringArray(R.array.Chakawtou_Ar);
			fran = getResources().getStringArray(R.array.Chakawtou_Fr);
			tran = getResources().getStringArray(R.array.Chakawtou_Tr);

		} else if (qacidaName.equals("Sabhountaqi")) {
			url = "http://www.tab8.com/media/Sabhountaqi.mp3";
			arab = getResources().getStringArray(R.array.Sabhountaqi_Ar);
			fran = getResources().getStringArray(R.array.Sabhountaqi_Fr);
			tran = getResources().getStringArray(R.array.Sabhountaqi_Tr);

		} else if (qacidaName.equals("Nafahani")) {
			url = "http://www.tab8.com/media/Nafahani.mp3";
			arab = getResources().getStringArray(R.array.Nafahani_Ar);
			fran = getResources().getStringArray(R.array.Nafahani_Fr);
			tran = getResources().getStringArray(R.array.Nafahani_Tr);

		} else if (qacidaName.equals("Allahoumin")) {
			url = "http://www.tab8.com/media/Allahoumin.mp3";
			arab = getResources().getStringArray(R.array.Allahoumin_Ar);
			fran = getResources().getStringArray(R.array.Allahoumin_Fr);
			tran = getResources().getStringArray(R.array.Allahoumin_Tr);

		} else if (qacidaName.equals("Walbaladou")) {
			url = "http://www.tab8.com/media/Walbaladou.mp3";
			arab = getResources().getStringArray(R.array.Walbaladou_Ar);
			fran = getResources().getStringArray(R.array.Walbaladou_Fr);
			tran = getResources().getStringArray(R.array.Walbaladou_Tr);

		} else if (qacidaName.equals("Allahoumouhamadou")) {
			url = "http://www.tab8.com/media/Allahoumouhamadou.mp3";
			arab = getResources().getStringArray(R.array.Allahoumouhamadou_Ar);
			fran = getResources().getStringArray(R.array.Allahoumouhamadou_Fr);
			tran = getResources().getStringArray(R.array.Allahoumouhamadou_Tr);

		} else if (qacidaName.equals("Albaaqi")) {
			url = "http://www.tab8.com/media/Albaaqi.mp3";
			arab = getResources().getStringArray(R.array.Albaaqi_Ar);
			fran = getResources().getStringArray(R.array.Albaaqi_Fr);
			tran = getResources().getStringArray(R.array.Albaaqi_Tr);

		} else if (qacidaName.equals("Rabbi")) {
			url = "http://www.tab8.com/media/Rabbi.mp3";
			arab = getResources().getStringArray(R.array.Rabbi_Ar);
			fran = getResources().getStringArray(R.array.Rabbi_Fr);
			tran = getResources().getStringArray(R.array.Rabbi_Tr);

		} else if (qacidaName.equals("Yamanbiamdahihi")) {
			url = "http://www.tab8.com/media/Yamanbiamdahihi.mp3";
			arab = getResources().getStringArray(R.array.Yamanbiamdahihi_Ar);
			fran = getResources().getStringArray(R.array.Yamanbiamdahihi_Fr);
			tran = getResources().getStringArray(R.array.Yamanbiamdahihi_Tr);

		} else if (qacidaName.equals("WjahtuWajhi")) {
			url = "http://www.tab8.com/media/WjahtuWajhi.mp3";
			arab = getResources().getStringArray(R.array.WjahtuWajhi_Ar);
			fran = getResources().getStringArray(R.array.WjahtuWajhi_Fr);
			tran = getResources().getStringArray(R.array.WjahtuWajhi_Tr);

		} else if (qacidaName.equals("AhmadounalMoukhtar")) {
			url = "http://www.tab8.com/media/AhmadounalMoukhtar.mp3";
			arab = getResources().getStringArray(R.array.AhmadounalMoukhtar_Ar);
			fran = getResources().getStringArray(R.array.AhmadounalMoukhtar_Fr);
			tran = getResources().getStringArray(R.array.AhmadounalMoukhtar_Tr);

		} else if (qacidaName.equals("AshaaboulJannah")) {
			url = "http://www.tab8.com/media/AshaaboulJannah.mp3";
			arab = getResources().getStringArray(R.array.AshaaboulJannah_Ar);
			fran = getResources().getStringArray(R.array.AshaaboulJannah_Fr);
			tran = getResources().getStringArray(R.array.AshaaboulJannah_Tr);

		} else if (qacidaName.equals("Waqaani")) {
			url = "http://www.tab8.com/media/Waqaani.mp3";
			arab = getResources().getStringArray(R.array.Waqaani_Ar);
			fran = getResources().getStringArray(R.array.Waqaani_Fr);
			tran = getResources().getStringArray(R.array.Waqaani_Tr);

		} else if (qacidaName.equals("Ayassa")) {
			url = "http://www.tab8.com/media/Ayassa.mp3";
			arab = getResources().getStringArray(R.array.Ayassa_Ar);
			fran = getResources().getStringArray(R.array.Ayassa_Fr);
			tran = getResources().getStringArray(R.array.Ayassa_Tr);

		}

		ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();

		SongsProvider plm = new SongsProvider();
		try {
			songsList = plm.getPlayList();
			for (int i = 0; i < songsList.size(); i++) {
				HashMap<String, String> song = songsList.get(i);
				songsListData.add(song);
			}
			adapter = new SimpleAdapter(this, songsListData,
					R.layout.listsong_item, new String[] { "songTitle" },
					new int[] { R.id.songTitle });
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		rowItems = new ArrayList<RowItem>();
		RowItem item = null;
		for (int i = 0; i < arab.length; i++) {
			item = new RowItem(arab[i], fran[i], tran[i]);
			rowItems.add(item);
		}

		listView = (ListView) findViewById(R.id.list);
		CustomListViewAdapter adapter = new CustomListViewAdapter(this,
				R.layout.list_item, rowItems);
		listView.setAdapter(adapter);
		listSongLv = (ListView) findViewById(R.id.listsong_listview);
		playerService = new Intent(this, PlayerService.class);
		playerService.putExtra("songIndex", PlayerService.currentSongIndex);
		startService(playerService);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (!PlayerService.mp.isPlaying()) {
			stopService(playerService);
			cancelNotification();
		}
	}

	public void cancelNotification() {
		String notificationServiceStr = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(notificationServiceStr);
		mNotificationManager.cancel(PlayerService.NOTIFICATION_ID);
	}

	/**
	 * Initiaze Views
	 */
	private void initViews() {
		playerScreen = (LinearLayout) findViewById(R.id.playerScreen);
		listSongScreen = (LinearLayout) findViewById(R.id.list_song_layout);
		listSongScreen.setVisibility(View.INVISIBLE);
		// lecteurScreen = (RelativeLayout) findViewById(R.id.footer);
		// lecteurScreen.setVisibility(View.INVISIBLE);
		// lecteurScreen.setVisibility(View.GONE);
		btnPlay = (ImageView) findViewById(R.id.btn_play_imageview);
		btnForward = (ImageView) findViewById(R.id.btn_forward_imageview);
		btnBackward = (ImageView) findViewById(R.id.btn_backward_imagview);
		btnNext = (ImageView) findViewById(R.id.btn_next_imageview);
		btnPrevious = (ImageView) findViewById(R.id.btn_previous_imageview);
		listSongBtn = (ImageView) findViewById(R.id.listsong_btn);
		downloadfileBtn = (ImageView) findViewById(R.id.downloadsong_btn);
		btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
		btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
		songProgressBar = (SeekBar) findViewById(R.id.song_playing_progressbar);
		songTitle = (TextView) findViewById(R.id.song_title_txt);
		songCurrentDurationLabel = (TextView) findViewById(R.id.current_time_txt);
		songTotalDurationLabel = (TextView) findViewById(R.id.total_time_txt);
		backBtn = (ImageView) findViewById(R.id.back_btn);
		// lecteurBtn = (ImageView) findViewById(R.id.lecteur_btn);
		btnPlay.setOnClickListener(this);
		btnForward.setOnClickListener(this);
		btnBackward.setOnClickListener(this);
		btnNext.setOnClickListener(this);
		btnPrevious.setOnClickListener(this);
		btnShuffle.setOnClickListener(this);
		btnRepeat.setOnClickListener(this);
		listSongBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
		// lecteurBtn.setOnClickListener(this);
		downloadfileBtn.setOnClickListener(this);

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
								onDestroy();
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

	/**
	 * View OnclickListener Implement
	 * 
	 * @param v
	 */
	public void onClick(View v) {
		Intent i = null;
		switch (v.getId()) {
		case R.id.listsong_btn:
			showListSongScreen();
			listSongLv.setAdapter(adapter);
			listSongLv.setOnItemClickListener(this);

			break;
		case R.id.downloadsong_btn:
			i = new Intent(this, DownloadSound.class);
			i.putExtra("urlSong", url);
			startActivity(i);
			break;
		case R.id.back_btn:
			showListSongScreen();
			break;
		// case R.id.lecteur_btn:
		// showLecteurScreen();
		// break;

		}
	}

	/**
	 * onItemClick Listener Implement.
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.listsong_listview:
			playerService = new Intent(this, PlayerService.class);
			playerService.putExtra("songIndex", position);
			startService(playerService);
			showListSongScreen();
			break;
		}

	}

	/**
	 * Show Listsong Screen with Sliding Animation
	 */
	// private boolean isVisibleLecteur = false;
	//
	// private void showLecteurScreen() {
	// Animation anim;
	// if (isVisibleLecteur == false) {
	// lecteurScreen.setVisibility(View.VISIBLE);
	// anim = AnimationUtils.loadAnimation(this, R.anim.push_down_in);
	// isVisibleLecteur = true;
	// } else {
	// anim = AnimationUtils.loadAnimation(this, R.anim.push_down_out);
	// isVisibleLecteur = false;
	// playerScreen.setVisibility(View.VISIBLE);
	// playerScreen.setVisibility(View.GONE);
	//
	// }
	// anim.setAnimationListener(this);
	// lecteurScreen.startAnimation(anim);
	//
	// }

	/**
	 * Show Listsong Screen with Sliding Animation
	 */
	private boolean isVisible = false;

	private void showListSongScreen() {
		Animation anim;
		if (isVisible == false) {
			listSongScreen.setVisibility(View.VISIBLE);
			anim = AnimationUtils.loadAnimation(this, R.anim.push_down_in);
			isVisible = true;
		} else {
			anim = AnimationUtils.loadAnimation(this, R.anim.push_down_out);
			isVisible = false;
			playerScreen.setVisibility(View.VISIBLE);

		}
		anim.setAnimationListener(this);
		listSongScreen.startAnimation(anim);

	}

	public void onAnimationStart(Animation animation) {

	}

	public void onAnimationEnd(Animation animation) {
		if (isVisible == true)
			playerScreen.setVisibility(View.INVISIBLE);
		else
			listSongScreen.setVisibility(View.INVISIBLE);
	}

	public void onAnimationRepeat(Animation animation) {

	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// switch (item.getItemId()) {
	//
	// case R.id.menu_settings:
	// Intent i = new Intent(this, UserSettingActivity.class);
	// startActivityForResult(i, RESULT_SETTINGS);
	// break;
	//
	// }
	//
	// return true;
	// }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SETTINGS:
			// showUserSettings();
			break;

		}

	}

}
