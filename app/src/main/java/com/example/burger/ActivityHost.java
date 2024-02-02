package com.example.burger;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ActivityHost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 pager2 = findViewById(R.id.pager2);

        AdapterLancheFragment adapterLancheFragment = new AdapterLancheFragment(ActivityHost.this);

        BurgerFragment burgerFragment = new BurgerFragment();
        HotDogFragment hotDogFragment = new HotDogFragment();
        HotDogFragment coxinha = new HotDogFragment();

        adapterLancheFragment.addFragment(burgerFragment, "Burgers");

        adapterLancheFragment.addFragment(hotDogFragment, "Hot Dog");

        adapterLancheFragment.addFragment(coxinha, "Fritos");

        pager2.setAdapter(adapterLancheFragment);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, pager2, (tab, position) -> {

            View customViewTab = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tabs_custom_layout, pager2, false);

            tab.setText(adapterLancheFragment.getTitleFragment(position));

            TextView nameLanche = customViewTab.findViewById(R.id.nameLanche);

            TabLayout.Tab selectedTab = tabLayout.getTabAt(0);


            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int positionTab = tab.getPosition();
                    Typeface typeface = ResourcesCompat.getFont(ActivityHost.this, R.font.freckle_face);
                    nameLanche.setTypeface(typeface, R.style.tab_text);
                    nameLanche.setText(adapterLancheFragment.getTitleFragment(positionTab));

                    tab.setCustomView(customViewTab);

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tabl) {
                    tab.setCustomView(null);
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        });tabLayoutMediator.attach();



    }
}