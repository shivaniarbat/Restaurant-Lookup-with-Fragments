package cs.uga.edu.restaurantlookupfragments;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CuisineListFragment extends ListFragment{

    private String[] cuisineListStringArr = {
            "Native American",
            "Indian",
            "Thai",
            "Italian",
            "Chinese",
            "Mexican",
            "Japanese",
            "Armenian",
    };

    private static final String DEBUG_TAG = "CListFragment";
    // indicate if this is a landscape mode with both fragments present or not
    boolean twoFragmentsActivity = false;

    // list selection index
    int cuisineIndex = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(DEBUG_TAG,"FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onActivityCreated()");

        // create a new ArrayAdapter for the list
        setListAdapter( new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_activated_1, cuisineListStringArr ) );

        // set the twoFragmentsActivity variable to true iff we are in 2 fragment (landscape) view
        View detailsFrame = getActivity().findViewById( R.id.cuisineInfo );
        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // restore the saved list index selection (Android version no), if available
        if( savedInstanceState != null ) {
            // Restore last state for checked position.
            cuisineIndex = savedInstanceState.getInt("androidVersionSelection", 0 );
            Log.d( DEBUG_TAG, "AndroidVersionListFragment.onActivityCreated(): restored versionIndex: " + cuisineIndex );
        }

        // set the list mode as single choice and
        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
        getListView().setItemChecked( cuisineIndex, true );

        // if we are in 2 fragment (landscape) orientation, show the Android version information on the right side
        // This class will act as the "driver" here by displaying the details using the other fragment.
        if( twoFragmentsActivity ) {

            // display the information about the selected Android version in the fragment on the right (details)
            viewCuisineInfo( cuisineIndex );

            // The list in the landscape orientation may be shorter and the selected item
            // which was visible in portrait mode might be invisible (out of view)
            // i.e., "below" the end of the screen in landscape orientation.
            // To make it visible again, call smoothScrollToPosition
            getListView().smoothScrollToPosition( cuisineIndex );
        }

    }

    @Override
    public void onListItemClick( ListView l, View v, int position, long id ) {
        // on a click on a list item, show the selected Android version info
        // store the list view and selection for coming back to the list (using the back button)
        //firstVisibleVersionIndex = l.getFirstVisiblePosition();
        //versionIndex = position;
        viewCuisineInfo( position );
    }

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt( "androidVersionSelection", cuisineIndex);
        Log.d( DEBUG_TAG, "AndroidVersionListFragment.onSaveInstanceState(): saved versionIndex: " + cuisineIndex );
    }

    void viewCuisineInfo(int cuisineIndex){
        this.cuisineIndex = cuisineIndex;

        if( twoFragmentsActivity ) {  // only in the 2 fragment (landscape) orientation

            getListView().setItemChecked( cuisineIndex, true );

            // get the placeholder element (FrameLayout) in a 2 fragment (landscape) orientation layout
            CuisineRestaurantsListFragment details =
                    (CuisineRestaurantsListFragment) getFragmentManager().findFragmentById(R.id.cuisineInfo);

            if( details == null || details.getShownIndex() != cuisineIndex ) {

                // obtain a new Android info fragment instance
                details = CuisineRestaurantsListFragment.newInstance( cuisineIndex );

                // replace the placeholder with the new fragment stance within a transaction
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.cuisineInfo, details );

                // TRANSIT_FRAGMENT_FADE means that the fragment should fade in or fade out
                fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );

                // commit the transaction, i.e. make the changes
                fragmentTransaction.commit();
            }
        }
        else {
            // In a 1 fragment orientation (portrait), start a new activity using an Intent, as in the previous demo app
            Intent intent = new Intent();
            intent.setClass( getActivity(), CuisineRestaurantViewActivity.class );
            intent.putExtra("cuisineIndex", cuisineIndex);

            startActivity( intent );
        }
    }


    ///////

//    void viewCuisineInfo(int index) {
//
//        mCurPosition = index;
//        Log.d(DEBUG_TAG, "CuisineListFragment.onBackStackChanged() : " + mCurPosition + " " + mShowTwoFragments);
//        if(mShowTwoFragments) {
//            // Check what fragment is currently shown, replace if needed.
//            CuisineRestaurantsListFragment details = (CuisineRestaurantsListFragment) getFragmentManager()
//                    .findFragmentById(R.id.cusineList);
//            if (details == null || details.getShownIndex() != index) {
//
//                // Make new fragment to show this selection.
//                CuisineRestaurantsListFragment newDetails = CuisineRestaurantsListFragment
//                        .newInstance(index);
//
//                // Execute a transaction, replacing any existing fragment
//                // with this one inside the frame.
//                android.support.v4.app.FragmentManager fm = getFragmentManager();
//                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.cusineList, newDetails);
//                // Add this fragment instance to the back-stack of the Activity
//                // so we can backtrack through our veggies
//                if (index != -1) {
//                    String[] veggies = getResources().getStringArray(
//                            R.array.cuisine_list);
//                    String strBackStackTagName = veggies[index];
//                    ft.addToBackStack(strBackStackTagName);
//                }
//                // Fade between Urls
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                ft.commit();
//            }
//
//        } else {
//            // Otherwise we need to launch a new activity to display
//            // the dialog fragment with selected text.
//            Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.else part");
//            Intent intent = new Intent();
//            intent.setClass(getActivity(), CuisineRestaurantViewActivity.class);
//            intent.putExtra("index", index);
//            startActivity(intent);
//        }
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onStart()");
        super.onStart();
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onDetach()");
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onPause()");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onResume(): "
                + cuisineIndex);
        super.onResume();
    }

    @Override
    public void onStop() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(DEBUG_TAG, "FRAGMENT LIFECYCLE EVENT: CuisineListFragment.onDestroy()");
        super.onDestroy();
    }
}