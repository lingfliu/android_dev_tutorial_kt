package io.issc.android_dev_tutorial_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import io.issc.android_dev_tutorial_kt.databinding.FragmentP1Binding

class FragmentP1 : Fragment() {
    lateinit var binding: FragmentP1Binding

    lateinit var btnPage1: Button

    interface Callback {
        fun onRequestPage3()
    }

    var cb:Callback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentP1Binding.inflate(layoutInflater)
        btnPage1 = binding.btnToPage1

        btnPage1.setOnClickListener{
            cb?.onRequestPage3()
        }
        return binding.root
    }
}