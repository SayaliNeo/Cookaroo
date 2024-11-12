package com.app.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var dataBinding: DB

    @LayoutRes
    abstract fun provideLayoutRes(): Int

    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
    }

    protected abstract fun initView()

    private fun initViewBinding() {
        dataBinding = DataBindingUtil.setContentView(this, provideLayoutRes())
        setContentView(dataBinding.root)
        initView()
        observeViewModel()
    }
}