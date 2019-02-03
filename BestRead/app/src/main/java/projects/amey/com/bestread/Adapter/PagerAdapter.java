package projects.amey.com.bestread.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import projects.amey.com.bestread.Fragments.CameraFragment;
import projects.amey.com.bestread.Fragments.SpeechFragment;
import projects.amey.com.bestread.Fragments.TextFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CameraFragment cameraFragment = new CameraFragment();
                return cameraFragment;

            case 1:
                TextFragment textFragment = new TextFragment();
                return textFragment;

            case 2:
                SpeechFragment speechFragment = new SpeechFragment();
                return speechFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
