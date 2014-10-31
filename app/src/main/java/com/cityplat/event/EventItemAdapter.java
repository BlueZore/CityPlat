package com.cityplat.event;

import java.util.HashMap;
import java.util.List;
import com.cityplat.R;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventItemAdapter extends BaseAdapter {
	LayoutInflater inflater;
	Context context;
	List<HashMap<String, Object>> data;
	HashMap<Integer, View> lmap = new HashMap<Integer, View>();

	public EventItemAdapter(Context context, List<HashMap<String, Object>> data) {
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		String _id = data.get(position).get("_id").toString();
		String _CaseCode = data.get(position).get("_CaseCode").toString();
		String _ReportDate = data.get(position).get("_ReportDate").toString();
		String _CaseTitle = data.get(position).get("_CaseTitle").toString();
		String _EventAddress = data.get(position).get("_EventAddress")
				.toString();
		String _CreateType = "À´×Ô£º"
				+ data.get(position).get("_CreateType").toString();

		if (lmap.get(position) == null) {

			holder = new Holder();
			convertView = inflater.inflate(R.layout.event_item, null);
			holder._id = (TextView) convertView.findViewById(R.id._id);
			holder._CaseCode = (TextView) convertView
					.findViewById(R.id._CaseCode);
			holder._ReportDate = (TextView) convertView
					.findViewById(R.id._ReportDate);
			holder._CaseTitle = (TextView) convertView
					.findViewById(R.id._CaseTitle);
			holder._EventAddress = (TextView) convertView
					.findViewById(R.id._EventAddress);
			holder._CreateType = (TextView) convertView
					.findViewById(R.id._CreateType);
			lmap.put(position, convertView);
			convertView.setTag(holder);

		} else {
			convertView = lmap.get(position);
			holder = (Holder) convertView.getTag();
		}

		holder._id.setText(_id);
		holder._CaseCode.setText(_CaseCode);
		holder._ReportDate.setText(_ReportDate);
		holder._CaseTitle.setText(_CaseTitle);
		holder._EventAddress.setText(_EventAddress);
		holder._CreateType.setText(_CreateType);

		return convertView;

	}

	class Holder {
		public TextView _id;
		public TextView _CaseCode;
		public TextView _ReportDate;
		public TextView _CaseTitle;
		public TextView _EventAddress;
		public TextView _CreateType;
	}

}
