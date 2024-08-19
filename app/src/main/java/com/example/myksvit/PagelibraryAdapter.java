package com.example.myksvit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagelibraryAdapter extends FragmentStatePagerAdapter {

    int counttab;
    public PagelibraryAdapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                TabSyllabus tabSyllabus= new TabSyllabus();
                return tabSyllabus;
            case 1:
                TabMaterials tabMaterials = new TabMaterials();
                return tabMaterials;
            case 2:
                TabPqp tabPqp= new TabPqp();
                return tabPqp;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
