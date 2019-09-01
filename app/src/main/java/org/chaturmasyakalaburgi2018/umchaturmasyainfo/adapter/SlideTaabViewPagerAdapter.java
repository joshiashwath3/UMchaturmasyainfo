package org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter;

import androidx.core.app.Fragment;
import androidx.core.app.FragmentManager;
import androidx.core.app.FragmentPagerAdapter;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.fragment.EventFragment;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.fragment.UpdatesFragment;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.fragment.ViewFragment;

/**
 * Created by 19SIMBLS LLP on 27,June,2018
 *
 * @Author Ashwath Joshi
 */
public class SlideTaabViewPagerAdapter extends FragmentPagerAdapter {

    public SlideTaabViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new EventFragment();
        } else if (position == 1) {
            return new ViewFragment();
        } else return new UpdatesFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}