package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagecse4Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagecse4Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabcse41 tabcse41= new Tabcse41();
                return tabcse41;
            case 1:
                Tabcse42 tabcse42= new Tabcse42();
                return tabcse42;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
