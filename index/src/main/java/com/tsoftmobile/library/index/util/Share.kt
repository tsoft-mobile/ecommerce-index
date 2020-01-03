package com.tsoftmobile.library.index.util

import com.securepreferences.SecurePreferences
import com.tsoftmobile.library.index.TSoftApplication


object Share {
    private val prefs: SecurePreferences by lazy {
        SecurePreferences(TSoftApplication.applicationContext(), "tsoft_index_pref", "tsoft_index_pref")
    }
    private val editor: SecurePreferences.Editor by lazy {
        prefs.edit()
    }

    fun remove(key: String) {
        editor.remove(key)
        editor.apply()
    }

    fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, default: String): String {
        return prefs.getString(key, default)?:default
    }

    fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, default: Int): Int {
        return prefs.getInt(key, default)
    }

    fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return prefs.getBoolean(key, default)
    }


}