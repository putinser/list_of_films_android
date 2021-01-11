package com.balinasoft.themoviedb.mvp.base.base_screen

import com.arellomobile.mvp.MvpPresenter

import com.vmeste.app.mvp.base.baseScreen.BaseScreenView

open class BaseScreenPresenter<VIEW : BaseScreenView>() : MvpPresenter<VIEW>() {

    open fun refresh(): Boolean {
        return false
    }

}