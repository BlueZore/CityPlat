package com.cityplat.transaction;

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
		String _CityEventID = data.get(position).get("_CityEventID").toString();
		String _CaseCode = data.get(position).get("_CaseCode").toString();
		String _PaymentDate = data.get(position).get("_PaymentDate").toString();
		String _CaseTitle = data.get(position).get("_CaseTitle").toString();
		String _UserName = "≈…∑¢»À£∫"
				+ data.get(position).get("_UserName").toString();
		String _FlowStatus = data.get(position).get("_FlowStatus").toString();
		String _FlowType = data.get(position).get("_FlowType").toString();

		if (lmap.get(position) == null) {

			holder = new Holder();
			convertView = inflater.inflate(R.layout.transaction_item, null);
			holder._id = (TextView) convertView.findViewById(R.id._id);
			holder._CityEventID = (TextView) convertView
					.findViewById(R.id._CityEventID);
			holder._CaseCode = (TextView) convertView
					.findViewById(R.id._CaseCode);
			holder._PaymentDate2 = (TextView) convertView
					.findViewById(R.id._PaymentDate2);
			holder._CaseTitle = (TextView) convertView
					.findViewById(R.id._CaseTitle);
			holder._UserName = (TextView) convertView
					.findViewById(R.id._UserName);
			holder._FlowStatus = (TextView) convertView
					.findViewById(R.id._FlowStatus);
			holder._FlowType = (TextView) convertView
					.findViewById(R.id._FlowType);
			lmap.put(position, convertView);
			convertView.setTag(holder);

		} else {
			convertView = lmap.get(position);
			holder = (Holder) convertView.getTag();
		}

		holder._id.setText(_id);
		holder._CityEventID.setText(_CityEventID);
		holder._CaseCode.setText(_CaseCode);
		holder._PaymentDate2.setText(_PaymentDate);
		holder._CaseTitle.setText(_CaseTitle);
		holder._UserName.setText(_UserName);
		holder._FlowStatus.setText(_FlowStatus);
		holder._FlowType.setText(_FlowType);

		return convertView;

	}

	class Holder {
		public TextView _id;
		public TextView _CityEventID;
		public TextView _CaseCode;
		public TextView _PaymentDate2;
		public TextView _CaseTitle;
		public TextView _UserName;
		public TextView _FlowStatus;
		public TextView _FlowType;
	}

}
