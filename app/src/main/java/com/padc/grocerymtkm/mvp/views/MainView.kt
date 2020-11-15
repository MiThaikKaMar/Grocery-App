package com.padc.grocerymtkm.mvp.views

import com.padc.grocerymtkm.data.vos.GroceryVO

interface MainView : BaseView {
    fun showGroceryData(groceryList: List<GroceryVO>)
    fun showGroceryDialog(name: String, description: String, amount: String)
    fun showErrorMessage(message: String)
    fun openGallery()
    fun showUserName(name: String)

    fun displayToolbarTitle(title :String)
    fun getLayout(viewType : Long)
}