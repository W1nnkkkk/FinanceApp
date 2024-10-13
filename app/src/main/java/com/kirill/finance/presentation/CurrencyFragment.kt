package com.kirill.finance.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.kirill.finance.R
import com.kirill.finance.databinding.FragmentCurrencyBinding
import com.kirill.finance.presentation.viewmodel.currency.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding
    @Inject
    lateinit var currencyViewModel : CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.russianCurrency.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isEmpty()) {
                    val num = s.toString().toDouble()
                    val usdCurrency = num * (currencyViewModel.item.value?.usdValue ?: 0.0)
                    val eurCurrency = num * (currencyViewModel.item.value?.eurValue ?: 0.0)
                    binding.usaCurrency.setText(usdCurrency.toString())
                    binding.europeCurrency.setText(eurCurrency.toString())
                }
                else
                {
                    binding.usaCurrency.setText("")
                    binding.europeCurrency.setText("")
                }
            }
        })
    }

    companion object {
        private var INSTANCE: CurrencyFragment? = null

        fun newInstance() : CurrencyFragment  {
            return INSTANCE ?: synchronized(this) {
                val instance = CurrencyFragment()
                INSTANCE = instance
                instance
            }
        }
    }
}