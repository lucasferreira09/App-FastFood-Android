package com.example.burger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.burger.HoldersEAdapters.AdapterTiposLanches;
import com.example.burger.databinding.ActivityHostBinding;
import com.example.burger.MenuLanches.BurgerFragment;
import com.example.burger.MenuLanches.CombosFragment;
import com.example.burger.MenuLanches.HotDogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityHost extends AppCompatActivity{

    private ActivityHostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHostBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        AdapterTiposLanches adapterTiposLanches = new AdapterTiposLanches(ActivityHost.this);
        binding.pager2.setAdapter(adapterTiposLanches);
        
        //Fragments que contém o menu de Lanches
        BurgerFragment burgerFragment = new BurgerFragment();
        HotDogFragment hotDogFragment = new HotDogFragment();
        CombosFragment combosFragment = new CombosFragment();

        //Tabs com as opções de Lanches
        adapterTiposLanches.addFragment(burgerFragment, "Burgers");
        adapterTiposLanches.addFragment(hotDogFragment, "Hot Dog");
        adapterTiposLanches.addFragment(combosFragment, "Combos");


        //Controla o Menu com os tipos de Lanches ('Burguers', 'Hot Dog', 'Combos')
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.pager2, (tab, position) -> {

            //Cria uma Tab personalizada
            View customViewTab = LayoutInflater.from(
                    getApplicationContext()).inflate(R.layout.tab_personalizada, binding.pager2, false);

            //Define a Tab com o tipo de Lanche
            tab.setText(adapterTiposLanches.getTitleFragment(position));

            TextView nameLanche = customViewTab.findViewById(R.id.nameLanche);

            TabLayout.Tab selectedTab = binding.tabLayout.getTabAt(0);

            //Listener para a Tab selecionada
            //O Layout Personalizado só é aplicado na Tab que está selecionada.
            binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int positionTab = tab.getPosition();
                    Typeface typeface = ResourcesCompat.getFont(ActivityHost.this, R.font.freckle_face);

                    nameLanche.setTypeface(typeface, R.style.tab_text);
                    nameLanche.setText(adapterTiposLanches.getTitleFragment(positionTab));

                    tab.setCustomView(customViewTab);//Aplica o Layout Personalizado

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tabl) {
                    //Quando essa mesma Tab não estiver mais Selecionada
                    // O Layout Personalizado é retirado
                    tab.setCustomView(null);
                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    //IGNORADO
                }
            });

        });tabLayoutMediator.attach();


        CarrinhoFragment carrinhoFragment = new CarrinhoFragment();
        binding.verCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.containerVi, carrinhoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }
}