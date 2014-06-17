package efrei.asyria.m1a.adapter;

import java.net.URI;
import java.util.List;

import efrei.asyria.m1a.model.Card;
import efrei.asyria.m1a.smartcard.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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
			LayoutInflater li = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.list_card_item, null);
			viewHolder = new CompleteListViewHolder(v);
			v.setTag(viewHolder);
		} else {
			viewHolder = (CompleteListViewHolder) v.getTag();
		}
		viewHolder.tvName.setText(mList.get(position).getUser().getName() + " "
				+ mList.get(position).getUser().getSurname());
		viewHolder.tvAdress.setText(mList.get(position).getUser().getAdress());
		viewHolder.tvCp.setText(mList.get(position).getUser().getCp() + " "
				+ mList.get(position).getUser().getCity());
		viewHolder.tvPhoneF.setText(mList.get(position).getUser().getPhoneF());
		viewHolder.tvPhoneM.setText(mList.get(position).getUser().getPhoneM());
		viewHolder.tvEmail.setText(mList.get(position).getUser().getEmail());
		viewHolder.tvJ.setText(mList.get(position).getUser().getJob());
		viewHolder.tvCn.setText(mList.get(position).getUser().getCname());
		int i = 1;
		try {
			System.out.println("uriimgnumber="+mList.get(position).getUrlImg());
			i = Integer.parseInt(mList.get(position).getUrlImg());
			//viewHolder.imageView.setImageURI(Uri.parse("@http://smart-card.fr/images/carte-visite-"+i+".png"));
			switch (i) {
				case 1:
					viewHolder.imageView.setImageResource(R.drawable.cartevisite1);
					break;
				case 2:
					viewHolder.imageView.setImageResource(R.drawable.cartevisite2);
					break;
				case 3:
					viewHolder.imageView.setImageResource(R.drawable.cartevisite3);
					break;
				case 4:
					viewHolder.imageView.setImageResource(R.drawable.cartevisite4);
					break;
				case 5:
					viewHolder.imageView.setImageResource(R.drawable.cartevisite2);
					break;
			}
			
		} catch (NumberFormatException nfe) {
			System.out.println("Could not parse " + nfe);
		}
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
	public TextView tvJ;
	public TextView tvCn;

	public CompleteListViewHolder(View base) {
		imageView = (ImageView) base.findViewById(R.id.imgCV);
		tvName = (TextView) base.findViewById(R.id.name);
		tvAdress = (TextView) base.findViewById(R.id.adress);
		tvCp = (TextView) base.findViewById(R.id.cpCity);
		tvPhoneF = (TextView) base.findViewById(R.id.phoneF);
		tvPhoneM = (TextView) base.findViewById(R.id.phoneM);
		tvEmail = (TextView) base.findViewById(R.id.email);
		tvJ = (TextView) base.findViewById(R.id.tvJob);
		tvCn = (TextView) base.findViewById(R.id.cname);

	}
}
