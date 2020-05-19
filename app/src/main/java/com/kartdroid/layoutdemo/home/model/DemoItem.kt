package com.kartdroid.layoutdemo.home.model

import android.app.Activity

class DemoItem(
    val title: String,
    val demoActivityComponetClass: Class<out Activity>
)