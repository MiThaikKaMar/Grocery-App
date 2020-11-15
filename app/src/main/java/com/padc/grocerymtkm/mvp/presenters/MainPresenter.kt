package com.padc.grocerymtkm.mvp.presenters

import android.graphics.Bitmap
import com.padc.grocerymtkm.data.vos.GroceryVO
import com.padc.grocerymtkm.delegates.GroceryViewItemActionDelegate
import com.padc.grocerymtkm.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>, GroceryViewItemActionDelegate {
    //fun onTapAddGrocery(name: String, description: String, amount: Int)
    fun onTapAddGroceryWithoutImage(name: String, description: String, amount: Int,image:String)
    fun onTapAddGrocery(groceryVO: GroceryVO, bitmap: Bitmap)
    fun onPhotoTaken(bitmap: Bitmap)
}
