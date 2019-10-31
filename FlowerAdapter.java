package app.mma.jsonxml2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.mma.jsonxml2.Flower;

public class FlowerAdapter extends ArrayAdapter<Flower> {
    private Context context;
    private List<Flower> flowerList;
    public FlowerAdapter(Context context, List<Flower> flowerList) {
        super(context, R.layout.flower_list_item, flowerList);
        this.context = context;
        this.flowerList = flowerList;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if(convertView == null){
            LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.flower_list_item,parent,false);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.fill(position);
        return convertView;
    }


    public class ViewHolder {
        ImageView flower_image;
        TextView flower_name, flower_category, flower_price;
        public ViewHolder(View view){
            flower_image = (ImageView) view.findViewById(R.id.flower_image);
            flower_name = (TextView) view.findViewById(R.id.flower_name);
            flower_price = (TextView) view.findViewById(R.id.flower_price);
            flower_category = (TextView) view.findViewById(R.id.flower_category);
        }

        public void fill(int position){
            Flower flower = flowerList.get(position);
            flower_name.setText(flower.getName());
            flower_price.setText(flower.getPrice() + " $");
            flower_category.setText(flower.getCategory());
            // load image
            String photoName = flower.getPhoto();
            if(photoName.contains(".")){
                photoName = photoName.substring(0, photoName.lastIndexOf('.'));
            }
            int imageResId = context.getResources().getIdentifier(
                    photoName, "drawable", context.getApplicationContext().getPackageName());
            flower_image.setImageResource(imageResId);
        }

    }


}
