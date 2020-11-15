package com.padc.grocerymtkm.mvp.presenters

import android.content.Context
import com.padc.grocerymtkm.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView>{
    fun onTapLogin(context: Context,email: String, password: String)
    fun onTapRegister()
}