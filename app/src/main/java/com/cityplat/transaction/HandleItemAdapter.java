package com.cityplat.transaction;

import helper.CommonMethod;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cityplat.R;

public class HandleItemAdapter extends BaseAdapter {
	LayoutInflater inflater;
	Context context;
	List<HashMap<String, Object>> data;
	HashMap<Integer, View> lmap = new HashMap<Integer, View>();

	public HandleItemAdapter(Context context, List<HashMap<String, Object>> data) {
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
		String _FlowStatusName = "处理状态:"
				+ data.get(position).get("_FlowStatusName").toString();
		String _PaymentPeople = "派发人:"
				+ data.get(position).get("_PaymentPeople").toString();
		String _UserName = "受理人:"
				+ data.get(position).get("_UserName").toString();
		String _PaymentDate = "派发时间:"
				+ data.get(position).get("_PaymentDate").toString();
		String _AcceptDate = "受理时间:"
				+ data.get(position).get("_AcceptDate").toString();
		String _DepartmentName = "受理部门:"
				+ data.get(position).get("_DepartmentName").toString();
		String _FlowTypeName = "任务类型:"
				+ data.get(position).get("_FlowTypeName").toString();

		if (lmap.get(position) == null) {

			holder = new Holder();
			convertView = inflater.inflate(R.layout.transaction_handle_item,
					null);
			holder._id = (TextView) convertView.findViewById(R.id._id);
			holder._FlowStatusName = (TextView) convertView
					.findViewById(R.id._FlowStatusName);
			holder._PaymentPeople = (TextView) convertView
					.findViewById(R.id._PaymentPeople);
			holder._UserName = (TextView) convertView
					.findViewById(R.id._UserName);
			holder._PaymentDate = (TextView) convertView
					.findViewById(R.id._PaymentDate);
			holder._AcceptDate = (TextView) convertView
					.findViewById(R.id._AcceptDate);
			holder._DepartmentName = (TextView) convertView
					.findViewById(R.id._DepartmentName);
			holder._FlowTypeName = (TextView) convertView
					.findViewById(R.id._FlowTypeName);
			lmap.put(position, convertView);
			convertView.setTag(holder);

		} else {
			convertView = lmap.get(position);
			holder = (Holder) convertView.getTag();
		}

		holder._id.setText(_id);
		holder._FlowStatusName.setText(_FlowStatusName);
		holder._PaymentPeople.setText(_PaymentPeople);
		holder._UserName
				.setText(CommonMethod.ToNullOrEmptyForStirng(_UserName));
		holder._PaymentDate.setText(_PaymentDate);
		holder._AcceptDate.setText(CommonMethod
				.ToNullOrEmptyForStirng(_AcceptDate));
		holder._DepartmentName.setText(_DepartmentName);
		holder._FlowTypeName.setText(_FlowTypeName);

		return convertView;

	}

	class Holder {
		public TextView _id;
		public TextView _FlowStatusName;
		public TextView _PaymentPeople;
		public TextView _UserName;
		public TextView _PaymentDate;
		public TextView _AcceptDate;
		public TextView _DepartmentName;
		public TextView _FlowTypeName;
	}

}
