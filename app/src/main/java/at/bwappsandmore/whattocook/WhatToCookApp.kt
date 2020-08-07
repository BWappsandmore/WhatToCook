package at.bwappsandmore.whattocook

import at.bwappsandmore.whattocook.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WhatToCookApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>  = DaggerAppComponent.factory().create(this)
}