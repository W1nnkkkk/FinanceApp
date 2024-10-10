package com.kirill.finance.presentation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.get
import androidx.core.view.WindowInsetsCompat
import com.kirill.finance.R
import com.kirill.finance.databinding.ActivityAddOperationBinding
import com.kirill.finance.domain.operation.OperationType
import com.kirill.finance.presentation.operation.OperationListItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class AddOperationActivity : AppCompatActivity() {
    var operationType = OperationType.INCOMING

    lateinit var binding: ActivityAddOperationBinding
    private lateinit var operationItem: OperationListItem
    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOperationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent.getStringExtra(Keys.OPERATION_CHANGE_KEY) != null) {
            changeOperation(intent.getStringExtra(Keys.OPERATION_CHANGE_KEY)!!)
        }
        else {
            createOperation()
        }

        binding.addOperationButton.setOnClickListener {
            when(operationType) {
                OperationType.INCOMING -> imageId = R.drawable.ic_incoming
                OperationType.EXPENSES -> imageId = R.drawable.ic_expenses
                else -> imageId = R.drawable.ic_unknown
            }
            try {
                if (binding.operationNameTextView.text.toString() == "") {
                    throw Exception("No awalible text")
                }
                operationItem.typeImage = imageId
                operationItem.name = binding.operationNameTextView.text.toString()
                operationItem.cost = binding.operationCostTextView.text.toString().toInt()
                val jsonString = Json.encodeToString(operationItem)
                val intent = Intent().apply {
                    putExtra(Keys.OPERATION_RESULT_KEY, jsonString)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
            catch(e: Exception) {
                val dialog = AlertDialog
                    .Builder(this@AddOperationActivity)
                    .setTitle(R.string.wrong)
                    .setMessage(R.string.incorect_input)
                    .setPositiveButton("OK") { dialog, which -> }
                    .create()

                dialog.show()
            }
        }
    }

    private fun changeOperation(data: String) {
        operationItem = Json.decodeFromString<OperationListItem>(data)
        binding.addOperationButton.text = getString(R.string.change_button_operation)
        var operationText : String= ""
        when(operationItem.typeImage) {
            R.drawable.ic_incoming ->  {
                operationText = getString(R.string.incoming)
                operationType = OperationType.INCOMING
            }
            R.drawable.ic_expenses ->  {
                operationText = getString(R.string.expenses)
                operationType = OperationType.EXPENSES
            }
            else -> operationType = OperationType.UNKNOWN
        }
        binding.operationTypeSpinner.adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item, arrayOf(operationText))
        binding.operationNameTextView.setText(operationItem.name)
        binding.operationCostTextView.setText(operationItem.cost.toString())
    }

    private fun createOperation() {
        val items = listOf(getString(R.string.incoming), getString(R.string.expenses))

        binding.operationTypeSpinner.adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item, items)

        binding.addOperationButton.text = getString(R.string.add_operation)

        binding.operationTypeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(items[position]) {
                    getString(R.string.incoming) -> operationType = OperationType.INCOMING
                    getString(R.string.expenses) -> operationType = OperationType.EXPENSES
                    else -> operationType = OperationType.UNKNOWN
                }
            }
        }
        operationItem = OperationListItem(
            0,
            "",
            0)
    }
}