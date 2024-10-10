package com.kirill.finance.presentation.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.kirill.finance.R
import com.kirill.finance.domain.operation.usecases.DeleteOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditCostOperationUseCase
import com.kirill.finance.domain.operation.usecases.EditNameOperationUseCase
import com.kirill.finance.presentation.AddOperationActivity
import com.kirill.finance.presentation.OperationListFragment
import com.kirill.finance.presentation.operation.OperationListItem
import com.kirill.finance.presentation.operation.mapper.OperationListItemMapper
import com.kirill.finance.presentation.viewmodel.OperationViewModel
import java.lang.reflect.Field
import javax.inject.Inject


class OperationListAdapter @Inject constructor(
    private val viewModel: OperationViewModel,
    private val openChangeActivity: (operationItem: OperationListItem) -> Unit
) : RecyclerView.Adapter<OperationListAdapter.OperationViewHolder>() {

    private var operationList: MutableList<OperationListItem> = mutableListOf()

    private val mapper = OperationListItemMapper()

    private var editItemPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.finance_item, parent, false)
        return OperationViewHolder(view)
    }

    override fun getItemCount() = operationList.size

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        holder.imageView.setImageResource(operationList[position].typeImage)
        holder.name.text = operationList[position].name
        holder.count.text = operationList[position].cost.toString()

        holder.otherButton.setOnClickListener {
            createPopup(holder.itemView.context, holder.otherButton)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.delete_operation -> {
                        viewModel.deleteOperation(operationList[position], position)
//                        operationList.removeAt(position)
//                        OperationListFragment.setTotalCount(operationList)
//                        notifyDataSetChanged()
                    }
                    R.id.edit_name_operation -> {
                        Log.d("LOG", operationList[position].id.toString())
                        openChangeActivity(operationList[position])
                        editItemPosition = position
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }

    fun setOperationList(newList : List<OperationListItem>) {
        operationList = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun changeOperation(operationListItem: OperationListItem) {
        operationList[editItemPosition].name = operationListItem.name
        operationList[editItemPosition].cost = operationListItem.cost
        notifyItemChanged(editItemPosition)
    }

    fun addOperation(operation: OperationListItem) {
        operationList.add(0, operation)
        notifyDataSetChanged()
    }

    fun getData() = operationList.toList()

    class OperationViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView
        val name : TextView
        val count : TextView
        val otherButton : AppCompatImageButton
        init {
            imageView = itemView.findViewById(R.id.operationTypeImage)
            name = itemView.findViewById(R.id.operationName)
            count = itemView.findViewById(R.id.operationCount)
            otherButton = itemView.findViewById(R.id.otherButton)
        }
    }

    private lateinit var popupMenu: PopupMenu

    private fun createPopup(context: Context, view: View) {
        popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.popup_operation_menu)

        try {
            val fMenuHelper: Field = PopupMenu::class.java.getDeclaredField("mPopup")
            fMenuHelper.setAccessible(true)
            var menuHelper = fMenuHelper.get(popupMenu)
            var argTypes = arrayOf<Class<*>?>(Boolean::class.javaPrimitiveType)
            menuHelper.javaClass.getDeclaredMethod("setForceShowIcon", *argTypes)
                .invoke(menuHelper, true)
        } catch (e: Exception) {
            Log.w("TAG", "error forcing menu icons to show", e)
            return
        }
    }
}