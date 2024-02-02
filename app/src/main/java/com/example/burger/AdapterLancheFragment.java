package com.example.burger;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterLancheFragment extends FragmentStateAdapter {

    List<Fragment> fragmentsList = new ArrayList<>();
    List<String> titleFragmentList = new ArrayList<>();

    public AdapterLancheFragment(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentsList.get(position);
    }

    public void addFragment(Fragment fragmentX, String title){
        fragmentsList.add(fragmentX);
        titleFragmentList.add(title);

    }
    public String getTitleFragment(int position){
        return titleFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentsList.size();
    }
}
