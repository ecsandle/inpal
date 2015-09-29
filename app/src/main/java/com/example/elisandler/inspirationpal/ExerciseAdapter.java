package com.example.elisandler.inspirationpal;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by elisandler on 7/16/15.
 */
public class ExerciseAdapter extends BaseAdapter {

   // private ArrayList<HashMap<String, String>> data;
    ArrayList<SetsInfo> setsInfos = new ArrayList<>();

    ArrayList<String> exersiseNames = new ArrayList<>();

    private LayoutInflater inflater=null;

    View frameForEverything;
    TextView exerciseName;
    TextView number1;
    TextView rep1;
    TextView weight1;
    int numberOfsets = 0;
    View l1;
    View l2;
    View l3;
    View l4;
    View l5;
    View lAdditional;

    public ExerciseAdapter(ArrayList setsInfos,ArrayList<String> exersiseNames, Context context)//this didnt take in context before
    {
        super();
        this.setsInfos=setsInfos;
        this.exersiseNames = exersiseNames;
      //  inflater =(LayoutInflater)baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(context);

    }



    public int getCount()
    {

        return setsInfos.size();
    }



    public Object getItem(int position)
    {
        return position;
    }



    public long getItemId(int position)
    {
        return position;
    }



    public View getView(int position, View convertView, ViewGroup parent)
    {
        //View v=convertView;
        //if(convertView==null)
        convertView = inflater.inflate(R.layout.listview_item_row, null);
        numberOfsets = setsInfos.get(position).getReps().size();

        l1 = convertView.findViewById(R.id.linerLayout1);
        l2 = convertView.findViewById(R.id.linerLayout2);
        l3 = convertView.findViewById(R.id.linerLayout3);
        l4 = convertView.findViewById(R.id.linerLayout4);
        l5 = convertView.findViewById(R.id.linerLayout5);
        lAdditional = convertView.findViewById(R.id.linerLayoutAdditional);

        if(numberOfsets == 1) {
            frameForEverything = convertView.findViewById(R.id.frameForEverything);
            exerciseName = (TextView) frameForEverything.findViewById(R.id.exerciseName);
            exerciseName.setText(exersiseNames.get(position));
            number1 = (TextView) frameForEverything.findViewById(R.id.number1);
            rep1 = (TextView) frameForEverything.findViewById(R.id.repsNumber1);
            weight1 = (TextView) frameForEverything.findViewById(R.id.weightNumber1);
            number1.setText("1");
            rep1.setText(setsInfos.get(position).getReps().get(0).toString());
            weight1.setText(setsInfos.get(position).getWeight().get(0).toString());
            l2.setVisibility(View.GONE);
            l3.setVisibility(View.GONE);
            l4.setVisibility(View.GONE);
            l5.setVisibility(View.GONE);
            lAdditional.setVisibility(View.GONE);
        }
        if(numberOfsets == 2){
            frameForEverything = convertView.findViewById(R.id.frameForEverything);
            exerciseName = (TextView) frameForEverything.findViewById(R.id.exerciseName);
            exerciseName.setText(exersiseNames.get(position));
            number1 = (TextView) frameForEverything.findViewById(R.id.number1);
            rep1 = (TextView) frameForEverything.findViewById(R.id.repsNumber1);
            weight1 = (TextView) frameForEverything.findViewById(R.id.weightNumber1);
            number1.setText("1");
            rep1.setText(setsInfos.get(position).getReps().get(0).toString());
            weight1.setText(setsInfos.get(position).getWeight().get(0).toString());

            TextView number2 = (TextView) frameForEverything.findViewById(R.id.number2);
            TextView rep2 = (TextView) frameForEverything.findViewById(R.id.repsNumber2);
            TextView weight2 = (TextView) frameForEverything.findViewById(R.id.weightNumber2);
            number2.setText("2");
            rep2.setText(setsInfos.get(position).getReps().get(1).toString());
            weight2.setText(setsInfos.get(position).getWeight().get(1).toString());

            //l2.setVisibility(View.GONE);
            l3.setVisibility(View.GONE);
            l4.setVisibility(View.GONE);
            l5.setVisibility(View.GONE);
            lAdditional.setVisibility(View.GONE);

        }
        if(numberOfsets == 3){
            frameForEverything = convertView.findViewById(R.id.frameForEverything);
            exerciseName = (TextView) frameForEverything.findViewById(R.id.exerciseName);
            exerciseName.setText(exersiseNames.get(position));
            number1 = (TextView) frameForEverything.findViewById(R.id.number1);
            rep1 = (TextView) frameForEverything.findViewById(R.id.repsNumber1);
            weight1 = (TextView) frameForEverything.findViewById(R.id.weightNumber1);
            number1.setText("1");
            rep1.setText(setsInfos.get(position).getReps().get(0).toString());
            weight1.setText(setsInfos.get(position).getWeight().get(0).toString());

            TextView number2 = (TextView) frameForEverything.findViewById(R.id.number2);
            TextView rep2 = (TextView) frameForEverything.findViewById(R.id.repsNumber2);
            TextView weight2 = (TextView) frameForEverything.findViewById(R.id.weightNumber2);
            number2.setText("2");
            rep2.setText(setsInfos.get(position).getReps().get(1).toString());
            weight2.setText(setsInfos.get(position).getWeight().get(1).toString());

            TextView number3 = (TextView) frameForEverything.findViewById(R.id.number3);
            TextView rep3 = (TextView) frameForEverything.findViewById(R.id.repsNumber3);
            TextView weight3 = (TextView) frameForEverything.findViewById(R.id.weightNumber3);
            number3.setText("3");
            rep3.setText(setsInfos.get(position).getReps().get(2).toString());
            weight3.setText(setsInfos.get(position).getWeight().get(2).toString());

            //l2.setVisibility(View.GONE);
            //l3.setVisibility(View.GONE);
            l4.setVisibility(View.GONE);
            l5.setVisibility(View.GONE);
            lAdditional.setVisibility(View.GONE);

        }

        if(numberOfsets == 4){
            frameForEverything = convertView.findViewById(R.id.frameForEverything);
            exerciseName = (TextView) frameForEverything.findViewById(R.id.exerciseName);
            exerciseName.setText(exersiseNames.get(position));
            number1 = (TextView) frameForEverything.findViewById(R.id.number1);
            rep1 = (TextView) frameForEverything.findViewById(R.id.repsNumber1);
            weight1 = (TextView) frameForEverything.findViewById(R.id.weightNumber1);
            number1.setText("1");
            rep1.setText(setsInfos.get(position).getReps().get(0).toString());
            weight1.setText(setsInfos.get(position).getWeight().get(0).toString());

            TextView number2 = (TextView) frameForEverything.findViewById(R.id.number2);
            TextView rep2 = (TextView) frameForEverything.findViewById(R.id.repsNumber2);
            TextView weight2 = (TextView) frameForEverything.findViewById(R.id.weightNumber2);
            number2.setText("2");
            rep2.setText(setsInfos.get(position).getReps().get(1).toString());
            weight2.setText(setsInfos.get(position).getWeight().get(1).toString());

            TextView number3 = (TextView) frameForEverything.findViewById(R.id.number3);
            TextView rep3 = (TextView) frameForEverything.findViewById(R.id.repsNumber3);
            TextView weight3 = (TextView) frameForEverything.findViewById(R.id.weightNumber3);
            number3.setText("3");
            rep3.setText(setsInfos.get(position).getReps().get(2).toString());
            weight3.setText(setsInfos.get(position).getWeight().get(2).toString());

            TextView number4 = (TextView) frameForEverything.findViewById(R.id.number4);
            TextView rep4 = (TextView) frameForEverything.findViewById(R.id.repsNumber4);
            TextView weight4 = (TextView) frameForEverything.findViewById(R.id.weightNumber4);
            number4.setText("4");
            rep4.setText(setsInfos.get(position).getReps().get(3).toString());
            weight4.setText(setsInfos.get(position).getWeight().get(3).toString());

            //l2.setVisibility(View.GONE);
            //l3.setVisibility(View.GONE);
            //l4.setVisibility(View.GONE);
            l5.setVisibility(View.GONE);
            lAdditional.setVisibility(View.GONE);

        }

        if(numberOfsets == 5){
            frameForEverything = convertView.findViewById(R.id.frameForEverything);
            exerciseName = (TextView) frameForEverything.findViewById(R.id.exerciseName);
            exerciseName.setText(exersiseNames.get(position));
            number1 = (TextView) frameForEverything.findViewById(R.id.number1);
            rep1 = (TextView) frameForEverything.findViewById(R.id.repsNumber1);
            weight1 = (TextView) frameForEverything.findViewById(R.id.weightNumber1);
            number1.setText("1");
            rep1.setText(setsInfos.get(position).getReps().get(0).toString());
            weight1.setText(setsInfos.get(position).getWeight().get(0).toString());

            TextView number2 = (TextView) frameForEverything.findViewById(R.id.number2);
            TextView rep2 = (TextView) frameForEverything.findViewById(R.id.repsNumber2);
            TextView weight2 = (TextView) frameForEverything.findViewById(R.id.weightNumber2);
            number2.setText("2");
            rep2.setText(setsInfos.get(position).getReps().get(1).toString());
            weight2.setText(setsInfos.get(position).getWeight().get(1).toString());

            TextView number3 = (TextView) frameForEverything.findViewById(R.id.number3);
            TextView rep3 = (TextView) frameForEverything.findViewById(R.id.repsNumber3);
            TextView weight3 = (TextView) frameForEverything.findViewById(R.id.weightNumber3);
            number3.setText("3");
            rep3.setText(setsInfos.get(position).getReps().get(2).toString());
            weight3.setText(setsInfos.get(position).getWeight().get(2).toString());

            TextView number4 = (TextView) frameForEverything.findViewById(R.id.number4);
            TextView rep4 = (TextView) frameForEverything.findViewById(R.id.repsNumber4);
            TextView weight4 = (TextView) frameForEverything.findViewById(R.id.weightNumber4);
            number4.setText("4");
            rep4.setText(setsInfos.get(position).getReps().get(3).toString());
            weight4.setText(setsInfos.get(position).getWeight().get(3).toString());

            TextView number5 = (TextView) frameForEverything.findViewById(R.id.number5);
            TextView rep5 = (TextView) frameForEverything.findViewById(R.id.repsNumber5);
            TextView weight5 = (TextView) frameForEverything.findViewById(R.id.weightNumber5);
            number5.setText("5");
            rep5.setText(setsInfos.get(position).getReps().get(4).toString());
            weight5.setText(setsInfos.get(position).getWeight().get(4).toString());

            //l2.setVisibility(View.GONE);
            //l3.setVisibility(View.GONE);
            //l4.setVisibility(View.GONE);
            //l5.setVisibility(View.GONE);
            lAdditional.setVisibility(View.GONE);
        }

        if(numberOfsets > 5){
            frameForEverything = convertView.findViewById(R.id.frameForEverything);
            exerciseName = (TextView) frameForEverything.findViewById(R.id.exerciseName);
            exerciseName.setText(exersiseNames.get(position));
            number1 = (TextView) frameForEverything.findViewById(R.id.number1);
            rep1 = (TextView) frameForEverything.findViewById(R.id.repsNumber1);
            weight1 = (TextView) frameForEverything.findViewById(R.id.weightNumber1);
            number1.setText("1");
            rep1.setText(setsInfos.get(position).getReps().get(0).toString());
            weight1.setText(setsInfos.get(position).getWeight().get(0).toString());

            TextView number2 = (TextView) frameForEverything.findViewById(R.id.number2);
            TextView rep2 = (TextView) frameForEverything.findViewById(R.id.repsNumber2);
            TextView weight2 = (TextView) frameForEverything.findViewById(R.id.weightNumber2);
            number2.setText("2");
            rep2.setText(setsInfos.get(position).getReps().get(1).toString());
            weight2.setText(setsInfos.get(position).getWeight().get(1).toString());

            TextView number3 = (TextView) frameForEverything.findViewById(R.id.number3);
            TextView rep3 = (TextView) frameForEverything.findViewById(R.id.repsNumber3);
            TextView weight3 = (TextView) frameForEverything.findViewById(R.id.weightNumber3);
            number3.setText("3");
            rep3.setText(setsInfos.get(position).getReps().get(2).toString());
            weight3.setText(setsInfos.get(position).getWeight().get(2).toString());

            TextView number4 = (TextView) frameForEverything.findViewById(R.id.number4);
            TextView rep4 = (TextView) frameForEverything.findViewById(R.id.repsNumber4);
            TextView weight4 = (TextView) frameForEverything.findViewById(R.id.weightNumber4);
            number4.setText("4");
            rep4.setText(setsInfos.get(position).getReps().get(3).toString());
            weight4.setText(setsInfos.get(position).getWeight().get(3).toString());

            TextView number5 = (TextView) frameForEverything.findViewById(R.id.number5);
            TextView rep5 = (TextView) frameForEverything.findViewById(R.id.repsNumber5);
            TextView weight5 = (TextView) frameForEverything.findViewById(R.id.weightNumber5);
            number5.setText("5");
            rep5.setText(setsInfos.get(position).getReps().get(4).toString());
            weight5.setText(setsInfos.get(position).getWeight().get(4).toString());

            //l2.setVisibility(View.GONE);
            //l3.setVisibility(View.GONE);
            //l4.setVisibility(View.GONE);
            //l5.setVisibility(View.GONE);
            //lAdditional.setVisibility(View.GONE);
            TextView additionalNumber = (TextView) frameForEverything.findViewById(R.id.additionalSets);
            int numberOfadditional = numberOfsets - 5;
            additionalNumber.setText(""+numberOfadditional);
        }







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
