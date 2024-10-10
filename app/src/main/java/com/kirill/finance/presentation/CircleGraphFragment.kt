package com.kirill.finance.presentation

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentController
import androidx.lifecycle.LifecycleOwner
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.kirill.finance.R
import com.kirill.finance.databinding.FragmentCircleGraphBinding
import com.kirill.finance.domain.operation.OperationType
import com.kirill.finance.domain.operation.usecases.GetOperationsUseCase
import com.kirill.finance.presentation.viewmodel.OperationViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CircleGraphFragment : Fragment() {

    private lateinit var binding: FragmentCircleGraphBinding

    @Inject
    lateinit var viewModel: OperationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCircleGraphBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pieChart: PieChart = binding.pieChart
        viewModel.operationList.observe(this as LifecycleOwner, {
            var incomingCount = 0f
            var expensesCount = 0f
            it.forEach {
                if (it.typeImage == R.drawable.ic_incoming) { incomingCount++ }
                else if (it.typeImage == R.drawable.ic_expenses) { expensesCount++ }
            }

            incomingCount = (incomingCount / it.size) * 100
            expensesCount = (expensesCount / it.size) * 100
            setData(pieChart, incomingCount, expensesCount)
        })
    }

    private fun setData(pieChart: PieChart, incoming: Float, expenses: Float) {
        val entries: MutableList<PieEntry> = mutableListOf(PieEntry(incoming, getString(R.string.incoming)),
            PieEntry(expenses, getString(R.string.expenses))
        )
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        pieChart.setDragDecelerationFrictionCoef(0.95f)

        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)

        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)


        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)

        pieChart.setDrawCenterText(true)

        pieChart.setRotationAngle(0f)

        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)
        pieChart.legend.isEnabled = false

        pieChart.animateY(1400, Easing.EaseInOutQuad)

        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        val dataSet = PieDataSet(entries, getString(R.string.operations))

        dataSet.valueTextSize = 16F

        dataSet.setDrawIcons(true)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        dataSet.colors = listOf(resources.getColor(R.color.main_color),
            resources.getColor(R.color.accent_color))

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        pieChart.setData(data)

        pieChart.highlightValues(null)
        pieChart.invalidate()
    }


    companion object {
        @JvmStatic
        fun newInstance() = CircleGraphFragment()
    }
}