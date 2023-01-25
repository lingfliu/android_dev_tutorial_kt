package io.issc.android_dev_tutorial_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.issc.android_dev_tutorial_kt.databinding.FragmentP2Binding
import io.issc.android_dev_tutorial_kt.databinding.FragmentP3Binding

class FragmentP2 : Fragment() {
    lateinit var binding: FragmentP2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentP2Binding.inflate(layoutInflater)
        return binding.root
    }
}