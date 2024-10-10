package com.kirill.finance.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.kirill.finance.R
import com.kirill.finance.databinding.ActivityMainBinding
import com.kirill.finance.domain.operation.OperationItem
import com.kirill.finance.domain.operation.OperationItemRepository
import com.kirill.finance.domain.operation.OperationType
import com.kirill.finance.domain.operation.usecases.GetOperationsUseCase
import com.kirill.finance.presentation.viewmodel.OperationViewModel
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

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