package charlyn23.c4q.nyc.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
    private String[] pictureTitles;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictureTitles = getResources().getStringArray(R.array.pictureTitles);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.listView);

        //Set adapter for list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, pictureTitles);
        listView.setAdapter(adapter);

        //Set click listener for list
        listView.setOnItemClickListener(new DrawerItemClickListener());

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(listView);
//        menu.findItem(R.string.drawerIsOpen).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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


    //Create click listener for list
    public class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    //Assign a fragment to each position on navdrawer
    //Remember: include your defalut(home) fragment as a placeholder, otherwise, your
    // drawer will be off by one
    public void selectItem(int position) {
        if (position == 0) {
            fragment = new HomeFragment();
        }
        else if (position == 1) {
            fragment = new CounterFragment();
        }
        else if (position == 2) {
            fragment = new PictureFragementOne();
        }
        else if (position == 3) {
            fragment = new PictureFragmentTwo();
        }
        else if (position == 4 ) {
            fragment = new PictureFragmentThree();
        }


        //Fragment Manager to handle the fragments that will appear in the fragment conainter in
        //Main's layout. Remember, there's one activity and its frag-container will host
        //all of the fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.pictureContainer, fragment).commit();

        //Once an item is selected in drawerlist, close the drawer
        listView.setItemChecked(position, true);
        drawerLayout.closeDrawer(listView);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


}

