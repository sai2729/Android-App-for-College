package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pagecse2Adapter extends FragmentStatePagerAdapter {

    int counttab;
    public Pagecse2Adapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                Tabcse21 tabcse21= new Tabcse21();
                return tabcse21;
            case 1:
                Tabcse22 tabcse22= new Tabcse22();
                return tabcse22;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
