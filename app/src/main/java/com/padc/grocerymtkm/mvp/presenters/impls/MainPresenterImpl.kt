package com.padc.grocerymtkm.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.padc.grocerymtkm.analytics.SCREEN_HOME
import com.padc.grocerymtkm.data.models.impls.AuthenticationModelImpl
import com.padc.grocerymtkm.data.models.impls.GroceryModelImpl
import com.padc.grocerymtkm.data.vos.GroceryVO
import com.padc.grocerymtkm.mvp.presenters.AbstractBasePresenter
import com.padc.grocerymtkm.mvp.presenters.MainPresenter
import com.padc.grocerymtkm.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mGroceryModel =
        GroceryModelImpl
    private val mAuthModel = AuthenticationModelImpl
    private var mChosenGroceryForFileUpload: GroceryVO? = null
    override fun onTapAddGroceryWithoutImage(
        name: String,
        description: String,
        amount: Int,
        image: String
    ) {
        mGroceryModel.addGrocery(name, description, amount, image)
    }

    override fun onTapAddGrocery(groceryVO: GroceryVO, bitmap: Bitmap) {
         mGroceryModel.uploadImageAndUpdateGrocery(groceryVO, bitmap)
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mChosenGroceryForFileUpload?.let {
            mGroceryModel.uploadImageAndUpdateGrocery(it, bitmap)
        }
    }

    override fun onTapEditGrocery(name: String, description: String, amount: Int) {
        mView.showGroceryDialog(name, description, amount.toString())
    }

    override fun onTapFileUpload(grocery: GroceryVO) {
        mChosenGroceryForFileUpload = grocery
        mView.openGallery();
    }

    override fun onUiReady(owner: LifecycleOwner,context: Context) {
        sendEventsToFirebaseAnalytics(context, SCREEN_HOME)
        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)
            },
            onFaiure = {
                mView.showErrorMessage(it)
            }
        )
        mView.showUserName(mAuthModel.getUserName()+",")
        mView.displayToolbarTitle(mGroceryModel.getAppNameFromRemoteConfig())

        val layoutNo =mGroceryModel.getLayout()
        if (layoutNo == 0.toLong()){
            mView.getLayout(0)
        }else if (layoutNo == 1.toLong()){
            mView.getLayout(1)
        }

    }


    override fun onTapDeleteGrocery(name: String) {
        mGroceryModel.removeGrocery(name)
    }
}