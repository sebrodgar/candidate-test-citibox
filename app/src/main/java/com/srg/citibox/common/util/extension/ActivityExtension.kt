package com.srg.citibox.common.util.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

fun Activity.openActivity(classToOpen: Class<*>, extras: Bundle) =
    startActivity(Intent(this, classToOpen).putExtras(extras))