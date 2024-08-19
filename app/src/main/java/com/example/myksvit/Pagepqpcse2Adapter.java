package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagepqpcse2Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagepqpcse2Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabpqpcse21 tabpqpcse21= new Tabpqpcse21();
                return tabpqpcse21;
            case 1:
                Tabpqpcse22 tabpqpcse22= new Tabpqpcse22();
                return tabpqpcse22;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
