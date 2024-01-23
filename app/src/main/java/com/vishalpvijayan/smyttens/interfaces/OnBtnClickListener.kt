package com.vishalpvijayan.smyttens.interfaces

import com.vishalpvijayan.smyttens.data.ButtonEntity

interface OnBtnClickListener {
    fun onButtonClicked(button: ButtonEntity, name:String)
}