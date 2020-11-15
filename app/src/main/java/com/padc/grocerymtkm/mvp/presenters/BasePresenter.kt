package com.padc.grocerymtkm.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padc.grocerymtkm.mvp.views.BaseView

interface BasePresenter<V: BaseView> {
    fun onUiReady(owner: LifecycleOwner,context: Context)
    fun initPresenter(view: V)
}