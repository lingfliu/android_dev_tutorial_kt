package io.issc.android_dev_tutorial_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.issc.android_dev_tutorial_kt.databinding.FragmentP1Binding

abstract class BaseFragment : Fragment() {
    var signature:String = "base"
}