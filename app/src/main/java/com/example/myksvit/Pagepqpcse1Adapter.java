package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagepqpcse1Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagepqpcse1Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabpqpcse11 tabpqpcse11= new Tabpqpcse11();
                return tabpqpcse11;
            case 1:
                Tabpqpcse12 tabpqpcse12= new Tabpqpcse12();
                return tabpqpcse12;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
