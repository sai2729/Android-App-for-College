package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagepqpcse3Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagepqpcse3Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabpqpcse31 tabpqpcse31= new Tabpqpcse31();
                return tabpqpcse31;
            case 1:
                Tabpqpcse32 tabpqpcse32= new Tabpqpcse32();
                return tabpqpcse32;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
