package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    int counttab;
    public PageAdapter(FragmentManager fm,int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                TabOne tabOne= new TabOne();
                return tabOne;
            case 1:
                TabTwo tabTwo= new TabTwo();
                return tabTwo;
            case 2:
                TabThree tabThree= new TabThree();
                return tabThree;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
