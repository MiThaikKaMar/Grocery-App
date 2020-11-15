package com.padc.grocerymtkm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.grocerymtkm.R
import com.padc.grocerymtkm.data.vos.GroceryVO
import com.padc.grocerymtkm.delegates.GroceryViewItemActionDelegate
import com.padc.grocerymtkm.viewholders.GroceryViewHolder
import com.zg.burgerjoint.adapters.BaseRecyclerAdapter

class GroceryAdapter(private val mDelegate: GroceryViewItemActionDelegate,val viewTypes:Long) : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {

        when (viewTypes) {
            0.toLong() -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_grocery_item, parent, false)
                return GroceryViewHolder(view, mDelegate)
            }
            1.toLong() -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.grocery_item, parent, false)
                return GroceryViewHolder(view, mDelegate)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_grocery_item, parent, false)
                return GroceryViewHolder(view, mDelegate)
            }
        }

    }
}