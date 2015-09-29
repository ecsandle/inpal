package com.example.elisandler.inspirationpal;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by elisandler on 7/30/15.
 */
public class WeightAdapter extends BaseAdapter {

    private LayoutInflater inflater=null;

    SetsInfo setsInfos = new SetsInfo();
    int numOfSets = 0;

    public WeightAdapter(SetsInfo setsInfos, Context context)//this didnt take in context before
    {
        super();
        this.setsInfos=setsInfos;
        numOfSets = setsInfos.getReps().size();
        //this.exersiseNames = exersiseNames;
        //  inflater =(LayoutInflater)baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(context);

    }



    public int getCount()
    {

        return numOfSets;
    }



    public Object getItem(int position)
    {
        return position;
    }



    public long getItemId(int position)
    {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {

        convertView = inflater.inflate(R.layout.weight_row_item, null);
        TextView number = (TextView) convertView.findViewById(R.id.WeightNumber1);
        number.setText((position+1)+". ");
        TextView weight = (TextView)convertView.findViewById(R.id.WeightWeightNumber1);
        weight.setText(setsInfos.getWeight().get(position).toString());
        TextView reps = (TextView)convertView.findViewById(R.id.WeightRepsNumber1);
        reps.setText(setsInfos.getReps().get(position).toString());
        convertView.setBackgroundResource(R.color.material_deep_teal_200);


        // ImageView compleatImageView=(ImageView)vi.findViewById(R.id.complet_image);
        // TextView name = (TextView)vi.findViewById(R.id.game_name); // name
        //TextView email_id = (TextView)vi.findViewById(R.id.e_mail_id); // email ID
        //TextView notification_message = (TextView)vi.findViewById(R.id.notification_message); // notification message



        //compleatImageView.setBackgroundResource(R.id.address_book);
        //name.setText(data.getIndex(position));
        //email_id.setText(data.getIndex(position));
        //notification_message.setTextdata.getIndex(position));

        return convertView;
    }



}
