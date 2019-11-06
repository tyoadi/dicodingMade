package com.sulistyolabs.made.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }

}