package com.tab28.majalisplayerservice;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

	Context context;

	public CustomListViewAdapter(Context context, int resourceId,
			List<RowItem> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/* private view holder class */
	private class ViewHolder {
		// ImageView imageView;
		TextView txtFran;
		TextView txtArab;
		TextView txtTran;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		RowItem rowItem = getItem(position);
//		SharedPreferences sharedPrefs = PreferenceManager
//				.getDefaultSharedPreferences(context);
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.txtArab = (TextView) convertView.findViewById(R.id.arab);
			holder.txtFran = (TextView) convertView.findViewById(R.id.fran);
			holder.txtTran = (TextView) convertView.findViewById(R.id.tran);
			// holder.imageView = (ImageView)
			// convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
//		boolean isFr = sharedPrefs.getBoolean("prefLangFr", true);
//		boolean isTr = sharedPrefs.getBoolean("prefLangTr", true);
//		if (isFr == true && isTr == true) {
			holder.txtArab.setText(rowItem.getArab());
			holder.txtFran.setText(rowItem.getFran());
			holder.txtTran.setText(rowItem.getTran());
//		} else if (isFr == false && isTr == true) {
//			holder.txtArab.setText(rowItem.getArab());
//			holder.txtTran.setText(rowItem.getTran());
//		} else if (isFr == true && isTr == false) {
//			holder.txtArab.setText(rowItem.getArab());
//			holder.txtTran.setText(rowItem.getFran());
//		}else if (isFr == false && isTr == false) {
//			holder.txtArab.setText(rowItem.getArab());
//		}
		// /holder.imageView.setImageResource(rowItem.getImageId());

		return convertView;
	}
}