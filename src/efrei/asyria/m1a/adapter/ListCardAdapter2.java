package efrei.asyria.m1a.adapter;

import efrei.asyria.m1a.smartcard.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCardAdapter2 extends ArrayAdapter<String>{

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.list_card_item, parent, false);
		
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgCV);
        TextView tvName = (TextView) rowView.findViewById(R.id.name);
        TextView tvAdress = (TextView) rowView.findViewById(R.id.adress);
        TextView tvCp = (TextView) rowView.findViewById(R.id.cpCity);
        TextView tvPhoneF = (TextView) rowView.findViewById(R.id.phoneF);
        TextView tvPhoneM = (TextView) rowView.findViewById(R.id.phoneM);
        TextView tvEmail = (TextView) rowView.findViewById(R.id.email);
 
        tvName.setText(getItem(position));
 
        /*if(convertView == null )
          imageView.setImageResource(tab_images_pour_la_liste[position]);
        else
          rowView = (View)convertView;
 */
        return rowView;
	}
	
	public ListCardAdapter2(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	
}
