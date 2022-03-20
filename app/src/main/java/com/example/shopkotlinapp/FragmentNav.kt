package com.example.shopkotlinapp

import androidx.fragment.app.Fragment

interface FragmentNav {
    fun navigateFragment(fragment: Fragment, addToStack: Boolean)
}