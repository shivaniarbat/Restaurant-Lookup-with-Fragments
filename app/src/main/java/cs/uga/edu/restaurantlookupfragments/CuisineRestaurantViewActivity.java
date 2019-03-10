package cs.uga.edu.restaurantlookupfragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class CuisineRestaurantViewActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "XLLCRList";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        Log.d( DEBUG_TAG, "CuisineRestaurantListActivity.onCreate()" );

        super.onCreate( savedInstanceState );

        // IMPORTANT:
        // Add the back button in the ActionBar of this activity.  It will appear
        // as a left chevron (arrow tip), the regular back button.
        //
        // We can't use the Parent specification in the AndroidManifest, since
        // it would place the activity on the back stack.  Consequently, when going
        // back to the screen with the list of Android versions (using the "parent-specified"
        // back button, this would cause a recreation of the ListFragment.  The new list
        // would show the initial item (Android version) as selected, not the one we
        // actually selected when transitioning to the Android version details screen.
        //
        // However, there must be a listener added for this back button (look below).
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled( true );

        // if this call is in landscape orientation, do nothing and return, as the main activity w/ ListFragment
        // will do the work
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            Log.d( DEBUG_TAG, "CuisineRestaurantListActivity.onCreate(): in landscape mode; returning" );
            finish();
            return;
        }

        Log.d(DEBUG_TAG, "CuisineRestaurantListActivity.onCreate(): in portrait mode; replacing fragments");

        CuisineRestaurantsListFragment androidVersionInfoFragment = new CuisineRestaurantsListFragment();

        // pass on any saved data, i.e., Android version no (list index)
        androidVersionInfoFragment.setArguments( getIntent().getExtras() );

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // add the fragment within a transaction
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, androidVersionInfoFragment).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Implement Back button listener method.
        // This method may be used for other actions from the ActionBar menu, if provided in the layout.
        int id = item.getItemId();

        // android.R.id.home is the built-in designation of the back button widget.
        if( id == android.R.id.home ) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Log.d(DEBUG_TAG,"CuisineRestaurantViewActivity.onCreate()");
//
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            // If the screen is now in landscape mode, we can show the
//            // dialog in-line so we don't need this activity.
//            finish();
//            return;
//        }
//
//        if (savedInstanceState == null) {
//            setContentView(R.layout.fragment_cuisinelist);
//
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//            // During initial setup, plug in the veggie fragment.
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            CuisineRestaurantsListFragment details = new CuisineRestaurantsListFragment();
//            details.setArguments(getIntent().getExtras());
//
//            ft.replace(R.id.cusineList, details);
//            ft.commit();
//        }
//
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        if (id == android.R.id.home) {
//            onBackPressed();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    // These activity callback methods are not needed and are for edational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "CuisineRestaurantViewActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "CuisineRestaurantViewActivity.onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "CuisineRestaurantViewActivity.onPause()" );
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "CuisineRestaurantViewActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "CuisineRestaurantViewActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "CuisineRestaurantViewActivity.onRestart()" );
        super.onRestart();
    }
}
