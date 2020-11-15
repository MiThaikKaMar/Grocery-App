package com.padc.grocerymtkm.mvp.presenters

import android.content.Context
import com.padc.grocerymtkm.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context: Context,email: String, password: String, userName: String)
}