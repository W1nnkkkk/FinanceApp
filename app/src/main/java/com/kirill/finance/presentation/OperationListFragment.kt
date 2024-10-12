package com.kirill.finance.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirill.finance.R
import com.kirill.finance.databinding.FragmentOperationListBinding
import com.kirill.finance.presentation.operation.OperationListItem
import com.kirill.finance.presentation.adapter.OperationListAdapter
import com.kirill.finance.presentation.viewmodel.operation.OperationViewModel
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class OperationListFragment: Fragment() {

    private lateinit var binding: FragmentOperationListBinding
    private lateinit var adapter: OperationListAdapter

    @Inject
    lateinit var viewModel: OperationViewModel

    lateinit var startOperationActivity: ActivityResultLauncher<Intent>
    lateinit var startChangeOperationActivity: ActivityResultLauncher<Intent>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOperationListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startOperationActivity = registerForActivityResult(
                androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { it ->
            if (it.resultCode == RESULT_OK) {
                val operationListItem : String? =
                    it.data?.getStringExtra(Keys.OPERATION_RESULT_KEY)
                if (operationListItem != null) {
                    val operationItem = Json.decodeFromString<OperationListItem>(operationListItem)
                    viewModel.insertOperation(operationItem)
                }
            }
        })

        startChangeOperationActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { it ->
                if (it.resultCode == RESULT_OK) {
                    val operationListItem : String? =
                        it.data?.getStringExtra(Keys.OPERATION_RESULT_KEY)
                    if (operationListItem != null) {
                        val item = Json.decodeFromString<OperationListItem>(operationListItem)
                        viewModel.editCost(item.id, item.cost)
                        viewModel.editName(item.id, item.name)
                        adapter.changeOperation(item)
                        setTotalCount(adapter.getData())
                    }
                }
            })

        adapter = OperationListAdapter(viewModel) { it ->
            val intent = Intent(this.context, AddOperationActivity::class.java)
            val jsonString = Json.encodeToString(it)
            intent.putExtra(Keys.OPERATION_CHANGE_KEY, jsonString)
            startChangeOperationActivity.launch(intent)
        }
        binding.recyclerView.setLayoutManager(LinearLayoutManager(this.context));
        binding.recyclerView.adapter = adapter

        viewModel.operationList.observe(this as LifecycleOwner, {
            adapter.setOperationList(it)
            setTotalCount(it)
        })

        binding.button.setOnClickListener {
            val intent = Intent(this.context, AddOperationActivity::class.java)
            startOperationActivity.launch(intent)
        }
    }


    companion object {
        private var INSTANCE: OperationListFragment? = null

        fun setTotalCount(list: List<OperationListItem>) {
            if (INSTANCE != null) {
                var sum: Long = 0
                list.forEach {
                    if (it.typeImage == R.drawable.ic_incoming) {
                        sum += it.cost
                    }
                    else {
                        sum -= it.cost
                    }
                }

                INSTANCE.let {
                    it!!.binding.maxCost.text = it.getString(R.string.total_cost) + " $sum"
                }
            }
        }

        fun newInstance(): OperationListFragment {
            return INSTANCE ?: synchronized(this) {
                val instance = OperationListFragment()
                INSTANCE = instance
                instance
            }
        }
    }
}