package com.padc.grocerymtkm.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padc.grocerymtkm.analytics.PARAMETER_EMAIL
import com.padc.grocerymtkm.analytics.SCREEN_LOGIN
import com.padc.grocerymtkm.analytics.TAP_LOGIN
import com.padc.grocerymtkm.data.models.AuthenticationModel
import com.padc.grocerymtkm.data.models.GroceryModel
import com.padc.grocerymtkm.data.models.impls.AuthenticationModelImpl
import com.padc.grocerymtkm.data.models.impls.GroceryModelImpl
import com.padc.grocerymtkm.mvp.presenters.AbstractBasePresenter
import com.padc.grocerymtkm.mvp.presenters.LoginPresenter
import com.padc.grocerymtkm.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel =
        AuthenticationModelImpl
    val mGroceryModel :GroceryModel= GroceryModelImpl

    override fun onUiReady(owner: LifecycleOwner,context: Context) {
        sendEventsToFirebaseAnalytics(context, SCREEN_LOGIN)
        mGroceryModel.setUpRemoteConfigWithDefaultValues()
        mGroceryModel.fetchRemoteConfigs()
    }

    override fun onTapLogin(context: Context,email: String, password: String) {
        sendEventsToFirebaseAnalytics(context, TAP_LOGIN, PARAMETER_EMAIL,email)
        mAuthenticatioModel.login(email, password, onSuccess = {
            mView.navigateToHomeScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }
}