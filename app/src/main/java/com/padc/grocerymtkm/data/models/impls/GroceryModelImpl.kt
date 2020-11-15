package com.padc.grocerymtkm.data.models.impls

import android.graphics.Bitmap
import com.padc.grocerymtkm.data.models.GroceryModel
import com.padc.grocerymtkm.data.vos.GroceryVO
import com.padc.grocerymtkm.network.FirebaseApi
import com.padc.grocerymtkm.network.RealtimeDatabaseFirebaseApiImpl
import com.padc.grocerymtkm.network.remoteconfig.FirebaseRemoteConfigManager

object GroceryModelImpl : GroceryModel {
    override var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl

    private val mFirebaseRemoteConfigManager : FirebaseRemoteConfigManager= FirebaseRemoteConfigManager

    //override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getGroceries(onSuccess, onFaiure)
    }

    override fun addGrocery(name: String, description: String, amount: Int, image: String) {
        mFirebaseApi.addGrocery(name, description, amount, image)
    }

    override fun removeGrocery(name: String) {
        mFirebaseApi.deleteGrocery(name)
    }

    override fun uploadImageAndUpdateGrocery(grocery: GroceryVO, image: Bitmap) {
            mFirebaseApi.uploadImageAndEditGrocery(image, grocery)
    }

    override fun setUpRemoteConfigWithDefaultValues() {
        mFirebaseRemoteConfigManager.setUpRemoteConfigWithDefaultValues()
    }

    override fun fetchRemoteConfigs() {
        mFirebaseRemoteConfigManager.fetchRemoteConfig()
    }

    override fun getAppNameFromRemoteConfig(): String {
       return mFirebaseRemoteConfigManager.getToolbarName()
    }

    override fun getLayout(): Long {
        return mFirebaseRemoteConfigManager.getLayout()
    }


}