package com.example.week5.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.week5.R
import com.example.week5.databinding.ActivityMainBinding
import com.example.week5.ui.bag.BagFragment
import com.example.week5.ui.buy.BuyFragment
import com.example.week5.ui.home.HomeFragment
import com.example.week5.ui.profile.ProfileFragment
import com.example.week5.ui.wishlist.WishlistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
        private set

    // ✅ Factory 없이 by viewModels() 한 줄로 ViewModel 주입
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // MainViewModel의 init 블록에서 시드 데이터 삽입이 자동으로 시작됩니다.
        // (insertInitialProducts 로직은 ViewModel ▶ Repository로 이동)
        // ViewModel을 한 번이라도 참조해야 생성됩니다.
        viewModel

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, HomeFragment())
                .commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            changeFragment(item.itemId)
            true
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.main_container)
            when (currentFragment) {
                is HomeFragment -> binding.bottomNavigation.menu.findItem(R.id.menu_home).isChecked = true
                is WishlistFragment -> binding.bottomNavigation.menu.findItem(R.id.menu_wishlist).isChecked = true
                is BagFragment -> binding.bottomNavigation.menu.findItem(R.id.menu_bag).isChecked = true
                is ProfileFragment -> binding.bottomNavigation.menu.findItem(R.id.menu_profile).isChecked = true
            }
        }
    }

    fun changeFragment(menuId: Int) {
        val fragment = when (menuId) {
            R.id.menu_home -> HomeFragment()
            R.id.menu_buy -> BuyFragment()
            R.id.menu_wishlist -> WishlistFragment()
            R.id.menu_bag -> BagFragment()
            R.id.menu_profile -> ProfileFragment()
            else -> return
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}
