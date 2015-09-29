package com.example.elisandler.inspirationpal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;


public class MyFriendsList extends ActionBarActivity {

    private static final String SERVICE_URL = "http://128.119.22.172:8080/trialServer/webapi";

    View frameForFriendsList;
    ArrayAdapter adapter;
    ListView lv;
    String clickedListItem;
    ArrayList<String> friendList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends_list);




        frameForFriendsList = findViewById(R.id.frameForFriendList);
        lv = (ListView) frameForFriendsList.findViewById(R.id.friendList);
        adapter = new ArrayAdapter(frameForFriendsList.getContext(),android.R.layout.simple_list_item_1,friendList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ListClickHandler());




    }


    private class myTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            // String secondResourseURL = params[0] + "/secondresource";
            // Log.i("", "this is the url: " + secondResourseURL);
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);
            Log.i("", "about to make the service");
            WebResource service = client.resource(params[0]);

            Log.i("", "made the service!!");

            //WebResource addService = service.path("/secondresource");

            //Log.i("", "Add Output as Text: " + getOutputAsText(addService));
            //System.out.println("---------------------------------------------------");


            //return getOutputAsText(addService);
            return null;
        }

        protected void onPostExecute(String result) {
            //TextView myText = (TextView)findViewById(R.id.textToDisplayMessage);
            //myText.setText(result.toString());
        }
    }

    private static String getOutputAsText(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).get(String.class);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_friends_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arrayAdapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            clickedListItem = (String) lv.getItemAtPosition(position);
            Log.i("", "this is the clickedListItem: " + clickedListItem);

        }
    }

}
