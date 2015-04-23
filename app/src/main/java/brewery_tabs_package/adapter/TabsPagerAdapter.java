package brewery_tabs_package.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.davidclifford.beermaps.BreweryListFragment;
import com.example.davidclifford.beermaps.BreweryProximityFragment;
import com.example.davidclifford.beermaps.RandomBreweryFragment;

/**
 * Created by davidclifford on 4/22/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // BreweryList Activity
                return new BreweryListFragment();
            case 1:
                // BreweryProximity Activity
                return new BreweryProximityFragment();
            case 2:
                // RandomBrewery Activity
                return new RandomBreweryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Use this to return the number of tabs
        int numOfTabs = 3;
        return numOfTabs;
    }
}
