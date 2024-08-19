package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagecse3Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagecse3Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabcse31 tabcse31= new Tabcse31();
                return tabcse31;
            case 1:
                Tabcse32 tabcse32= new Tabcse32();
                return tabcse32;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
