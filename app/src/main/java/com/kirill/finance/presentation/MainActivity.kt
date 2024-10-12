package com.kirill.finance.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.kirill.finance.R
import com.kirill.finance.databinding.ActivityMainBinding
import com.kirill.finance.presentation.viewmodel.currency.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var currencyViewModel : CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            setFragment(
                R.id.fragmentLayout,
                OperationListFragment.newInstance()
            )
        }

        currencyViewModel.item.observe(this as LifecycleOwner, {
            Log.d("LOG", it.rusValue.toString())
        })

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.listOperations -> {
                    setFragment(
                        R.id.fragmentLayout,
                        OperationListFragment.newInstance()
                    )
                }

                R.id.graphOperations -> {
                    setFragment(
                        R.id.fragmentLayout,
                        CircleGraphFragment.newInstance()
                    )
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setFragment(containerView: Int,  fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerView, fragment)
            .commit()
    }
}