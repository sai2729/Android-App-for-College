package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagecse1Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagecse1Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabcse11 tabcse11= new Tabcse11();
                return tabcse11;
            case 1:
                Tabcse12 tabcse12= new Tabcse12();
                return tabcse12;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
