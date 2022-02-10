package com.yuk.fuckMiLinkService

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedInit : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            "com.milink.service" -> {
                XposedHelpers.findAndHookMethod("com.miui.circulate.world.auth.AuthUtil", lpparam.classLoader, "doPermissionCheck", String::class.java, String::class.java,
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam) { param.result = null }
                    })
                XposedHelpers.findAndHookMethod("com.miui.circulate.world.utils.GetKeyUtil", lpparam.classLoader, "doWhiteListAuth", String::class.java, String::class.java,
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam) { param.result = true }
                    })
            }
            else -> return
        }
    }
}