package at.fhj.itm10.mobcomp.drivebyreminder.activities;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.fhj.itm10.mobcomp.drivebyreminder.R;
import at.fhj.itm10.mobcomp.drivebyreminder.helper.MainFragmentPagerAdapter;

import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;

/**
 * Main view.
 * 
 * @author Wolfgang Gaar
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboSherlockFragmentActivity
		implements OnNavigationListener, OnPageChangeListener {
	
	@InjectView(R.id.lblNavigationList)
	private TextView lblNavigationSelected;
	
	@InjectView(R.id.pgrMainView)
	private ViewPager pagerMainView;

	private MainFragmentPagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        Context context = getSupportActionBar().getThemedContext();
        // Set content of nav dropdown
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context,
        		R.array.locations, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        // Pager class which enables us to have several fragments loaded and "pagable"
        pagerAdapter = new MainFragmentPagerAdapter(
        		getSupportFragmentManager());
        pagerMainView.setAdapter(pagerAdapter);
        pagerMainView.setOnPageChangeListener(this);
        
        Log.d("MainActivity", "onCreate: given savedInstanceState = " + savedInstanceState);
        
        if (savedInstanceState != null) {
			restoreState(savedInstanceState);
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("MainActivity", "onSaveInstanceState: setting state");

		outState.putInt("currentFragment", pagerMainView.getCurrentItem());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d("MainActivity", "onRestoreInstanceState: given savedInstanceState = "
				+ savedInstanceState);
		
		if (savedInstanceState != null) {
			restoreState(savedInstanceState);
		}
	}
	
	private void restoreState(Bundle savedInstanceState) {
		// Pager and nav menu item number
		int currentFragment = savedInstanceState.getInt("currentFragment");
		getSupportActionBar().setSelectedNavigationItem(currentFragment);
		pagerMainView.setCurrentItem(currentFragment, false);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        case R.id.menu_main_add:
        	Intent intent = new Intent(this, AddTaskActivity.class);
        	// There should only be one "Add new" activity open at any time
        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        	this.startActivity(intent);
        	return true;
        case R.id.menu_main_settings:
        	this.startActivity(new Intent(this, SettingsActivity.class));
        	return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Used for the top-left navigation menu.
     */
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// Change the focused fragment of the pager if the navigation list is changed.
		// itemPosition = number of fragment, see MainFragmentPagerAdapter for numbering
		pagerMainView.setCurrentItem(itemPosition, true);

		return true;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// Empty by intention
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		// Empty by intention	
	}

	/**
	 * Called after a fragment has been selected ("scrolled to") by the user.
	 */
	@Override
	public void onPageSelected(int position) {
		// Change the focused navigation list item if the viewed fragment has been changed.
		// position = number of fragment, see MainFragmentPagerAdapter for numbering
		getSupportActionBar().setSelectedNavigationItem(position);
	}

}
