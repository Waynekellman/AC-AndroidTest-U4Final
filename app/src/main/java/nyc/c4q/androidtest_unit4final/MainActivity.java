package nyc.c4q.androidtest_unit4final;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nyc.c4q.androidtest_unit4final.networking.ColorNetwork;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ColorAdapter adapter;
    protected HashMap<String, String> colorDict;
    protected List<String> colorsList;
    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorDict = new HashMap<>();
        colorDict.put("indigo", "#4b0082");
        colorDict.put("green", "#00ff00");
        colorDict.put("blue", "#0000ff");
        colorDict.put("red", "#ff0000");
        // TODO: adding all the colors and their values would be tedious, instead fetch it from the url below
        // https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/operable/cog/master/priv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ColorNetwork service = retrofit.create(ColorNetwork.class);
        Call<HashMap<String,String>> colors = service.getColors();
        colors.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                colorDict.clear();
                colorDict = response.body();
                Log.d(TAG,colorDict.toString());
                adapter.setColorDict(colorDict);
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        colorsList = new ArrayList<>();
        String[] names = new String[] {"blue", "red", "purple", "indigo", "orange", "brown", "black", "green"};
        for(String n: names) colorsList.add(n);

        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new ColorAdapter(colorsList, colorDict);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // TODO: Add options menu with the item "Info" which is always visible
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    // TODO: When "Info" menu item is clicked, display the fragment InfoFragment
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        FragmentManager manager = getSupportFragmentManager();
        InfoFragment fragment = (InfoFragment) manager.findFragmentByTag("info_frag");

        switch (item.getItemId()) {
            case R.id.info:
                if(fragment != null){
                    manager.beginTransaction().remove(fragment).commit();
                }else {
                    fragment = new InfoFragment();
                    manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // TODO: If InfoFragment is already visible and I click "Info", remove InfoFragment from the view.
    // Link to creating options menu: https://github.com/C4Q/AC-Android/tree/v2/Android/Lecture-9-Menus-and-Navigation#anatomy-of-menu-xml
}
