package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagepqpcse4Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagepqpcse4Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabpqpcse41 tabpqpcse41= new Tabpqpcse41();
                return tabpqpcse41;
            case 1:
                Tabpqpcse42 tabpqpcse42= new Tabpqpcse42();
                return tabpqpcse42;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
