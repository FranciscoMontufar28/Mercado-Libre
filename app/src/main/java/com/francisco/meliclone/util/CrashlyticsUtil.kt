package com.francisco.meliclone.util

import com.google.firebase.crashlytics.FirebaseCrashlytics

fun crashlyticsLog(message: String) {
    FirebaseCrashlytics.getInstance().log(message)
}