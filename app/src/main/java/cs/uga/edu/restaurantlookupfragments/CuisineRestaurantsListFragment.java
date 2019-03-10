package cs.uga.edu.restaurantlookupfragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.InputStream;

public class CuisineRestaurantsListFragment extends Fragment {
    private static final String DEBUG_TAG = "XLLCResListViewFragment";

    public static CuisineRestaurantsListFragment newInstance(int cuisineIndex) {
        Log.d( DEBUG_TAG, "CuisineRestaurantsListFragment.newInstance(): versionIndex: " + cuisineIndex );

        // this uses the default constructor (not defined in this class, but Java-supplied)
        CuisineRestaurantsListFragment fragment = new CuisineRestaurantsListFragment();

        // save the selected list versionIndex in the new fragment's Bundle data
        // the AndroidVersionInfoFragment needs to know the version to display
        Bundle args = new Bundle();
        args.putInt( "cuisineIndex", cuisineIndex );
        fragment.setArguments( args );

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        Log.d( DEBUG_TAG, "CuisineRestaurantsListFragment.onCreateView()" );

        // Programmatically, create a scrollable TextView to show the Android version information
        ScrollView scroller = new ScrollView( getActivity()) ;
        TextView textViewResList = new TextView( getActivity() );
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.chinese);
        scroller.addView( textViewResList );

        // Set the padding for the new TextView
        // These padding attributes are normally defined in the XML file
        // here, they are set programmatically.
        int padding = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 12, getActivity().getResources().getDisplayMetrics() );
        textViewResList.setPadding( padding, padding, padding, padding );

        // set the text size
        textViewResList.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);

        // show the android version info
        // add data from raw file
        callListRestaurants(getShownIndex(),textViewResList);

        return scroller;
    }

    public int getShownIndex() {
        return getArguments().getInt("cuisineIndex", 0 );
    }


    /**
     * Methos setOverviewTextViews reads the raw file and displays in the desired text view
     *
     * @param view                    textview in which raw file content needs to be displayed
     * @param restListOverviewRawFile raw file for content
     * @throws Exception exception to handle any excption occuring while reading/writing raw file
     */
    protected void setOverviewTextViews(TextView view, @RawRes int restListOverviewRawFile) {
        try {
            InputStream mainOverviewFile = this.getResources().openRawResource(restListOverviewRawFile);
            byte[] cuisineOverview = new byte[mainOverviewFile.available()];
            mainOverviewFile.read(cuisineOverview);
            view.setText(Html.fromHtml(new String(cuisineOverview)));
        } catch (Exception e) {
            Log.i(DEBUG_TAG, "exception:while R/W the raw file");
        }

    }

    /**
     * Method callListRestaurants populates the value in the textViews depending upon the option selected
     * in spinner in the main activity window. Also the title of the action bar is also updated upon the
     * selected spinner option.
     *
     * @param index int value which is the deciding parameter on what values will be populated in views
     * @param textView textView to display list of restaurants
     */
    protected void callListRestaurants(int index, TextView textView) {
        switch (index) {
            case 0 : //"Native American":
                @StringRes int nativeamerican = R.string.native_american_res_title;
//                getSupportActionBar().setTitle(nativeamerican);
                setOverviewTextViews(textView, R.raw.native_american_restlist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.native_american,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
            case 1 : // "Indian":
                @StringRes int indian = R.string.indian_res_title;
//                getSupportActionBar().setTitle(indian);
                setOverviewTextViews(textView, R.raw.indian_restlist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.indian_cuisine,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
            case 2 : // "Thai":
                @StringRes int thai = R.string.thai_res_title;
//                getSupportActionBar().setTitle(thai);
                setOverviewTextViews(textView, R.raw.thai_restlist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.thai,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
            case 3 : //"Italian":
                @StringRes int italian = R.string.italian_res_title;
//                getSupportActionBar().setTitle(italian);
                setOverviewTextViews(textView, R.raw.italian_restist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.italian,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
            case 4 : //"Chinese":
                @StringRes int chinese = R.string.chinese_res_title;
//                getSupportActionBar().setTitle(chinese);
                setOverviewTextViews(textView, R.raw.chinese_restlist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.chinese,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
            case 5 : // "Mexican":
                @StringRes int mexican = R.string.mexican_res_title;
//                getSupportActionBar().setTitle(mexican);
                setOverviewTextViews(textView, R.raw.mexican_restlist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.mexican,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
            case 6 : //"Japanese":
                @StringRes int japanese = R.string.japanese_res_title;
//                getSupportActionBar().setTitle(japanese);
                setOverviewTextViews(textView, R.raw.japanese_restlist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.japanese,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
            case 7 : //"Armenian":
                @StringRes int armenian = R.string.armenian_res_title;
//                getSupportActionBar().setTitle(armenian);
                setOverviewTextViews(textView, R.raw.armenian_restlist);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.armenian,0,0);
                Linkify.addLinks(textView,Linkify.ALL);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onCreate() ");
        super.onCreate(savedInstanceState);
        Log.d(DEBUG_TAG, "OnCreate(): " + getShownIndex());


    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onStart(): " + getShownIndex());
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onResume():" +  getShownIndex());
    }

    @Override
    public void onDetach() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onDetach()");
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onPause(): " + getShownIndex());
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onStop(): " + getShownIndex());
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineRestaurantListFragment.onDestroy()");
        super.onDestroy();
    }
}
