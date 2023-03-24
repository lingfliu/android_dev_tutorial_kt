package io.issc.android_dev_tutorial_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.issc.android_dev_tutorial_kt.databinding.FragmentP1Binding
import io.issc.android_dev_tutorial_kt.databinding.FragmentP3Binding

class FrgNav3 : BaseFragment() {
    lateinit var binding: FragmentP3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signature = "FrgNav3"
        binding = FragmentP3Binding.inflate(layoutInflater)

        binding.txt.setOnClickListener{

        }
        return binding.root
    }

}