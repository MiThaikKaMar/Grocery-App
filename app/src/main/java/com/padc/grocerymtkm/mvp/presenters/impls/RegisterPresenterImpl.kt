package com.padc.grocerymtkm.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padc.grocerymtkm.analytics.PARAMETER_EMAIL
import com.padc.grocerymtkm.analytics.SCREEN_REGISTER
import com.padc.grocerymtkm.analytics.TAP_REGISTER
import com.padc.grocerymtkm.data.models.AuthenticationModel
import com.padc.grocerymtkm.data.models.impls.AuthenticationModelImpl
import com.padc.grocerymtkm.mvp.presenters.AbstractBasePresenter
import com.padc.grocerymtkm.mvp.presenters.RegisterPresenter
import com.padc.grocerymtkm.mvp.views.RegisterView

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel =
        AuthenticationModelImpl

    override fun onTapRegister(context: Context,email: String, password: String, userName: String) {
        sendEventsToFirebaseAnalytics(context, TAP_REGISTER, PARAMETER_EMAIL,email)
        mAuthenticationModel.register(email, password, userName, onSuccess = {
            mView.navigateToToLoginScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onUiReady(owner: LifecycleOwner,context: Context) {
        sendEventsToFirebaseAnalytics(context, SCREEN_REGISTER)
    }
}