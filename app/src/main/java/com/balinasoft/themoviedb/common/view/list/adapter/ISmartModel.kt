package com.balinasoft.themoviedb.common.view.list.adapter

interface ISmartModel {
    fun areItemsTheSame(other: Any): Boolean
    fun isContentsTheSame(other: Any): Boolean {
        return other == this
    }
}
