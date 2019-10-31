package app.mma.jsonxml2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
 import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_xml, btn_json, btn_xmljdom,btn_Tour;
    ListView listView;

    private List<Flower> flowers;
    private ArrayAdapter<Flower> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_xml = findViewById(R.id.btn_xml_pp);
        btn_json = findViewById(R.id.btn_json);
        btn_xmljdom = findViewById(R.id.btn_xml_jdom);
        btn_Tour=findViewById(R.id.btn_tours);
        listView = findViewById(R.id.listview);

        btn_xml.setOnClickListener(this);
        btn_xmljdom.setOnClickListener(this);
        btn_json.setOnClickListener(this);
        btn_Tour.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        if (v.getId() == btn_xml.getId()) {
            InputStream input = getResources().openRawResource(R.raw.flowers_xml);
            flowers = new FlowerXmlPullParser(input).parseXml();
            Toast.makeText(this, "flower" + flowers.size(), Toast.LENGTH_SHORT).show();
            refresh();
        } else if (v.getId() == btn_xmljdom.getId()) {
            flowers = new FlowerXmlJdomParser(MainActivity.this).parsexml();
            Toast.makeText(this, "flowers=" + flowers.size(), Toast.LENGTH_SHORT).show();
            refresh();
        } else if (v.getId() == btn_json.getId()) {
            InputStream input = getResources().openRawResource(R.raw.flowers_json);
            flowers = FlowerJsonParser.parseJson(input);
            refresh();
        }else if (v.getId()==btn_Tour.getId()){
            InputStream input=getResources().openRawResource(R.raw.tours);
            List<Tour> tourList=new TourJdomParser(input).parseXml();
            ArrayAdapter<Tour> tourArrayAdapter=new ArrayAdapter<Tour>(MainActivity.this,android.R.layout.simple_list_item_1,tourList);
            listView.setAdapter(tourArrayAdapter);

            JSONArray jsonArray=Tour.TourListToJsonarray(tourList);

            try {
                FileOutputStream file=openFileOutput("tour.json",MODE_PRIVATE);
                file.write(jsonArray.toString().getBytes());
                file.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    public void refresh () {
        if (flowers == null)
            flowers = new ArrayList<>();
        adapter = new FlowerAdapter(this, flowers);
        listView.setAdapter(adapter);
    }
}

