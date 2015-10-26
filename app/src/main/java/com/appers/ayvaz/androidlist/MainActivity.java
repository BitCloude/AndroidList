package com.appers.ayvaz.androidlist;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ImageView imageView;
    TextView title;
    TextView description;
    DBUtility dbUtility;

    Bitmap image;
    Bitmap image2;
    Bitmap image3;
    Bitmap image4;
    Bitmap image5;

    String[] titles = new String[]{"head1", "head2", "head3", "head4", "head5"};
    String[] desc = new String[]{"a head", "another head", "yet another head", "human head", "icon head"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbUtility = new DBUtility(getApplicationContext());
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this));
        image = BitmapFactory.decodeResource(this.getResources(), R.drawable.plane1);
        image2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.plane3);
        image3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.plane4);
        image4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.plane5);
        image5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.plane6);

        dbUtility.insertData(image);
        dbUtility.insertData(image2);
        dbUtility.insertData(image3);
        dbUtility.insertData(image4);
        dbUtility.insertData(image5);
    }
    class Person{
        Bitmap image;
        String title;
        String desc;
        public Person(String title, String desc)
        {
            //DBUtility dbUtility2 = new DBUtility(getApplicationContext());
            Cursor cursor = dbUtility.retreive(1);
            if((cursor.getCount() >0)){
                while (cursor.moveToNext()){
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(cursor.getBlob(0));
                    this.image = BitmapFactory.decodeStream(inputStream);

                }
            }
            this.title = title;
            this.desc = desc;
        }
    }
    class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<Person> persons =new ArrayList<>(100);
        int i = 0; int count = 0;
        public CustomAdapter(Context context) {
            this.context = context;
            for(int j=0;j<100;j++){
                i = j%titles.length;
                persons.add(new Person(titles[i],desc[i]));
            }
        }

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("Debug", "visited" + count);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            View row = inflater.inflate(R.layout.custom_row,parent,false);
            Person person = persons.get(position);
            imageView = (ImageView) row.findViewById(R.id.imageView);
            title = (TextView) row.findViewById(R.id.title);
            description = (TextView) row.findViewById(R.id.desc);
            imageView.setImageBitmap(person.image);
            title.setText(person.title);
            description.setText(person.desc);
            return row;
        }
    }

   
}
