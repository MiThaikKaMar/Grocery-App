package com.padc.grocerymtkm.data.models

import android.graphics.Bitmap
import com.padc.grocerymtkm.data.vos.GroceryVO
import com.padc.grocerymtkm.network.FirebaseApi

interface GroceryModel {

    var mFirebaseApi : FirebaseApi

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addGrocery(name: String ,description : String, amount: Int, image: String)

    fun removeGrocery(name: String)

    fun uploadImageAndUpdateGrocery(grocery : GroceryVO, image : Bitmap)

    fun setUpRemoteConfigWithDefaultValues()

    fun fetchRemoteConfigs()

    fun getAppNameFromRemoteConfig():String

    fun getLayout():Long
}