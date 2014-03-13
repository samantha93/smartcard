package efrei.asyria.m1a.adapter;

import java.util.List;

import efrei.asyria.m1a.model.Card;
import efrei.asyria.m1a.smartcard.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCardAdapter extends BaseAdapter {

	private Activity mContext;
	private List<Card> mList;
	private LayoutInflater mLayoutInflater = null;

	public ListCardAdapter(Activity context, List<Card> list) {
		mContext = context;
		mList = list;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		CompleteListViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.list_card_item, null);
			viewHolder = new CompleteListViewHolder(v);
			v.setTag(viewHolder);
		} else {
			viewHolder = (CompleteListViewHolder) v.getTag();
		}
		viewHolder.tvName.setText(mList.get(position).getUser().getName()+" "+mList.get(position).getUser().getSurname());
		viewHolder.tvAdress.setText(mList.get(position).getUser().getAdress());
		viewHolder.tvCp.setText(mList.get(position).getUser().getCp()+" "+mList.get(position).getUser().getCity());
		viewHolder.tvPhoneF.setText(mList.get(position).getUser().getPhoneF());
		viewHolder.tvPhoneM.setText(mList.get(position).getUser().getPhoneM());
		viewHolder.tvEmail.setText(mList.get(position).getUser().getEmail());
		return v;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}

class CompleteListViewHolder {
	public ImageView imageView;
	public TextView tvName;
	public TextView tvAdress;
	public TextView tvCp;
	public TextView tvPhoneF;
	public TextView tvPhoneM;
	public TextView tvEmail;

	public CompleteListViewHolder(View base) {
		imageView = (ImageView) base.findViewById(R.id.imgCV);
		tvName = (TextView) base.findViewById(R.id.name);
		tvAdress = (TextView) base.findViewById(R.id.adress);
		tvCp = (TextView) base.findViewById(R.id.cpCity);
		tvPhoneF = (TextView) base.findViewById(R.id.phoneF);
		tvPhoneM = (TextView) base.findViewById(R.id.phoneM);
		tvEmail = (TextView) base.findViewById(R.id.email);

	}
}
